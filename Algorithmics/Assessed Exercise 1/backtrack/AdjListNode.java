

/**
 class to represent an entry in the adjacency list of a vertex
 in a graph
*/
public class AdjListNode {

	private int vertexNumber;
	private int vertexDistance;
	// could be other fields, for example representing
	// properties of the edge - weight, capacity, . . .
	
    /* creates a new instance */
	public AdjListNode(int n,int d){
		vertexNumber = n;
		vertexDistance=d;
	}
	
	public int getVertexNumber(){
		return vertexNumber;
	}
	
	public void setVertexNumber(int n){
		vertexNumber = n;
	}
	
	public int getVertexDistance(){
		return vertexDistance;
	}
	
	public void setVertexDistance(int n){
		vertexDistance = n;
	}
}