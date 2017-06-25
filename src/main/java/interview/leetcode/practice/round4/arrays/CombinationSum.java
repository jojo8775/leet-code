package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<Integer>(), candidates, target, 0);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> list, int[] candidates, int target, int idx){
        if(target == 0){
            result.add(new ArrayList<>(list));
            return;
        }
        
        if(target < 0){
            return;
        }
        
        for(int i=idx; i<candidates.length; i++){
            list.add(candidates[i]);
            backtrack(result, list, candidates, target - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }
}
