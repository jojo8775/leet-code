package interview.leetcode.prob.multithreaad;

public class BlockingQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueueCustom queue = new BlockingQueueCustom(5);
		
		System.out.println("ssss");
		
		Runnable producer = () -> {
			String name = Thread.currentThread().getName();
			
			for(int i=0; i<10; i++) {
				try {
					queue.offer(i);
					System.out.println(name + " produced " + i);
					
				}
				catch(InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
			}
		};
		
		Runnable consumer = () -> {
			String name = Thread.currentThread().getName();
			
			while(true) {
				try {
					int val = queue.poll();
					System.out.println(name + "  consumed " + val);
				}
				catch(InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
			}
		};
		
		Thread p1 = new Thread(producer, "p - 1");
		Thread p2 = new Thread(producer, "p - 2");
		
		Thread c1 = new Thread(consumer, "c - 1");
		Thread c2 = new Thread(consumer, "c - 2");
		Thread c3 = new Thread(consumer, "c - 3");
		
		p1.start(); p2.start(); 
		
		c1.start(); c2.start(); c3.start();
		
		p1.join(); p2.join();
		
		Thread.sleep(500);
		
		c1.interrupt();
		c2.interrupt();
		c3.interrupt();
		
	}
}
