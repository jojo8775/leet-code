package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
 * @author jojo
 *
 */
public class SparceMatrixMultiplcation {
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];
        
        Map<Integer, List<Integer>> colVal = new HashMap<Integer, List<Integer>>();
        
        for(int i=0; i<A.length; i++){
            for(int j=0; j<A[i].length; j++){
                if(A[i][j] != 0){
                    colVal.computeIfAbsent(i, v-> new ArrayList<Integer>());
                    colVal.get(i).add(j);
                }
            }
        }
        
        
        for(Map.Entry<Integer, List<Integer>> entry : colVal.entrySet()){
            int i = entry.getKey();
            for(int k=0; k<B[0].length; k++){
                int val = 0;
                for(int j : entry.getValue()){
                    val = val + (A[i][j] * B[j][k]);
                }
                
                result[i][k] = val;
            }
        }
        
        return result;
    }
}
