package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.

A pair (i, j) is fair if:

0 <= i < j < n, and
lower <= nums[i] + nums[j] <= upper
 

Example 1:

Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
Output: 6
Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).
Example 2:

Input: nums = [1,7,9,2,5], lower = 11, upper = 11
Output: 1
Explanation: There is a single fair pair: (2,3).
 

Constraints:

1 <= nums.length <= 105
nums.length == n
-109 <= nums[i] <= 109
-109 <= lower <= upper <= 109
Accepted
49,084
Submissions
121,791
 * 
 * Nov 12, 2024 - 11:23:24 PM
 * Jojo 
 */
public class COuntTheNumberOfFairPairs {
	public long countFairPairs_bs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        
        long result = 0;
        
        int len = nums.length;
        
        // in a tuple fix one number. Here nums[i] is that number
        for(int i=0; i<len-1; i++){
            
            // choosing the second number index so that the sum is less than lower (thats why reducing 1)
            long lowerSumCount = (long)findCount(nums, lower - nums[i] - 1, i+1, len);
            
            // choosing the second number index so that the sum is less than upper
            long higherSumCount = (long)findCount(nums, upper - nums[i], i+1, len);
            
            // finding the diff, which will satisfy the problem statement. 
            result += Math.max(0, (higherSumCount - lowerSumCount));
        }
        
        return result;
    }
    
    private int findCount(int[] nums, int target, int beg, int end){
        while(beg < end){
            int mid = beg + (end - beg)/2;
            
            //System.out.println("b: " + beg + "  e: " + end + "  m: " + mid);
            
            if(nums[mid] <= target){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
        }
        
        return beg - 1;
    }
    
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        
        // choosing the number of pairs which forms sum less than lower
        long lowerSumCount = findCountUsingTwoPointer(nums, lower - 1);
        
        // choosing the number of pairs which forms sum less than equal to upper
        long higherSumCount = findCountUsingTwoPointer(nums, upper);
        
        
        // returning the diff as mentioned in the problem statement
        return higherSumCount - lowerSumCount;
    }
    
    private long findCountUsingTwoPointer(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        
        long count = 0;
        
        while(left <= right){
            int sum = nums[left]  + nums[right];
            
            if(sum <= target){
                count += (long)(right - left);
                left++;
            }
            else{
                right--;
            }
        }
        
        return count;
    }
}
