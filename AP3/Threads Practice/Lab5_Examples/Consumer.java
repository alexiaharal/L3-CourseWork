
public class Consumer implements Runnable{
	
	private String name;
	private BoundedBuffer<String> bb;

	public Consumer( int i, BoundedBuffer<String> bb) {
		this.name="Consumer"+i;
		this.bb=bb;
	}

	public void run(){
		System.out.println("Started "+name);
		while (true){
			String s;
			try{
				s=bb.get();
			}catch (InterruptedException e){
				System.out.println(name+" has been interrupted");
				return;
			}
			System.out.println(name+" received "+s);
		}
	}




}
