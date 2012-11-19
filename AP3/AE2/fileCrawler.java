import java.util.concurrent.*;
import java.util.regex.*;
import java.util.*;
import java.io.*;

public class fileCrawler {

/*
 * routine to convert bash pattern to regex pattern
 * 
 * e.g. if bashpat is "*.c", pattern generated is "^.*\.c$"
 *      if bashpat is "a.*", pattern generated is "^a\..*$"
 *
 * i.e. '*' is converted to ".*"
 *      '.' is converted to "\."
 *      '?' is converted to "."
 *      '^' is put at the beginning of the regex pattern
 *      '$' is put at the end of the regex pattern
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
		switch(bashpat.charAt(i)) {
			case '*': pat.append('.'); pat.append('*'); break;
			case '.': pat.append('\\'); pat.append('.'); break;
			case '?': pat.append('.'); break;
			default: pat.append(bashpat.charAt(i)); break;
		}
	}
	pat.append('$');
	Pattern p=Pattern.compile(new String(pat));
	return p;
}

public fileCrawler(){
	//Using LinkedBlockingQueue to make it safe for threads to 
	//queue/dequeue elements concurrently
	LinkedBlockingQueue<String> workQueue = new LinkedBlockingQueue<String>();

}


private static void matchRegex(Pattern regExpression, String filename){
	Matcher m=regExpression.matcher(filename);
	if(m.matches()){
		System.out.println("It matches bitch!");
	}else{
		System.out.println("No match");
	}
}
public static void main(String Arg[]){
	String directory="";
	String pattern="";
	if (Arg.length<2){
		System.err.println("Usage: ./fileCrawler pattern [dir] ... " );
		System.exit(1);
	}
	//convert the given pattern to Java Regex
	Pattern pat=cvtPattern(Arg[0]);
	matchRegex(pat,"cis.java");

	System.exit(0);
}
}