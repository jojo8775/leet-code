package interview.leetcode.prob;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are given an integer array nums and a positive integer k. You can choose any subsequence of the array and sum all of its elements together.

We define the K-Sum of the array as the kth largest subsequence sum that can be obtained (not necessarily distinct).

Return the K-Sum of the array.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

Note that the empty subsequence is considered to have a sum of 0.

 

Example 1:

Input: nums = [2,4,-2], k = 5
Output: 2
Explanation: All the possible subsequence sums that we can obtain are the following sorted in decreasing order:
- 6, 4, 4, 2, 2, 0, 0, -2.
The 5-Sum of the array is 2.
Example 2:

Input: nums = [1,-2,3,4,-10,12], k = 16
Output: 10
Explanation: The 16-Sum of the array is 10.
 

Constraints:

n == nums.length
1 <= n <= 105
-109 <= nums[i] <= 109
1 <= k <= min(2000, 2n)
Accepted
5,388
Submissions
15,040
 * @author jojo
 * Oct 13, 2022 9:41:01 PM
 */
public class FindTheKSumOfAnArray {
	public long kSum(int[] nums, int k) {
        int len = nums.length;
        
        long totalPositiveSum = 0;
        
        for(int i=0; i<len; i++){
            if(nums[i] > 0){
            	// finding the maximum positive subsequence of an array by just taking 
				// the positive numbers.
                totalPositiveSum += nums[i];
            }
            else{
        		// since subtracting positive number from the max sum is same as removing the negative number 
				// in terms of subsequence sum.
                nums[i] *= -1;
            }
        }
        
		// sorting the array because we are dealing with sum so the order of the element doesn't matter
        Arrays.sort(nums);
        
        // this is a max heap.
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a,b) -> Long.compare(b.sum, a.sum));
        maxHeap.offer(new Pair(0, totalPositiveSum - nums[0]));
        
        while(--k > 0){
            Pair top = maxHeap.poll();
            
            if(top.idx + 1 < len){
            	
            	// this is the formula for decreasing subsequence sum.
            	// this formula is derived from increasing subsequence sum. where sum for next index (i+1) are   
            	// s1 = previousSum + arr[i+1]
            	// s2 = previousSum + arr[i+1] - arr[i]
            	
            	// decreasing subsequece sum is formula is just reverse of increasing formulat. Where sum of next index are 
            	// s1 = maxSumSoFar - arr[i+1];
            	// s2 = maxSumSoFar - arr[i+1] + arr[i];
                maxHeap.offer(new Pair(top.idx + 1, top.sum - nums[top.idx + 1]));
                maxHeap.offer(new Pair(top.idx + 1, top.sum - nums[top.idx + 1] + nums[top.idx]));
            }
            
            totalPositiveSum = top.sum;
        }
        
        return totalPositiveSum;
    }
    
	public long kSum_1(int[] nums, int k) {
		int len = nums.length;

		long maxsum = 0;
		for (int i = 0; i < len; i++) {
			if (nums[i] > 0) {
				// finding the maximum positive subsequence of an array by just taking 
				// the positive numbers. 
				maxsum += (long) nums[i];
			} 
			else {
				// since subtracting positive number from the max sum is same as removing the negative number 
				// in terms of subsequence sum.
				nums[i] = -1 * nums[i]; 
			}
		}

		// sorting the array because we are dealing with sum so the order of the element doesnt matter
		Arrays.sort(nums);

		if (k == 1) {
			return maxsum;
		}

		// this is a min heap 
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a, b) -> (Long.compare(a.sum, b.sum)));
		
		// storing the minimum sum first. 
		pq.add(new Pair(0, nums[0]));
		
		while (--k > 1) {
			Pair top = pq.poll();
			
			if (top.idx + 1 < len) {
				
				// formula for increasing subsequence sum is 
				// s1 = sofarSum + num[i+1],
				// s2 = sofarSum + num[i+1] - num[i],
				// using this formula if we perform this action for each index 
				pq.add(new Pair(top.idx + 1, top.sum + (long) nums[top.idx + 1]));
				pq.add(new Pair(top.idx + 1, top.sum + (long) nums[top.idx + 1] - (long) nums[top.idx]));
			}
		}

		return maxsum - pq.poll().sum;
	}
	
    private static class Pair{
        int idx;
        long sum;
        
        public Pair(int idx, long sum){
            this.idx = idx;
            this.sum = sum;
        }
    }
	
}
