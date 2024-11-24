package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an m x n binary matrix matrix.

You can choose any number of columns in the matrix and flip every cell in that column (i.e., Change the value of the cell from 0 to 1 or vice versa).

Return the maximum number of rows that have all values equal after some number of flips.

 

Example 1:

Input: matrix = [[0,1],[1,1]]
Output: 1
Explanation: After flipping no values, 1 row has all values equal.
Example 2:

Input: matrix = [[0,1],[1,0]]
Output: 2
Explanation: After flipping values in the first column, both rows have equal values.
Example 3:

Input: matrix = [[0,0,0],[0,0,1],[1,1,0]]
Output: 2
Explanation: After flipping values in the first two columns, the last two rows have equal values.
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is either 0 or 1.
Accepted
33,192
Submissions
48,602
 * 
 * Nov 21, 2024 - 8:28:01 PM
 * Jojo 
 */
public class FlipColumnsForMaximumNumberOfEqualRows {
	public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> map = new HashMap<>();
        
        int max = 0, m = matrix.length, n = matrix[0].length;
        
        for(int i=0; i<m; i++){
            StringBuilder sb = new StringBuilder();
            
            for(int j=0; j<n; j++){
                if(matrix[i][0] == matrix[i][j]){
                    sb.append("T");
                }
                else{
                    sb.append("F");
                }
            }
            
            String key = sb.toString();
            
            max = Math.max(max, 1 + map.getOrDefault(key, 0));
            
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        
        return max;
    }
}
