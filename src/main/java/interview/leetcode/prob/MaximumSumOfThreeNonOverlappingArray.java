package interview.leetcode.prob;

/**
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:

Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 

Note:

nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).
 

Accepted
28,243
Submissions
63,628
 * @author jojo
 * Sep 15, 2019 12:56:06 AM
 */
public class MaximumSumOfThreeNonOverlappingArray {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        
        // stores the k window sum
        int[] prefixSum = new int[nums.length - k + 1];
        
        int sum = 0;
        for(int i=0; i<nums.length; i++){
            sum += nums[i];
            
            // + 1 because k is 1 based and i is zero based. 
            if(i - k + 1 >= 0){
                prefixSum[i - k + 1] = sum;
                sum -= nums[i - k + 1];
            }
        }
        
        // these arrays stores the largest prefix sum index at any given time.
        int[] leftMax = new int[prefixSum.length], rightMax =  new int[prefixSum.length];
        
        // finding max array from left
        int leftMaxIdx = 0;
        for(int i=0; i<prefixSum.length; i++){
            if(prefixSum[leftMaxIdx] < prefixSum[i]){
                leftMaxIdx = i;
            }
            
            leftMax[i] = leftMaxIdx;
        }
        
        // finding max array from right
        int rightMaxIdx = prefixSum.length - 1;
        for(int i=prefixSum.length - 1; i>=0; i--){
            // since need to print the leftmost result when there are more than one answere. 
            if(prefixSum[rightMaxIdx] <= prefixSum[i]){
                rightMaxIdx = i;
            }
            
            rightMax[i] = rightMaxIdx;
        }
        
        int[] result = {-1,-1,-1};
        
        for(int i=k; i<prefixSum.length-k; i++){
            int leftIdx = leftMax[i-k];
            int rightIdx = rightMax[i + k];
            
            if(result[0] == -1 || (prefixSum[leftIdx] + prefixSum[rightIdx] + prefixSum[i] > prefixSum[result[0]] + prefixSum[result[1]] + prefixSum[result[2]])){
                result[0] = leftIdx;
                result[1] = i;
                result[2] = rightIdx;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	System.out.println("checking compilation");
    }
}
