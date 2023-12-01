package interview.leetcode.prob;

import java.util.Arrays;

/**
 * You are given a 0-indexed integer array nums and an integer p. Find p pairs of indices of nums such that the maximum difference amongst all the pairs is minimized. Also, ensure no index appears more than once amongst the p pairs.

Note that for a pair of elements at the index i and j, the difference of this pair is |nums[i] - nums[j]|, where |x| represents the absolute value of x.

Return the minimum maximum difference among all p pairs. We define the maximum of an empty set to be zero.

 

Example 1:

Input: nums = [10,1,2,7,1,3], p = 2
Output: 1
Explanation: The first pair is formed from the indices 1 and 4, and the second pair is formed from the indices 2 and 5. 
The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1. Therefore, we return 1.
Example 2:

Input: nums = [4,2,1,2], p = 1
Output: 0
Explanation: Let the indices 1 and 3 form a pair. The difference of that pair is |2 - 2| = 0, which is the minimum we can attain.
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 109
0 <= p <= (nums.length)/2
Accepted
65,304
Submissions
147,727
 * @author jojo
 * Nov. 27, 2023 11:00:02 p.m.
 */
public class MinimizeTheMaximumDifferenceOfPairs {
	public int minimizeMax(int[] nums, int p) {
        // sorting to make sure the diff between adjacent elements are minimum.
        Arrays.sort(nums);
        
        int len = nums.length, left = 0, right = nums[len - 1] - nums[0];
        
        // left is the minimum diff = 0, right is max diff = last element - first element.
        while(left < right){
            int mid = left + (right - left) / 2;
            
            int validPairs = findValidPairs(nums, mid);
            
            // if there are less pairs than expected, need to increase the threshold. 
            if(validPairs < p){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        
        return left;
    }
    
    private int findValidPairs(int[] nums, int threshold){
        int len = nums.length, idx = 0, pairCount = 0;
        
        while(idx < len - 1){
            if(nums[idx + 1] - nums[idx] <= threshold){
                pairCount++;
                
                // skipping both numbers when a valid pair is found.
                idx++;
            }
            
            idx++;
        }
        
        return pairCount;
    }
}
