package interview.leetcode.prob;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.

A subarray is a contiguous part of an array.

 

Example 1:

Input: nums = [1], k = 1
Output: 1
Example 2:

Input: nums = [1,2], k = 4
Output: -1
Example 3:

Input: nums = [2,-1,2], k = 3
Output: 3
 

Constraints:

1 <= nums.length <= 105
-105 <= nums[i] <= 105
1 <= k <= 109
Accepted
117,194
Submissions
430,141
 * Nov 16, 2024 - 9:26:47 PM
 * Jojo 
 */
public class ShortestSubarrayWithSumAtLeastK {
	// sliding window using Priority Queue
    public int shortestSubarray_1(int[] nums, int k) {
        PriorityQueue<Long[]> pq = new PriorityQueue<>((a,b) -> Long.compare(a[0],b[0]));
        pq.offer(new Long[]{0L, -1L});
        
        long sum = 0;
        
        int len = Integer.MAX_VALUE;
        
        for(int i=0; i<nums.length; i++){
            sum += (long)nums[i];
            
            while(!pq.isEmpty() && sum - pq.peek()[0] >= (long) k){
                Long[] top = pq.poll();
                
                len = Math.min(len, (int)(i - top[1]));
            }
            
            pq.offer(new Long[]{sum, (long)i});
        }
        
        return len == Integer.MAX_VALUE ? -1 : len;
    }
    
    public int shortestSubarray(int[] nums, int k) {
        long[] prefixSum = new long[nums.length + 1];
        
        for(int i=0; i<nums.length; i++){
            prefixSum[i+1] = nums[i] + prefixSum[i];
        }
        
        Deque<Integer> deque = new LinkedList<>();
        
        int result = Integer.MAX_VALUE;
        
        for(int i=0; i<prefixSum.length; i++){
            // this is the slidind window, where if the left end meets the condition
            // time to increase the left index
            while(!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k){
                result = Math.min(result, (i - deque.pollFirst()));
            }
            
            // when the array prefix sum is decreasing, this means -ve elements are being counted. 
            // given and index i and j where i > j and prefix[i] < prefix[j] it will never make K 
            // as a sub sum. So need to drop all of those. 
            while(!deque.isEmpty() && prefixSum[deque.peekLast()] >= prefixSum[i]){
                deque.pollLast();
            }
            
            deque.offerLast(i);
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
