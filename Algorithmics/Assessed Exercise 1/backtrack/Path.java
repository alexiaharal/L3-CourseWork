import java.util.LinkedList;


public class Path {
	LinkedList<Integer> path;
	int distance;
	public Path(){
		this.path=new LinkedList<Integer>();
		this.distance=0;
	}
	
	public void setPath(Path current){
		path.clear();
		for (int i:current.path){
			this.path.add(i);
		}
	}
	public LinkedList<Integer> getPath() {
		return path;
	}
	
	public void add(int i, int dist){
		this.path.add(i);
		this.distance+=dist;
		
	}
	public void setPath(LinkedList<Integer> path) {
		this.path = path;
	}

	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getSize() {
		return path.size();
	}
	
	public void remove(int dist){
		path.removeLast();
		distance=(distance-dist);
	}
	
	public String toString(){
		String stringPath="";
		for (int i:path){
			stringPath+=" "+i;
		}
		return stringPath;
	}
}
