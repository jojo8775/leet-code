package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2
 * @author jojo
 *
 */
public class FindMedianFromDataStream {
	private PriorityQueue<Integer> maxHeap, minHeap;
    int size = 0;
    public FindMedianFromDataStream(){
        maxHeap = new PriorityQueue<Integer>((a,b) -> b - a);
        minHeap = new PriorityQueue<Integer>((a,b) -> a - b);
    }

    // Adds a number into the data structure.
    public void addNum(int num) {
    	size++;
        minHeap.offer(num);
        if(minHeap.size() - maxHeap.size() > 1){
            maxHeap.offer(minHeap.poll());
        }
        
        if(!maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()){
            minHeap.offer(maxHeap.poll());
            maxHeap.offer(minHeap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(size % 2 == 0){
            return (((double) minHeap.peek() + (double) maxHeap.peek())/2);
        }
        
        return (double) minHeap.peek();
    }
    
    public static void main(String[] args){
    	FindMedianFromDataStream ds = new FindMedianFromDataStream();
    	ds.addNum(-1);
    	ds.addNum(-2);
    	ds.addNum(-3);
    	System.out.println(ds.findMedian());
    	ds.addNum(-4);
    	System.out.println(ds.findMedian());
    	ds.addNum(-5);
    	System.out.println(ds.findMedian());
    }
}
