package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
 * @author jojo
 *
 */
public class CombinationSumIII {
	public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        find(result, new ArrayList<Integer>(), k, n, 1);
        
        return result;
    }
    
    private void find(List<List<Integer>> result, List<Integer> list, int n, int k, int idx){
        if(n <= 0 && k > 0){
            return;
        }
        
        //result found
        if(n == 0 && k == 0){
            result.add(new ArrayList<Integer>(list));
        }
        
        for(int i=idx; i<=9; i++){
            if(k - i < 0){
                break;
            }
            
            list.add(i);
            find(result, list, n - 1, k - i, i+1);
            list.remove(list.size() - 1);
        }
    }
}
