package interview.leetcode.prob.multithreaad;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueCustom {
	ReentrantLock lock = new ReentrantLock();
	Condition notEmpty = lock.newCondition();
	Condition notFull = lock.newCondition();
	
	Queue<Integer> queue = new LinkedList<>();
	int capacity;
	
	public BlockingQueueCustom(int capacity) {
		this.capacity = capacity;
	}
	
	public void offer(int val) throws InterruptedException {
		lock.lock();
		
		try {
			while(queue.size() == capacity) {
				notFull.await();
			}
			
			queue.offer(val);
			notEmpty.signal();
		}
		finally {
			lock.unlock();
		}
	}
	
	public int poll() throws InterruptedException {
		lock.lock();
		
		try {
			while(queue.isEmpty()) {
				notEmpty.await(); 
			}
			
			int val = queue.poll();
			notFull.signal();
			
			return val; 
		}
		finally {
			lock.unlock();
		}
	}
	
	public int peek() {
		lock.lock();
		
		try {
			return queue.peek();
		}
		finally {
			lock.unlock();
		}
	}
}
