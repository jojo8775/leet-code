package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * You are given a 0-indexed integer array nums consisting of 3 * n elements.

You are allowed to remove any subsequence of elements of size exactly n from nums. The remaining 2 * n elements will be divided into two equal parts:

The first n elements belonging to the first part and their sum is sumfirst.
The next n elements belonging to the second part and their sum is sumsecond.
The difference in sums of the two parts is denoted as sumfirst - sumsecond.

For example, if sumfirst = 3 and sumsecond = 2, their difference is 1.
Similarly, if sumfirst = 2 and sumsecond = 3, their difference is -1.
Return the minimum difference possible between the sums of the two parts after the removal of n elements.

 

Example 1:

Input: nums = [3,1,2]
Output: -1
Explanation: Here, nums has 3 elements, so n = 1. 
Thus we have to remove 1 element from nums and divide the array into two equal parts.
- If we remove nums[0] = 3, the array will be [1,2]. The difference in sums of the two parts will be 1 - 2 = -1.
- If we remove nums[1] = 1, the array will be [3,2]. The difference in sums of the two parts will be 3 - 2 = 1.
- If we remove nums[2] = 2, the array will be [3,1]. The difference in sums of the two parts will be 3 - 1 = 2.
The minimum difference between sums of the two parts is min(-1,1,2) = -1. 
Example 2:

Input: nums = [7,9,5,8,1,3]
Output: 1
Explanation: Here n = 2. So we must remove 2 elements and divide the remaining array into two parts containing two elements each.
If we remove nums[2] = 5 and nums[3] = 8, the resultant array will be [7,9,1,3]. The difference in sums will be (7+9) - (1+3) = 12.
To obtain the minimum difference, we should remove nums[1] = 9 and nums[4] = 1. The resultant array becomes [7,5,8,3]. The difference in sums of the two parts is (7+5) - (8+3) = 1.
It can be shown that it is not possible to obtain a difference smaller than 1.
 

Constraints:

nums.length == 3 * n
1 <= n <= 105
1 <= nums[i] <= 105
Accepted
6,618
Submissions
14,200
 * @author jojo
 * Oct 27, 2022 8:10:59 PM
 */
public class MinimumDifferenceInSumAfterRemovalOfElements {
	/**
	 *Idea is to remove the max from the first half and min from the second half to make sure that difference is same
	 *removing max from the first half will make sure the first part is smaller than the second half  
	 */
    public long minimumDifference(int[] nums) {
	    int len = nums.length, splitSize = len/3;
	   
	    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);
	    long leftSum = 0;
	   
	    // take example of Arr: [1,2,3  4,5,6  7,8,9]
	    // adding first [1,2,3, (0 .. 2) to the max heap
	    for(int i=0; i<splitSize; i++){
	        leftSum += nums[i];
	        maxHeap.offer(nums[i]);
	    }
	    
	    // storing the min value possible from 3 .. 5 index 
	    long[] leftMinArr = new long[len]; // taking size len for ease of calculation
	    leftMinArr[splitSize - 1] = leftSum;
	    
	    // this is same as sliding window going from index 3 .. 5 
	    for(int i=splitSize; i<splitSize*2; i++){
	        leftSum += nums[i];
	        maxHeap.offer(nums[i]);
	        
	        // taking the max number from the left
	        leftSum -= maxHeap.poll();
	        leftMinArr[i] = leftSum;
	    }
	    
	    PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> a-b);
	    long rightSum = 0;
	    
	    // adding from index 6 .. 8 for the right part  
	    for(int i=len - 1; i >= splitSize * 2; i--){
	        rightSum += nums[i];
	        minHeap.offer(nums[i]);
	    }
	    
	    // calculating the min
	    long result = leftMinArr[2*splitSize - 1] - rightSum;
	    
	    // sliding the window of n (size 3 since 9/3 = 3) from index 5 .. 3 
	    for(int i=(splitSize*2)-1; i>=splitSize; i--){
	        rightSum += nums[i];
	        minHeap.offer(nums[i]);
	        
	        // removing this min from right side
	        rightSum -= minHeap.poll();
	        result = Math.min(result, leftMinArr[i - 1] - rightSum);
	    }
	    
	    return result;
	}
}
