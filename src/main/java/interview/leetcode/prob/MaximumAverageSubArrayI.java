package interview.leetcode.prob;

/**
 * Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.

Example 1:

Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 

Note:

1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].
 * @author jojo
 * Aug 25, 2019 3:20:18 AM
 */
public class MaximumAverageSubArrayI {
    public double findMaxAverage(int[] nums, int k) {
        long sum = 0, max = Long.MIN_VALUE;
        for(int i=0; i<k; i++){
            sum += nums[i];
        }
        
        max = Math.max(sum, max);
        
        for(int i=k; i<nums.length; i++){
            sum += nums[i] - nums[i - k];
            max = Math.max(sum, max);
        }
        
        return (max * 1.0) / k;
    }
}
