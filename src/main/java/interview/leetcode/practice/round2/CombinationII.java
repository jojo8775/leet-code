package interview.leetcode.practice.round2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<Integer>(), candidates, 0, new boolean[candidates.length], 0, target);
        
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> list, int[] candidates, int idx, boolean[] visited, int sum, int target){
        if(sum > target){
            return;
        }
        
        if(target == sum){
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        for(int i = idx; i<candidates.length; i++){
            if(i > 0 && candidates[i] == candidates[i-1] && !visited[i-1]){
                continue;
            }
            
            if(sum + candidates[i] > target){
                break;
            }
            
            visited[i] = true;
            list.add(candidates[i]);
            dfs(result, list, candidates, i + 1, visited, sum + candidates[i], target);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
