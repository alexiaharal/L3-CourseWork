import java.util.concurrent.*;
import java.util.regex.*;
import java.util.*;
import java.io.*;

public class fileCrawler {
	// Using LinkedBlockingQueue to make it safe for threads to
	// queue/dequeue elements concurrently
	private LinkedBlockingQueue<String> workQueue;
	private ConcurrentHashMap<String, LinkedList<String>> anotherStructure;

	public fileCrawler() {
		workQueue = new LinkedBlockingQueue<String>();
		anotherStructure = new ConcurrentHashMap<String, LinkedList<String>>();
	}

	public Worker createWorker(Pattern pattern, int n) {
		return new Worker(workQueue, anotherStructure, pattern);
	}

	// Worker thread
	private class Worker implements Runnable {
		private LinkedBlockingQueue<String> queue;
		private ConcurrentHashMap<String, LinkedList<String>> otherStructure;
		private Pattern pat;

		public Worker(LinkedBlockingQueue<String> q,
				ConcurrentHashMap<String, LinkedList<String>> s, Pattern p) {
			queue = q;
			otherStructure = s;
			pat = p;
		}

		public void run() {
			String directory;
			// If work queue is empty and interrupt signal was sent, stop the
			// thread.
			// if work queue is empty but the thread is not interrupted continue
			// running. There are still elements to be added to the work queue.
			while ((directory = queue.poll()) != null || !Thread.interrupted()) {
				if (directory == null) {
					continue;
				} else {
					try {
						File dir = new File(directory);
						String dirFiles[] = dir.list();
						for (String entry : dirFiles) {

							String filePath = directory + "/" + entry;
							File file = new File(filePath);
							if (file.isDirectory()) {
								continue;// we are only interested at files so
											// ignore any directories.
							} else {
								// Check if the name of this file matches
								// given pattern.
								if (matchRegex(pat, entry)) {
									// if it matches then add it to the hashMap
									// that holds all found files under their
									// directories.
									if (otherStructure.get(directory) == null) {
										LinkedList<String> filesList = new LinkedList<String>();
										otherStructure
												.put(directory, filesList);
									}
									LinkedList<String> filesList = otherStructure
											.get(directory);
									filesList.add(entry);
									otherStructure.put(directory, filesList);
									// otherStructure.put(file.getName(),list);
								}
							}
						}
					} catch (Exception e) {
						System.err.println("Error processing file");
					}
				}
			}

		}
	}

	/*
	 *  ***Method cvtPattern was extracted from the AE2 specification document
	 * section 6***
	 */
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

	// matchRegex returns true if filename matches given regExpression or
	// false otherwise.
	private static boolean matchRegex(Pattern regExpression, String filename) {
		Matcher m = regExpression.matcher(filename);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * The code in processDirectory method is a modified version of the
	 * processDirectory version from the AE2 specification document section 7
	 */

	public static boolean processDirectory(String name,
			LinkedBlockingQueue<String> work) {
		LinkedBlockingQueue<String> q = work;
		try {
			File file = new File(name); // create a File object
			if (file.isDirectory()) { // a directory - could be symlink
				// System.out.println(name);
				String entries[] = file.list();
				if (entries != null) { // not a symlink
					q.add((String) name);
					for (String entry : entries) {
						if (entry.compareTo(".") == 0)
							continue;
						if (entry.compareTo("..") == 0)
							continue;
						processDirectory(new String(name + "/" + entry), q);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error processing " + name + ": " + e);
		}
		return true;

	}

	public static void main(String Arg[]) throws Exception {
		String directory = "";
		String pattern = "";
		fileCrawler crawler = new fileCrawler();
		// If user provides less than 1 argument print error and exit
		if (Arg.length < 1) {
			System.err.println("Usage: ./fileCrawler pattern [dir] ... ");
			System.exit(1);
		}
		// convert and compile the given pattern to Java Regex Pattern
		Pattern pat = cvtPattern(Arg[0]);
		// save given directory name into directory variable
		if (Arg.length == 2) {
			directory = Arg[1];
		} else {// if a directory was not specified by the user
			directory = ".";
		}
		// get environment variable "CRAWLER_THREADS"
		String envThreads = System.getenv("CRAWLER_THREADS");
		Integer numThreads;
		if (envThreads == null)
			// if CRAWLER_THREADS is not set, set number of threads to 2
			numThreads = new Integer(2);
		else
			numThreads = new Integer(envThreads);

		// Create and start workers
		Thread[] threads = new Thread[numThreads];
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new Thread(crawler.createWorker(pat, i));
			threads[i].start();

		}
		processDirectory(directory, crawler.workQueue);// Traverse directories
														// and add them to
														// workQueue/
		// Main thread is no done adding directories
		// to workQueue. Send interrupt signal to let the
		// threads know.
		for (int i = 0; i < numThreads; i++) {
			threads[i].interrupt();
		}

		// Now wait for threads to finish their work.
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (Exception e) {
			}
			;
		}

		// I added all the matched files from the ConcurrentHashMap to the TreeSet
		// which resulted in a sorted TreeSet.
		TreeSet<String> tree = new TreeSet<String>();
		for (String key : crawler.anotherStructure.keySet()) {
			Iterator listIt = crawler.anotherStructure.get(key).iterator();
			while (listIt.hasNext()) {
				tree.add(key + "/" + crawler.anotherStructure.get(key).poll());
			}

		}

		//Iterate over all the elements in the TreeSet and print them.
		Iterator it = tree.iterator();
		if (tree.isEmpty())
			System.out.println("Unable to create iterator over tree set");
		else{while (it.hasNext()) {
			System.out.println(it.next());
		}}
		System.exit(0);
	}
}