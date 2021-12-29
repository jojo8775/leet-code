package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * You are given an integer array nums and an integer k. Find the largest even sum of any subsequence of nums that has a length of k.

Return this sum, or -1 if such a sum does not exist.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: nums = [4,1,5,3,1], k = 3
Output: 12
Explanation:
The subsequence with the largest possible even sum is [4,5,3]. It has a sum of 4 + 5 + 3 = 12.
Example 2:

Input: nums = [4,6,2], k = 3
Output: 12
Explanation:
The subsequence with the largest possible even sum is [4,6,2]. It has a sum of 4 + 6 + 2 = 12.
Example 3:

Input: nums = [1,3,5], k = 1
Output: -1
Explanation:
No subsequence of nums with length 1 has an even sum.
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 105
1 <= k <= nums.length
Accepted
434
Submissions
1,055
 * @author jojo
 * Dec 22, 2021 11:03:17 PM
 */
public class SubsequenceOfSizeKWithLargestEvenSum {
    public long largestEvenSum(int[] nums, int k) {
        // using priority queue to get the max K numbers
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a-b);
        int len = nums.length;
        
        // using these to track the max odd and max even outside of the K max numbers 
        int maxOdd = -1, maxEven = -1;
        
        for(int i=0; i<len; i++){
            pq.offer(nums[i]);
            
            if(i < k){
                continue;
            }
            
            int val = pq.poll();
            
            // traking the max odd and max even outside the K max numbers 
            if(val % 2 == 0){
                maxEven = Math.max(maxEven, val);
            }
            else{
                maxOdd = Math.max(maxOdd, val);
            }
        }
        
        int minEven = Integer.MAX_VALUE, minOdd = Integer.MAX_VALUE;
        long sum = 0;

        // finding sum, min and max numbers from the selected K numbers 
        while(!pq.isEmpty()){
            int top = pq.poll();
            sum += top;
            
            if(top % 2 == 1){
                minOdd = Math.min(minOdd, top);
            }
            else{
                minEven = Math.min(minEven, top);
            }
        }
        
        // if sum of K max numbers are even then we got the result.
        if(sum % 2 == 0){
            return sum;
        }
        
        long m1 = -1, m2 = -1;
        
        // replacing the min even number from K with max odd number outside K
        if(minEven != Integer.MAX_VALUE && maxOdd != -1){
            m1 = sum - minEven + maxOdd;
        }
        
        // replacing the min odd number from K with max even number outside K
        if(minOdd != Integer.MAX_VALUE && maxEven != -1){
            m2 = sum - minOdd + maxEven;
        }
        
        return Math.max(m1, m2);
    }
}
