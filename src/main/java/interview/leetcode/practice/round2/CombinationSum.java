package interview.leetcode.practice.round2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        
        dfs(result, new ArrayList<Integer>(), candidates, 0, 0, target);
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> list, int[] candidates, int idx, int sum, int target){
        if(target == sum){
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        for(int i=idx; i<candidates.length; i++){
            if(i > 0 && candidates[i-1] == candidates[i]){
                continue;
            }
            
            if(sum + candidates[i] <= target){
                list.add(candidates[i]);
                dfs(result, list, candidates, i, sum + candidates[i], target);
                list.remove(list.size() - 1);
            }
            else {
                break;
            }
        }
    }
}
