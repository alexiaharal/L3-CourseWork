import java.util.LinkedList;
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

	/**
	 * carry out a Dijkstra's best path search of the graph
	 */
	public void dijkstras(int from, int to) {
		int[] distances = new int[this.size()];
		for (int i = 0; i < distances.length; i++) {
			distances[i] = -1;
		}
		// create a list to hold all the unvisited vertices
		LinkedList<Vertex> unvisited = new LinkedList<Vertex>();
		// create a list to hold all the visited vertices
		LinkedList<Vertex> visited = new LinkedList<Vertex>();
		// add all vertices into the unvisited vertices list
		for (Vertex v : vertices) {
			unvisited.add(v);// set all vertices to be unvisited
		}
		Vertex current = this.getVertex(from);// we start with the starting
												// point.
		distances[from] = 0;// d(from) should apparently be set to 0;
		for (int index = 0; index < distances.length; index++) {
			unvisited.remove(current);// remove starting point from unvisited
										// vertices list.
			visited.add(current);// add it to the visited vertices
			// go through the current vertex's adjacency list
			for (AdjListNode node : current.getAdjList()) {
				if (distances[node.getVertexNumber()] < 0) {// if
															// d(node.getVertexNumber())
															// is not
															// initialized
					distances[node.getVertexNumber()] = distances[current
							.getIndex()] + node.getVertexDistance();// distance
																	// to this
																	// node
																	// is d(u)
																	// of the
																	// calling
																	// vertex
																	// plus
																	// the
																	// distance
																	// between
																	// the
																	// calling
																	// vertex
																	// and this
																	// node.
					vertices[node.getVertexNumber()].setPredecessor(current
							.getIndex());// THIS MIGHT CAUSE PROBLEMS

				} else {// if it already has a distance
					if (distances[node.getVertexNumber()] > (distances[current
							.getIndex()] + node.getVertexDistance())) {
						distances[node.getVertexNumber()] = distances[current
								.getIndex()] + node.getVertexDistance();
						vertices[node.getVertexNumber()].setPredecessor(current
								.getIndex());
					}
				}
			}
			// find next minumum.
			int minimumDistance = Integer.MAX_VALUE;
			for (Vertex v : unvisited) {
				int du = distances[v.getIndex()];// getting the d(u) of each
													// unvisited vertex;
				if (du > -1)// if the d(u) of this node has been initialised
					if (du < minimumDistance) {
						minimumDistance = du;
						current = v;
					}
			}
		}
		if (distances[to] < 0)
			System.out.println("No possible path found from vertex " + from
					+ " to vertex " + to);
		else {
			System.out.println("Shortest distance from vertex " + from
					+ " to vertex " + to + " is: " + distances[to]);
			LinkedList<Integer> path = new LinkedList<Integer>();
			int destination = to;// start reading predecessors from destination
									// vertex
			path.add(vertices[to].getIndex());// add destination vertex to the
												// path
			while (destination != from) {
				path.add(vertices[destination].getPredecessor());
				destination = vertices[destination].getPredecessor();
			}
			System.out.print("Shortest path: ");
			for (int i = path.size() - 1; i > -1; i--)
				System.out.print(path.get(i) + " ");
		}
	}
}