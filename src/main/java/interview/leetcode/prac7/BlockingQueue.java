package interview.leetcode.prac7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {

	Object[] container;
	int count = 0;
	int idx = 0;

	ReentrantLock lock = new ReentrantLock();
	Condition notFull = lock.newCondition();
	Condition notEmpty = lock.newCondition();

	BlockingQueue(int capacity) {
		container = new Object[capacity];
	}

	public void offer(T val) throws InterruptedException {
		if (lock.tryLock() == false) {
			return;
		}

		try {
			while (count == container.length) {
				notFull.await();
			}

			container[idx] = val;
			idx++;
			idx = idx % container.length;

			count++;

			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public T poll() throws InterruptedException {
		if (lock.tryLock() == false) {
			return null;
		}

		try {
			while (count == 0) {
				notEmpty.await();
			}

			T result = (T) container[idx];
			idx--;
			count--;

			notFull.signal();
			return result;
		} finally {
			lock.unlock();
		}

	}
}
