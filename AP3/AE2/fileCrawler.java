import java.util.concurrent.*;
import java.util.regex.*;
import java.util.*;
import java.io.*;

public class fileCrawler {
	// Using LinkedBlockingQueue to make it safe for threads to
	// queue/dequeue elements concurrently
	private LinkedBlockingQueue<String> workQueue;
	private ConcurrentHashMap<String, LinkedList<String>> anotherStructure;

	private class Worker implements Runnable {

		private LinkedBlockingQueue<String> queue;
		private ConcurrentHashMap<String, LinkedList<String>> otherStructure;
		private Pattern pat;
		public Worker(LinkedBlockingQueue<String> q,
				ConcurrentHashMap<String, LinkedList<String>> s,
				Pattern p) {
			queue = q;
			otherStructure = s;
			pat = p;
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
					String dirFiles[] = dir.list();
					Arrays.sort(dirFiles);
					for (String entry: dirFiles){
						String filePath=directory+"/"+entry;
						File file=new File(filePath);
						if (file.isDirectory()){
//if a directory is found add it to the workQueue so that a worker can pick it up and inspect it.
			if (entry.compareTo(".") == 0)
				continue;
			if (entry.compareTo("..") == 0)
				continue;
							queue.add(filePath);	

							continue;
						}else {
							//Check if the name of this file matches
							//given pattern.
							if (matchRegex(pat,entry)){
								//if it matches then add it to the hashMap
								//that holds all found files under their
								//directories.
								if(otherStructure.get(directory)==null){
									LinkedList<String> filesList=new LinkedList<String>();
									otherStructure.put(directory,filesList);
								}
								LinkedList<String>filesList=otherStructure.get(directory);
								filesList.add(entry);
								otherStructure.put(directory,filesList);
								//otherStructure.put(file.getName(),list);
							}
					}
					}}catch (Exception e){
					System.err.println("Error processing file");
				}}
		
			
			}}

	public fileCrawler() {
		workQueue = new LinkedBlockingQueue<String>();
		anotherStructure=new ConcurrentHashMap<String, LinkedList<String>>();
	}
 
	public Worker createWorker(Pattern pattern, int n) {
		return new Worker(workQueue, anotherStructure, pattern);
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

//matchRegex returns true if filaname matches given regExpression or
//false otherwise.
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

	//not used anymore:	processDirectory(directory, crawler.workQueue);
		crawler.workQueue.add(directory);

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
		//Set<String> keys=crawler.anotherStructure.keySet();
		//String[] skeys=keys.toArray(new String[keys.size()]);
		//Arrays.sort(skeys);
	//	Iterator hashIt=anotherStructure.iterator()
		TreeSet<String> tree=new TreeSet<String>();
		for (String key : crawler.anotherStructure.keySet()){
			Iterator listIt=crawler.anotherStructure.get(key).iterator();
			while(listIt.hasNext()){
				tree.add(key+"/"+crawler.anotherStructure.get(key).poll());			
			}

		} 
					//for (String f:crawler.anotherStructure.get(s)){
				//tree.add(s+"/"+f);
				//System.out.println(s+"/"+f);

		
		Iterator it=tree.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		System.exit(0);
	}
}
