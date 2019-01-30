package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
Note: 
You may assume that nums' length ≥ k-1 and k ≥ 1.
 * @author jojo
 * Jan 16, 2019 11:03:06 PM
 */
public class KthLargestElementInAStream {
	class KthLargest {

	    private PriorityQueue<Integer> pq;
	    private int k;
	    
	    public KthLargest(int k, int[] nums) {
	    	// this is max heap
	        pq = new PriorityQueue<>((a,b) -> a - b);
	        this.k = k;
	        
	        for(int num : nums){
	            insert(num);
	        }
	    }
	    
	    public int add(int val) {
	        return insert(val);
	    }
	    
	    private int insert(int num){
	        if(pq.size() < k){
	            pq.offer(num);
	        }
	        else if(pq.peek() < num){
	            pq.poll();
	            pq.offer(num);
	        }
	        
	        return pq.peek();
	    }
	}
}
