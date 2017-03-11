package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
 * @author jojo
 *
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, 0, new ArrayList<Integer>(), result);
        return result;
    }
    
    private void dfs(int[] candidates, int target, int sum, int idx, List<Integer> entry, List<List<Integer>>result){    
        if(sum == target){
            result.add(new ArrayList<Integer>(entry));
            return;
        }
        
        if(idx == candidates.length){
            return;
        }
        
        for(int i=idx; i<candidates.length; i++){
            // skipping the duplicates
            if(i > idx && candidates[i]  == candidates[i - 1]){
                continue;
            }
            
            if(sum + candidates[i] <= target){
                entry.add(candidates[i]);
                dfs(candidates, target, sum + candidates[i], i+1, entry, result);
                entry.remove(entry.size() - 1);
            }
            else{
                break;
            }
        }
    }
}
