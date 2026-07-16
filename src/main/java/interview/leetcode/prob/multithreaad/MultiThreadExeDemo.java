package interview.leetcode.prob.multithreaad;

public class MultiThreadExeDemo {
	public static void main(String[] args) throws InterruptedException {
		MultiThreadProcessor p = new MultiThreadProcessor(5);
		p.start();
		
		for(int i=0; i<500; i++) {
			p.submit("jobid - " + i);
		}
		
		System.out.println("producer is emptied.");
		p.shutdown();
		
	}
}
