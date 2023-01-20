package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements. You may return the answer in any order.

 

Example 1:

Input: nums = [4,6,7,7]
Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
Example 2:

Input: nums = [4,4,3,2,1]
Output: [[4,4]]
 

Constraints:

1 <= nums.length <= 15
-100 <= nums[i] <= 100
Accepted
93.9K
Submissions
175.1K
 * @author jojo
 * Jan 19, 2023 8:01:10 PM
 */
public class NonDecreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        
        backtrack(nums, 0, list, result);
        
        return new ArrayList<>(result);
    }
    
    private void backtrack(int[] nums, int idx, List<Integer> list, Set<List<Integer>> result){
        if(idx == nums.length){
            if(list.size() > 1){
                result.add(new ArrayList<>(list));
            }
            
            return;
        }
        
        if(list.isEmpty() || list.get(list.size() - 1) <= nums[idx]){
            list.add(nums[idx]);
            backtrack(nums, idx + 1, list, result);
            list.remove(list.size() - 1);
        }
        
        backtrack(nums, idx + 1, list, result);
    }
}
