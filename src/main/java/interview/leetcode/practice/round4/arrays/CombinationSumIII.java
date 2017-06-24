package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backTrack(result, new ArrayList<Integer>(), k, n, 1, 0);
        return result;
    }
    
    private void backTrack(List<List<Integer>> result, List<Integer> list, int k, int n, int idx, int sum){
        if(list.size() == k && sum == n){
            result.add(new ArrayList<>(list));
            return;
        }
        
        if(sum > n || list.size() > k){
            return;
        }
        
        for(int i=idx; i<=9; i++){
            list.add(i);
            backTrack(result, list, k, n, i+1, sum + i);
            list.remove(list.size() - 1);
        }
    }
}
