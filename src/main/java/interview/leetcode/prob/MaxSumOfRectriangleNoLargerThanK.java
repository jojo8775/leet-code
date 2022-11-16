package interview.leetcode.prob;

import java.util.TreeSet;

/**
 * Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix such that its sum is no larger than k.

It is guaranteed that there will be a rectangle with a sum no larger than k.

 

Example 1:


Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2
Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
Example 2:

Input: matrix = [[2,2,-1]], k = 3
Output: 3
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-100 <= matrix[i][j] <= 100
-105 <= k <= 105
 

Follow up: What if the number of rows is much larger than the number of columns?

Accepted
116,159
Submissions
263,578
 * @author jojo
 * Nov 16, 2022 12:32:27 AM
 */
public class MaxSumOfRectriangleNoLargerThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        
        int area = Integer.MIN_VALUE;
        
        for(int r1=0; r1<m; r1++){
            
            int[] arr = new int[n];
            for(int r2 = r1; r2 < m; r2++){
                for(int c1=0; c1<n; c1++){
                    arr[c1] += matrix[r2][c1];
                }
                
                area = Math.max(area, findMax(arr, n, k));
            }
        }
        
        return area;
    }
    
    private int findMax(int[] arr, int n, int k){
        TreeSet<Integer> bst = new TreeSet<>();
        bst.add(0);
        
        int area = Integer.MIN_VALUE;
        
        for(int i=0, right = 0; i<n; i++){
            right += arr[i];
            Integer left = bst.ceiling(right - k);
            if(left != null){
                area = Math.max(area, right - left);
            }
            
            bst.add(right);
        }
        
        return area;
    }
}
