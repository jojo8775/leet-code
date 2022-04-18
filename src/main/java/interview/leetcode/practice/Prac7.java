package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.List;

public class Prac7 {
	public List<List<Integer>> combine(int n, int k) {
	    List<List<Integer>> result = new ArrayList<>();

	    backtrack(result, new int[k], 0, 1, n);

	    return result;
	}

	private void backtrack(List<List<Integer>> result, int[] arr, int idx, int beg, int n){
	    if(idx == arr.length){
	        List<Integer> entry = new ArrayList<>();
	        for(int e : arr){
	            entry.add(e);
	        }

	        result.add(entry);

	        return;
	    }

	    for(int i=beg; i<=n; i++){
	        arr[idx] = i;
	        backtrack(result, arr, idx + 1, i + 1, n);
	    }
	}
	
	public static void main(String[] args) {
		var sol = new Prac7();
		
		var result = sol.combine(7, 3);
		for(List<Integer> e : result) {
			for(int n : e) {
				System.out.print(n + ", ");
			}
			System.out.println();
		}
	}
}
