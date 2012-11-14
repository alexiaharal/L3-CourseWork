import java.lang.Integer;

/**
 * class to represent an undirected graph using adjacency lists
 */
public class Graph {

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices = 0; // number of vertices

	// possibly other fields representing properties of the graph

	/**
	 * creates a new instance of Graph with n vertices
	 */
	public Graph(int n) {
		numVertices = n;
		vertices = new Vertex[n];
		for (int i = 0; i < n; i++)
			vertices[i] = new Vertex(i);
	}

	public int size() {
		return numVertices;
	}

	public Vertex getVertex(int i) {
		return vertices[i];
	}

	public void setVertex(int i) {
		vertices[i] = new Vertex(i);
	}

	public void backtrack(int from, int to) {
		Path currentPath = new Path();
		Path bestPath = new Path();
		bestPath.setDistance(Integer.MAX_VALUE);
		this.vertices[from].setVisited(true);
		bestPath.add(this.getVertex(from).getIndex(), 0);// add starting vertex
															// to best path.
		currentPath.add(this.getVertex(from).getIndex(), 0);
		tryNextCandidate(currentPath.getSize(), currentPath, bestPath, to);
		if (bestPath.getPath().size() < 2) {
			System.out.println("No path found from vertex " + from
					+ " to vertex " + to);
		} else {
			System.out.println("Shortest distance from vertex " + from
					+ " to vertex " + to + " is " + bestPath.getDistance());
			System.out.println("Shortest path:" + bestPath.toString());
		}
	}

	private void tryNextCandidate(int n, Path currentPath, Path bestPath, int to) {
		for (AdjListNode node : vertices[currentPath.getPath().get(n - 1)]
				.getAdjList()) {// for every adj list node of the unvisited
			// vertex
			if (!vertices[node.getVertexNumber()].visited) {// if this vertex is
															// not visited
				currentPath.add(node.getVertexNumber(),
						node.getVertexDistance());// add it to currentPath
				this.vertices[node.getVertexNumber()].setVisited(true);// mark
																		// vertex
																		// as
																		// visited

				if (currentPath.getDistance() < bestPath.getDistance()) {
					if (node.getVertexNumber() == to) {
						bestPath.setPath(currentPath);
						bestPath.setDistance(currentPath.getDistance());
					} else {
						tryNextCandidate(currentPath.getSize(), currentPath,
								bestPath, to);
					}

				}
				currentPath.remove(node.getVertexDistance());
				this.vertices[node.getVertexNumber()].setVisited(false);
			}

		}
	}
}