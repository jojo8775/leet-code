package interview.leetcode.practice.round4;

import java.util.PriorityQueue;

public class Practice {
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);
	private PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> a-b);
	private int size = 0;
	
	public void add(int val) {
		size++;
		
		minHeap.offer(val);
		if(minHeap.size() > maxHeap.size() + 1) {
			maxHeap.offer(minHeap.poll());
		}
		
		if(!maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
			minHeap.offer(maxHeap.poll());
			maxHeap.offer(minHeap.poll());
		}
	}
	
	private double findMedian() {
		if(minHeap.isEmpty()) {
			return -1.0;
		}
		
		double val = minHeap.peek();
		if(size%2 == 0) {
			val += maxHeap.peek();
			val /= 2.0;
		}
		
		return val;
	}
}
