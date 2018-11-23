package interview.leetcode.practice.round4.queue;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<Integer>(), candidates, target, 0);
        
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> list, int[] candidates, int target, int idx){
        if(idx == candidates.length){
            return;
        }
        
        if(target == 0){
            result.add(new ArrayList<>(list));
            return;
        }
        
        for(int i=idx; i<candidates.length; i++){
            if(target - candidates[i] >= 0){
                list.add(candidates[i]);
                dfs(result, list, candidates, target - candidates[i], i);
                list.remove(list.size() - 1);
            }
        }
    }
    
    public static void main(String[] args) {
    	List<List<Integer>> result = new CombinationSum().combinationSum(new int[] {2, 3,6,7}, 7);
    	result.forEach(e1 -> {
    		e1.forEach( e2 -> System.out.print(e2 + ",") );
    		System.out.println();
    	});
    }
}
