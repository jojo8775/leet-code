package interview.leetcode.prob;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

Accepted
444,377
Submissions
1,046,686
 * @author jojo
 * Jan 30, 2019 9:28:09 PM
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE, curSum = 0;
        
        for(int num : nums){
            // if the current sum is less than 0 then it is always better to
            // refresh with the current number.
            if(curSum < 0){
                curSum = num;
            }
            else{
                curSum += num;
            }
            
            maxSum = Math.max(curSum, maxSum);
        }
        
        return maxSum;
    }
    
    public int maxSubArray_DC(int[] nums) {
        return divideAndConcur(nums, 0, nums.length - 1);
    }
    
    private int divideAndConcur(int[] arr, int beg, int end){
        if(beg > end){
            return Integer.MIN_VALUE;
        }
        
        int mid = beg + (end - beg)/2;
        
        int leftSumMax = 0, rightSumMax = 0, cur = 0;
        for(int i=mid-1; i>=beg; i--){
            cur += arr[i];
            leftSumMax = Math.max(leftSumMax, cur);
        }
        
        cur = 0;
        for(int i=mid+1; i<=end; i++){
            cur += arr[i];
            rightSumMax = Math.max(rightSumMax, cur);
        }
        
        int curMax = leftSumMax + rightSumMax + arr[mid];
        
        int leftMax = divideAndConcur(arr, beg, mid - 1);
        int rightMax = divideAndConcur(arr, mid + 1, end);
        
        return Math.max(curMax, Math.max(leftMax, rightMax));
    }
}
