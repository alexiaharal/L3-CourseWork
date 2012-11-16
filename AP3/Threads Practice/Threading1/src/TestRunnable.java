public class TestRunnable implements Runnable{
	public void run(){
		for(int i=0;i<10;i++){
		System.out.println("In Thread"+i);
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		}}
	
	public static void main(String[] args){
		Thread t=new Thread(new TestRunnable());
		t.start();
		t.interrupt();
	}
}