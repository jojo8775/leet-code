package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a matrix, and a target, return the number of non-empty submatrices that sum to target.

A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.

Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.

 

Example 1:

Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.
Example 2:

Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
 

Note:

1 <= matrix.length <= 300
1 <= matrix[0].length <= 300
-1000 <= matrix[i] <= 1000
-10^8 <= target <= 10^8
Accepted
4,128
Submissions
7,126
 * @author jojo
 *Sep 1, 20191:19:15 PM
 */
public class NumberOfsubmatrixThatSumToTarget {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int cols = matrix.length, rows = matrix[0].length;
        
        // doing the row sum for each row. 
        for(int i=0; i<cols; i++){
            for(int r = 1; r< rows;r++){
                matrix[i][r] += matrix[i][r-1];
            }
        }
        
        int result = 0;
        
        // selecting all possible window of rows using r1 and r2 loops 
        for(int r1 = 0; r1< rows; r1++){
            for(int r2 = r1; r2<rows; r2++){
                // this helps to find the previous sub matrix sum
                Map<Integer, Integer> sumMap = new HashMap<>();
                
                // this is to account when sum - target == 0;
                sumMap.put(0,1);
                
                // current sum
                int sum = 0;
                for(int c=0; c<cols; c++){
                    // since each row has cumulative value, need to remove the ceiling if r1 > 0
                    sum += r1 == 0 ? matrix[c][r2] : matrix[c][r2] - matrix[c][r1 - 1];
                    result += sumMap.getOrDefault(sum - target, 0);
                    sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
                }
            }
        }
        
        return result;
    }
}
