package interview.leetcode.prob;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

 

Example 1:


Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 9
Accepted
210,009
Submissions
320,338
 * @author jojo
 * Feb 27, 2022 12:50:02 PM
 */
public class NQueeensII {
	public int totalNQueens(int n) {
        int[] pos = new int[n];
        
        return solve(pos, 0);
    }
    
    private int solve(int[] pos, int col){
        if(pos.length == col){
        	return 1;
        }
        
        int count = 0;
        
        for(int i=0; i<pos.length; i++){
            pos[col] = i;
            if(isValid(pos, col)){
                count += solve(pos, col+1);
            }
        }
        
        return count;
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
}
