package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a 0-indexed integer array nums and a target element target.

A target index is an index i such that nums[i] == target.

Return a list of the target indices of nums after sorting nums in non-decreasing order. If there are no target indices, return an empty list. The returned list must be sorted in increasing order.

 

Example 1:

Input: nums = [1,2,5,2,3], target = 2
Output: [1,2]
Explanation: After sorting, nums is [1,2,2,3,5].
The indices where nums[i] == 2 are 1 and 2.
Example 2:

Input: nums = [1,2,5,2,3], target = 3
Output: [3]
Explanation: After sorting, nums is [1,2,2,3,5].
The index where nums[i] == 3 is 3.
Example 3:

Input: nums = [1,2,5,2,3], target = 5
Output: [4]
Explanation: After sorting, nums is [1,2,2,3,5].
The index where nums[i] == 5 is 4.
Example 4:

Input: nums = [1,2,5,2,3], target = 4
Output: []
Explanation: There are no elements in nums with value 4.
 

Constraints:

1 <= nums.length <= 100
1 <= nums[i], target <= 100
Accepted
7,612
Submissions
8,672
 * @author jojo
 * Nov 28, 2021 1:33:49 PM
 */
public class FindTargetIndicesAfterSortingArray {
    public List<Integer> targetIndices(int[] nums, int target) {
        int count = -1, hit = 0;
        
        List<Integer> result = new ArrayList<>();
        for(int n : nums){
            if(n < target){
                count++;
            }
            else if(n == target){
                hit++;
            }
        }
        
        while(hit-- > 0){
            result.add(++count);
        }
        
        return result;
    }
}
