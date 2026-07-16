package interview.leetcode.prob.multithreaad;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MultiThreadProcessor {
	
	private int threadCount;
	private BlockingQueue<String> queue = new LinkedBlockingQueue<>();
	private volatile boolean shutdownTriggered = false;
	
	private final Thread[] workers;

	public MultiThreadProcessor(int threadCount) {
		this.threadCount = threadCount;
		this.workers = new Thread[threadCount];
	}
	
	public void start() {
		for(int i=0; i<threadCount; i++) {
			workers[i] = new Thread(this::workerLoop, "w - " + i);
			workers[i].start();
		}
	}
	
	public void submit(String value) {
		if (shutdownTriggered) {
            System.out.println("Rejected (shutting down): " + value);
        }
        queue.offer(value);
	}
	
	public void shutdown() throws InterruptedException {
		shutdownTriggered = true;                 // API stops accepting new strings now
        for (Thread worker : workers) {
            worker.join();                   // blocks until that worker's loop returns
        }
        System.out.println("All workers drained the queue and exited.");
	}
	
	private void workerLoop() {
		String threadName = Thread.currentThread().getName();
		
		while(true) {
			String item;
			
			try {
				item = queue.poll(200, TimeUnit.MILLISECONDS);
			}
			catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
				return;
			}
			
			if(item != null) {
				process(item);
			}
			else if(shutdownTriggered == true && queue.size() == 0) {
				System.out.println(threadName + " drained the queue, exiting.");
                return;
			}
		}
	}
	
	private void process(String item) {
		System.out.println(Thread.currentThread().getName() + " processing: " + item);
		try {
	        Thread.sleep(1000);   // simulate 1 second of work
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();   // restore the interrupt flag; don't swallow it
	    }
	}
	
}
