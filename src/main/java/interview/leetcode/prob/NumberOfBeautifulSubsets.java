package interview.leetcode.prob;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array nums of positive integers and a positive integer k.

A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.

Return the number of non-empty beautiful subsets of the array nums.

A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.

 

Example 1:

Input: nums = [2,4,6], k = 2
Output: 4
Explanation: The beautiful subsets of the array nums are: [2], [4], [6], [2, 6].
It can be proved that there are only 4 beautiful subsets in the array [2,4,6].
Example 2:

Input: nums = [1], k = 1
Output: 1
Explanation: The beautiful subset of the array nums is [1].
It can be proved that there is only 1 beautiful subset in the array [1].
 

Constraints:

1 <= nums.length <= 20
1 <= nums[i], k <= 1000
Accepted
19,000
Submissions
59,488
 * 
 * Author: jojo
 * Dec. 31, 2023 - 11:57:49 a.m.
 */
public class NumberOfBeautifulSubsets {
	public int beautifulSubsets(int[] nums, int k) {
        // this is optional. If this is not done, then we need to n + k and n - k lookup. 
        // this is because all the numbers are always positive. 
        Arrays.sort(nums);
        Map<Integer, Integer> selected = new HashMap<>();
        
        int result = backTrack(nums, k, selected, 0);
        
        // -1 because the index is 0 based in the backtrack logic.
        return result - 1;
    }
    
    private int backTrack(int[] nums, int k, Map<Integer, Integer> selected, int idx){
        if(idx == nums.length){
            // since all single element array are valid returning 1
            return 1;
        }
        
        // without selecting 
        int result = backTrack(nums, k, selected, idx + 1);
        
        // with selecting if qualified.
        if(!selected.containsKey(nums[idx] - k)){
            selected.put(nums[idx], selected.getOrDefault(nums[idx], 0) + 1);
            result += backTrack(nums, k, selected, idx + 1);
            
            selected.put(nums[idx], selected.get(nums[idx]) - 1);
            int val = selected.get(nums[idx]);
            if(val == 0){
                selected.remove(nums[idx]);
            }
        }
        
        return result;
    }
}
