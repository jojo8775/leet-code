package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class FactorCombination {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        
        backtrack(result, new ArrayList<Integer>(), 2, n);
        
        return result;
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> list, int factor, int n){
        for(int i=factor; i*i<=n; i++){
            if(n%i == 0){
                list.add(i);
                List<Integer> entry = new ArrayList<Integer>(list);
                entry.add(n/i);
                result.add(entry);
                backtrack(result, list, i, n/i);
                list.remove(list.size() - 1);
            }
        }
    }
}
