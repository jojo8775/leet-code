package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	public List<List<String>> solveNQueens(int n) {
	    List<List<String>> result = new ArrayList<>();

	    if(n < 4){
	        return result;
	    }

	    solve(result, new int[n], 0);

	    return result;
	}

	private void solve(List<List<String>> result, int[] col, int idx){
	    if(idx == col.length){
	        List<String> entry = new ArrayList<>();

	        for(int i=0; i<col.length; i++){
	            StringBuilder sb = new StringBuilder("|");
	            for(int j=0; j<col.length; j++){
	                if(col[i] == j){
	                    sb.append("q");
	                }
	                else{
	                    sb.append(".");
	                }
	                sb.append("|");
	            }

	            entry.add(sb.toString());
	        }
	        
	        result.add(entry);
	        result.add(Arrays.asList("--------------------------------"));

	        return;
	    }

	    for(int i=0; i<col.length; i++){
	        col[idx] = i;
	        if(isValid(col, idx)){
	            solve(result, col, idx + 1);
	        }

	        col[idx] = 0;
	    }
	}

	private boolean isValid(int[] col, int colIdx){
	    for(int i=0; i<colIdx; i++){
	        // cannot be on the same horizontal line 
	        if(col[i] == col[colIdx]){
	            return false;
	        }

	        // cannot be in the left upper diagonal 
	        if(Math.abs(col[i] - col[colIdx]) == (colIdx - i)){
	            return false;
	        }
	    }

	    return true;
	}
	
	public static void main(String[] args) {
		var sol = new Prac7();
		
//		var result = sol.combine(7, 3);
//		for(List<Integer> e : result) {
//			for(int n : e) {
//				System.out.print(n + ", ");
//			}
//			System.out.println();
//		}
		
		var result = sol.solveNQueens(11);
		
		result.forEach(x -> x.forEach(y -> System.out.println(y)));
	}
}
