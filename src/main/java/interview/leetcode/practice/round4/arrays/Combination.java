package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if(n == 0 || k == 0){
            return result;
        }
        
        findCombination(result, new ArrayList<Integer>(), n, k, 1);
        return result;
    }
    
    private void findCombination(List<List<Integer>> result, List<Integer> list, int n, int k, int idx){
        if(list.size() == k){
            result.add(new ArrayList<>(list));
            return;
        }
        
        for(int i=idx; i<=n; i++){
            list.add(i);
            findCombination(result, list, n, k, i+1);
            list.remove(list.size() - 1);
        }
    }
}
