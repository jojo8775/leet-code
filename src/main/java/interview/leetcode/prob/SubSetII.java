package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 * @author jojo
 *
 */
public class SubSetII {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if(nums == null || nums.length == 0){
            return result;
        }
        
        Arrays.sort(nums);
        result.add(new ArrayList<Integer>());
        
        int begin = 0;
        for(int i=0; i<nums.length; i++){
            if(i == 0 || nums[i] != nums[i-1]){
                begin = 0;
            }
            
            int size = result.size();
            for(int j=begin; j<size; j++){
                List<Integer> list = new ArrayList<Integer>(result.get(j));
                list.add(nums[i]);
                result.add(list);
            }
            
            begin = size;
        }
        
        return result;
    }
}
