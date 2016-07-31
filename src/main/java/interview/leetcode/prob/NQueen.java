package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
 * @author jojo
 *
 */
public class NQueen {
	public List<List<String>> solveNQueens(int n) {
        int[] pos = new int[n];
        
        List<List<String>> result = new ArrayList<List<String>>();
        solve(result, pos, 0);
        
        return result;
    }
    
    private void solve(List<List<String>> result, int[] pos, int col){
        if(pos.length == col){
        	List<String> list = new ArrayList<String>();
            for(int i=0; i<pos.length; i++){
                StringBuilder sb = new StringBuilder();
                for(int j=0; j<pos.length; j++){
                    if(pos[j] == i){
                        sb.append('Q');
                    }
                    else{
                        sb.append('.');
                    }
                }
                list.add(sb.toString());
            }
            
            result.add(list);
            
            return;
        }
        
        for(int i=0; i<pos.length; i++){
            pos[col] = i;
            if(isValid(pos, col)){
                solve(result, pos, col+1);
            }
        }
    }
    
    private static boolean isValid(int[] pos, int col){
        for(int i=0; i<col; i++){
            //queen falls on the same row
            if(pos[col] == pos[i]){
                return false;
            }
            
            //queens falls on diagonal
            if(Math.abs(pos[col] - pos[i]) == (col - i)){
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args){
    	List<List<String>> result = new NQueen().solveNQueens(4);
    	
    	for(List<String> l : result){
    		for(String s : l){
    			System.out.println(s);
    		}
    		
    		System.out.println();
    	}
    }
}
