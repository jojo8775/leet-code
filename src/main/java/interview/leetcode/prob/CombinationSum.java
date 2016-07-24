package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]
 * @author jojo
 *
 */
public class CombinationSum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        dfs(result, new ArrayList<Integer>(), target, 0, candidates);
        
        return result;    
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> list, int target, int idx, int[] candidates){
        if(idx == candidates.length){
            return;
        }
        
        if(target == 0){
            result.add(new ArrayList<Integer>(list));
        }
        
        for(int i=idx; i<candidates.length; i++){
            if(target - candidates[i] >= 0){
                list.add(candidates[i]);
                dfs(result, list, target - candidates[i], i, candidates);
                list.remove(list.size() - 1);
            }
        }
    }
    
    public static void main(String[] args){
    	int[] candidates = {2,3,6,7};
    	List<List<Integer>> result = new CombinationSum().combinationSum(candidates, 7);
    	
    	System.out.println(result.size());
    }
}
