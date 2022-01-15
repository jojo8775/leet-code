package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An n x n matrix is valid if every row and every column contains all the integers from 1 to n (inclusive).

Given an n x n integer matrix matrix, return true if the matrix is valid. Otherwise, return false.

 

Example 1:


Input: matrix = [[1,2,3],[3,1,2],[2,3,1]]
Output: true
Explanation: In this case, n = 3, and every row and column contains the numbers 1, 2, and 3.
Hence, we return true.
Example 2:


Input: matrix = [[1,1,1],[1,2,3],[1,2,3]]
Output: false
Explanation: In this case, n = 3, but the first row and the first column do not contain the numbers 2 or 3.
Hence, we return false.
 

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 100
1 <= matrix[i][j] <= n
Accepted
11,158
Submissions
22,126
 * @author jojo
 * Jan 9, 2022 10:24:44 PM
 */
public class CheckIfEveryRowAndColumnHasUniqueNumbers {
	public boolean checkValid(int[][] matrix) {
        int m = matrix.length;
        
        for(int i=0; i<m; i++){
            boolean[] seen = new boolean[m+1];
            for(int j=0; j<m; j++){
                if(seen[matrix[i][j]]){
                    return false;
                }
                
                seen[matrix[i][j]] = true;
            }
        }
        
        for(int j=0; j<m; j++){
            boolean[] seen = new boolean[m+1];
            for(int i=0; i<m; i++){
                if(seen[matrix[i][j]]){
                    return false;
                }
                
                seen[matrix[i][j]] = true;
            }
        }
        
        return true;
    }
    
    public boolean checkValid_old(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<Set<Integer>> list = new ArrayList<>();
        
        for(int i=0; i<m; i++){
            list.add(new HashSet<>());
        }
        
        for(int j=0; j<n; j++){
            Set<Integer> s = new HashSet<>(); 
            for(int i=0; i<m; i++){
                if(!s.add(matrix[i][j])){
                    return false;
                }
                
                if(!list.get(i).add(matrix[i][j])){
                    return false;
                }
            }
        }
        
        return true;
    }
}
