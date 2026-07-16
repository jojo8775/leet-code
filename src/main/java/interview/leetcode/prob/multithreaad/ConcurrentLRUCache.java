package interview.leetcode.prob.multithreaad;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLRUCache {

	private int capacity;
	private Node head = null;
	private Node tail = null;

	private ConcurrentLinkedQueue<Node> queue = new ConcurrentLinkedQueue<>();
	private ConcurrentHashMap<Integer, Node> nodeMap = new ConcurrentHashMap<>();
	private AtomicInteger queueSize = new AtomicInteger(0);

	Lock writeLock = new ReentrantLock();

	public ConcurrentLRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		if (!nodeMap.containsKey(key)) {
			return -1;
		}

		Node node = nodeMap.get(key);
		
		// putting it for lazy drain 
		queue.offer(node);
		int size = queueSize.incrementAndGet();
		
		if(size > 64) {
			tryDrainQueue();
		}

		return node.val;
	}

	public void put(int key, int value) {
		writeLock.lock();

		try {
			if (capacity <= 0) {
				return;
			}
			
			if(!queue.isEmpty()) {
				drainQueue();
			}

			if (nodeMap.containsKey(key)) {
				Node node = nodeMap.get(key);
				node.val = value;
				removeNode(node);
				addNode(node);
			} else {
				if (nodeMap.size() == capacity) {
					removeNode(tail);
				}

				Node node = new Node(key, value);
				addNode(node);
			}
		} finally {
			writeLock.unlock();
		}
	}
	
	private void tryDrainQueue() {
		if(writeLock.tryLock()) {
			try {
				drainQueue();
			}
			finally {
				writeLock.unlock();
			}
		}
	}
	
	private void drainQueue() {
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			removeNode(node);
			addNode(node);
			queueSize.decrementAndGet();
		}
	}

	private void addNode(Node node) {
		node.prev = null;
		nodeMap.put(node.key, node);

		if (head != null) {
			head.prev = node;
		}

		node.next = head;
		head = node;

		if (tail == null) {
			tail = head;
		}
	}

	private void removeNode(Node node) {
		nodeMap.remove(node.key);

		if (node.prev != null) {
			node.prev.next = node.next;
		} else {
			head = node.next;
		}

		if (node.next != null) {
			node.next.prev = node.prev;
		} else {
			tail = tail.prev;
		}
	}

	private static class Node {
		int val, key;
		Node next = null, prev = null;

		Node(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}
}
