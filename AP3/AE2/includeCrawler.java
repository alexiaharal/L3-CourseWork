import java.util.concurrent.*;
import java.util.*;
import java.io.*;

public class includeCrawler {

	private LinkedBlockingQueue<String> workQ;
	private ConcurrentHashMap<String,LinkedList<String>> theTable;
	private LinkedList<String> searchPath;
	private Object lockObject;

	private class Worker implements Runnable {

		private LinkedBlockingQueue<String> queue;
		private ConcurrentHashMap<String,LinkedList<String>> table;
		private LinkedList<String> path;
		private Object lockObject;

		public Worker(LinkedBlockingQueue<String> q,
			      LinkedList<String> p,
			      ConcurrentHashMap<String,LinkedList<String>> t,
			      Object lo) {
			queue = q;
			path = p;
			table = t;
			lockObject = lo;
		}

		public Scanner openFile(String filename) {
			Scanner scan = null;
			Iterator<String> dit = path.iterator();
			while (dit.hasNext()) {
				try {
					String f = dit.next()+filename;
					scan = new Scanner(new FileInputStream(f));
					return scan;
				} catch (Exception e) {};
			}
			return null;
		}

		public void process(String file, LinkedList<String> ll) {
			Scanner sc = this.openFile(file);
			if (sc == null) {
				System.err.println("Unable to open file: "+file);
				System.exit(1);
			}
			while (sc.hasNext()) {
				String line = sc.nextLine();
				String t1 = line.trim();
				if (t1.startsWith("#include")) {
					String t2 = t1.substring(8).trim();
					if (t2.startsWith("\"")) {
						int ndx = t2.indexOf('"', 1);
						String t3 = t2.substring(1, ndx);
						ll.add(t3);
						synchronized(lockObject) {
							if (table.get(t3) == null) {
								LinkedList<String> list = 
									new LinkedList<String>();
								table.put(t3, list);
								try {
									queue.put(t3);
								} catch (Exception e) {};
							}
						}
					}
				}
			}
			sc.close();
		}

		public void run() {
			String file;
			while ((file = queue.poll()) != null) {
				LinkedList<String> ll = table.get(file);
				process(file, ll);
				table.put(file, ll);
			}
		}
	}

	public includeCrawler() {
		workQ = new LinkedBlockingQueue<String>();
		theTable = new ConcurrentHashMap<String,LinkedList<String>>();
		searchPath = new LinkedList<String>();
		lockObject = new Object();
	}

	public Worker createWorker() {
		return new Worker(workQ, searchPath, theTable, lockObject);
	}

	public void addToSearchPath(String d) {
		if(d.endsWith("/"))
			searchPath.addLast(d);
		else
			searchPath.addLast(d+"/");
	}

	public void printDependencies(HashMap<String,Integer> seen,
			              LinkedList<String> toProcess) {
		try {
			for(;;) {
				String file = toProcess.remove();
				LinkedList<String> ll = theTable.get(file);
				Iterator<String> it = ll.iterator();
				while (it.hasNext()) {
					String f = it.next();
					if (seen.get(f) != null)
						continue;
					System.out.print(" "+f);
					seen.put(f, new Integer(1));
					toProcess.add(f);
				}
			}
		} catch (Exception e) {}
	}

	public static void main(String Arg[]) {

		includeCrawler ic = new includeCrawler();
//
// build up search path
//
// first, "./"
//
		ic.addToSearchPath("./");
//
// now all -Idir flags
//
		int fileStart;
		for (fileStart = 0; fileStart < Arg.length; fileStart++) {
			if (! Arg[fileStart].startsWith("-I"))
				break;
			ic.addToSearchPath(Arg[fileStart].substring(2));
		}
//
// now any directories contained in CPATH
//
		String cpath = System.getenv("CPATH");
		String[] dirs;
		if (cpath != null) {
			dirs = cpath.split(":");
			for (int i = 0; i < dirs.length; i++)
				ic.addToSearchPath(dirs[i]);
		}
//
// obtain the number of threads
//
		String thstring = System.getenv("CRAWLER_THREADS");
		Integer nthreads;
		if (thstring == null)
			nthreads = new Integer(2);
		else
			nthreads = new Integer(thstring);
//
// now place each file into the workQ
//
		LinkedList<String> objects = new LinkedList<String>();
		for (int i = fileStart; i < Arg.length; i++) {
			int ndx = Arg[i].lastIndexOf('.');
			String root, ext, obj;
			if (ndx == -1) {
				root = new String(Arg[i]);
				ext = new String("");
			} else {
				root = Arg[i].substring(0, ndx);
				ext = Arg[i].substring(ndx+1);
			}
			if (ext.compareTo("c") != 0 &&
			    ext.compareTo("y") != 0 &&
			    ext.compareTo("l") != 0) {
				System.err.println("File arguments must end in .c, .y, or .l: "+Arg[i]);
				System.exit(1);
			}
			obj = root+".o";
			objects.add(obj);
			LinkedList<String> ll = new LinkedList<String>();
			ll.add(Arg[i]);
			ic.theTable.put(obj, ll);
			try {
				ic.workQ.put(Arg[i]);
			} catch (Exception e) {};
			ll = new LinkedList<String>();
			ic.theTable.put(Arg[i], ll);
		}
//
// now process each file in the workQ
//
		int N = nthreads.intValue();
		ArrayList<Thread> thread = new ArrayList<Thread>(N);
		for (int i = 0; i < N; i++) {
			Thread t = new Thread(ic.createWorker());
			thread.add(t);
			t.start();
		}
		for (int i = 0; i < N; i++)
			try {
				thread.get(i).join();
			} catch (Exception e) {};
//
// now print dependencies on System.out
//
		Iterator<String> it = objects.iterator();
		while (it.hasNext()) {
			LinkedList<String> toProcess = new LinkedList<String>();
			String obj = it.next();
			System.out.print(obj+":");
			HashMap<String,Integer> seen =
				new HashMap<String,Integer>();
			seen.put(obj, new Integer(1));
			toProcess.add(obj);
			ic.printDependencies(seen, toProcess);
			System.out.print("\n");
		}
	}
}