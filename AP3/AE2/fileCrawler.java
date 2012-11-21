import java.util.concurrent.*;
import java.util.regex.*;
import java.util.*;
import java.io.*;

public class fileCrawler {
	// Using LinkedBlockingQueue to make it safe for threads to
	// queue/dequeue elements concurrently
	private LinkedBlockingQueue<String> workQueue;
	private ConcurrentHashMap<String, LinkedList<String>> anotherStructure;
	private Object lockObject;

	private class Worker implements Runnable {

		private LinkedBlockingQueue<String> queue;
		private ConcurrentHashMap<String, LinkedList<String>> otherStructure;
		private Object lockObject;
		private Pattern pat;
		private int name;
		public Worker(int n,LinkedBlockingQueue<String> q,
				ConcurrentHashMap<String, LinkedList<String>> s, Object lock,
				Pattern p) {
			queue = q;
			otherStructure = s;
			lockObject = lock;
			pat = p;
			name=n;
		}

		// Part of the code in Worker.run was taken from the example on the
		// AE2 specification document section 7.
		public void run() {

			//main checks if "directory" is actually a directory and not a file
			//before adding it to the WorkQueue
			String directory;
			//System.out.println("Internal queue has element: " +queue.poll());
			while ((directory = queue.poll()) != null) {
				try{
					File dir = new File(directory);//create a file object
					String files[] = dir.list();
					Arrays.sort(files);
					for (String temp: files){
						//System.out.println("Continue"); 

						String filePath=directory+"/"+temp;
						//System.out.println("Directory "+directory+"+ temp "+temp+"="+filePath);
						//System.out.println(filePath);
						File entre=new File(filePath);
						if (entre.isDirectory()){
							//System.out.println(entre.getName()+" is a directory, skip");
							continue;
						}else if(entre.isFile()){
						//	System.out.println("File "+entre.getName());
							//System.out.println("in else");
							if (matchRegex(pat,temp)){
								if(anotherStructure.get(directory)==null){
									LinkedList<String> filesList=new LinkedList<String>();
									otherStructure.put(directory,filesList);
								}
								LinkedList<String>list=otherStructure.get(directory);
								list.add(temp);
								otherStructure.put(directory,list);
								//otherStructure.put(file.getName(),list);

							}
					}else{
						//System.out.println(filePath+" is not a file");
					}
					}}catch (Exception e){
					System.err.println("Error processing file");
				}}
		
			
			}}

	public fileCrawler() {
		workQueue = new LinkedBlockingQueue<String>();
		anotherStructure=new ConcurrentHashMap<String, LinkedList<String>>();
	//	anotherStructure = new ConcurrentHashMap<String, LinkedList<String>>();
		lockObject = new Object();
	}
 
	public Worker createWorker(Pattern pattern, int n) {
		return new Worker(n, workQueue, anotherStructure, lockObject, pattern);
	}

	/*
	 * routine to convert bash pattern to regex pattern
	 * 
	 * e.g. if bashpat is "*.c", pattern generated is "^.*\.c$" if bashpat is
	 * "a.*", pattern generated is "^a\..*$"
	 * 
	 * i.e. '*' is converted to ".*" '.' is converted to "\." '?' is converted
	 * to "." '^' is put at the beginning of the regex pattern '$' is put at the
	 * end of the regex pattern
	 * 
	 * assumes 'pattern' is large enough to hold the regular expression
	 */
	public static Pattern cvtPattern(String bashpat) {
		StringBuilder pat = new StringBuilder();
		int start, length;
		pat.append('^');
		if (bashpat.charAt(0) == '\'') { // double quoting on Windows
			start = 1;
			length = bashpat.length() - 1;
		} else {
			start = 0;
			length = bashpat.length();
		}
		for (int i = start; i < length; i++) {
			switch (bashpat.charAt(i)) {
			case '*':
				pat.append('.');
				pat.append('*');
				break;
			case '.':
				pat.append('\\');
				pat.append('.');
				break;
			case '?':
				pat.append('.');
				break;
			default:
				pat.append(bashpat.charAt(i));
				break;
			}
		}
		pat.append('$');
		Pattern p = Pattern.compile(new String(pat));
		return p;
	}

	private static boolean matchRegex(Pattern regExpression, String filename) {
		Matcher m = regExpression.matcher(filename);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}


public static void processDirectory( String name,LinkedBlockingQueue<String> work) {
	LinkedBlockingQueue<String> q=work;
	try {
		File file = new File(name); // create a File object
		if (file.isDirectory()) { // a directory - could be symlink
	//			System.out.println(name);
			String entries[] = file.list();
		if (entries != null) { // not a symlink
			q.add((String)name);
			for (String entry : entries ) {
			if (entry.compareTo(".") == 0)
				continue;
			if (entry.compareTo("..") == 0)
				continue;
			processDirectory(new String(name+"/"+entry),q);
			}
		}
		}
	} catch (Exception e) {
		System.err.println("Error processing "+name+": "+e);
	}

}
	public static void main(String Arg[]) throws Exception {
		String directory = "";
		String pattern = "";
		fileCrawler crawler = new fileCrawler();
		if (Arg.length < 1) {
			System.err.println("Usage: ./fileCrawler pattern [dir] ... ");
			System.exit(1);
		}
		// convert and compile the given pattern to Java Regex Pattern
		Pattern pat = cvtPattern(Arg[0]);
		// save given directory name into directory variable
		if(Arg.length==2){
			directory = Arg[1];
		}else{
			directory=".";
		}
		processDirectory(directory, crawler.workQueue);//Traverse directories and add them to workQueue/
	//	List<String> al = new ArrayList<String>(g.keySet());


		String envThreadsString = System.getenv("CRAWLER_THREADS"); 
		Integer envThreadsInt;
		if (envThreadsString == null) 
			envThreadsInt = new Integer(2); 
		else 
			envThreadsInt = new Integer(envThreadsString);

		Thread[] threads=new Thread[envThreadsInt];
		for(int i=0;i<envThreadsInt; i++){
			threads[i]= new Thread(crawler.createWorker(pat,i));
			threads[i].start();

		}
		for(int i=0;i<envThreadsInt; i++){
			try{
				threads[i].join(); 
			}catch(Exception e){};
			threads[i].interrupt();
		}

			//	TreeMap tree=new TreeMap(anotherStructure);
		Set<String> keys=crawler.anotherStructure.keySet();
		String[] skeys=keys.toArray(new String[keys.size()]);
		//Arrays.sort(skeys);
		TreeSet<String> tree=new TreeSet<String>();
		for(String s:skeys){
					for (String f:crawler.anotherStructure.get(s)){
				tree.add(s+"/"+f);
				//System.out.println(s+"/"+f);

			}}
		Iterator it=tree.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		//
		// now place each directory into the workQ
		//
		/*
		 * LinkedList<String> objects = new LinkedList<String>(); for (int i =
		 * fileStart; i < Arg.length; i++) { int ndx = Arg[i].lastIndexOf('.');
		 * String root, ext, obj; if (ndx == -1) { root = new String(Arg[i]);
		 * ext = new String(""); } else { root = Arg[i].substring(0, ndx); ext =
		 * Arg[i].substring(ndx + 1); } if (ext.compareTo("c") != 0 &&
		 * ext.compareTo("y") != 0 && ext.compareTo("l") != 0) {
		 * System.err.println("File arguments must end in .c, .y, or .l: " +
		 * Arg[i]); System.exit(1); } obj = root + ".o"; objects.add(obj);
		 * LinkedList<String> ll = new LinkedList<String>(); ll.add(Arg[i]);
		 * ic.theTable.put(obj, ll); try { ic.workQ.put(Arg[i]); } catch
		 * (Exception e) { } ; ll = new LinkedList<String>();
		 * ic.theTable.put(Arg[i], ll); }
		 * 
		 * // // now process each file in the workQ // int N =
		 * nthreads.intValue(); ArrayList<Thread> thread = new
		 * ArrayList<Thread>(N); for (int i = 0; i < N; i++) { Thread t = new
		 * Thread(ic.createWorker()); thread.add(t); t.start(); } for (int i =
		 * 0; i < N; i++) try { thread.get(i).join(); } catch (Exception e) { }
		 * ; //
		 * 
		 * // // now print dependencies on System.out // Iterator<String> it =
		 * objects.iterator(); while (it.hasNext()) { LinkedList<String>
		 * toProcess = new LinkedList<String>(); String obj = it.next();
		 * System.out.print(obj + ":"); HashMap<String, Integer> seen = new
		 * HashMap<String, Integer>(); seen.put(obj, new Integer(1));
		 * toProcess.add(obj); ic.printDependencies(seen, toProcess);
		 * System.out.print("\n"); }
		 */
		System.exit(0);
	}
}