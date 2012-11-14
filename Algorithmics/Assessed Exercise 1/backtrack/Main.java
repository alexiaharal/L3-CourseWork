import java.io.*;
import java.util.*;

/**
 program to find shortest path using Dijkstra's algorithm
 */
public class Main {
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		int from;//starting point
		int destination;//destination point

		// read in the data here
		String inputFileName = args[0];
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		String line = in.nextLine();
        Scanner lineScanner = new Scanner(line);
		int numVertices = lineScanner.nextInt();

		// insert code here to build the graph from the input file
		Graph G = new Graph(numVertices);
		for (int i = 0; i < numVertices; i++){
			// update information for vertex with index i
			line = in.nextLine(); // read information
			lineScanner = new Scanner(line);
			int distance;
			for (int j = 0; j < numVertices; j++){
				distance=lineScanner.nextInt();
				if (distance> 0)
					G.getVertex(i).addToAdjList(j,distance);
					}}
		line=in.nextLine();
		lineScanner=new Scanner(line);
		from=lineScanner.nextInt();
		destination=lineScanner.nextInt();
		reader.close();		
		//Find Dijkstra's shortest path from "start" to "destination"
		G.backtrack(from,destination);
		long end = System.currentTimeMillis();

		// end timer and print total time
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");
	}

}