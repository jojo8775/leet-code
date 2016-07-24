package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 * @author jojo
 *
 */
public class Combinations {
	public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int[] arr = new int[k];
        dfs(result, arr, 1, 0, n);
        
        return result;
    }
    
    private void dfs(List<List<Integer>> result, int[] arr, int count, int idx, int n){
        if(idx == arr.length){
            List<Integer> list = new ArrayList<Integer>();
            for(int i : arr){
                list.add(i);
            }
            
            result.add(list);
            return;
        }	
        
        for(int i=count; i<=n; i++){
            arr[idx] = i;
            dfs(result, arr, i + 1, idx + 1, n);
        }
    }
    
    public static void main(String[] args){
    	List<List<Integer>> result = new Combinations().combine(4, 2);
    	
    	System.out.println(result.size());
    }
}
