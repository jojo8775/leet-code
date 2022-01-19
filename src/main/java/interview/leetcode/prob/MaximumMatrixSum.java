package interview.leetcode.prob;

/**
 * You are given an n x n integer matrix. You can do the following operation any number of times:

Choose any two adjacent elements of matrix and multiply each of them by -1.
Two elements are considered adjacent if and only if they share a border.

Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.

 

Example 1:


Input: matrix = [[1,-1],[-1,1]]
Output: 4
Explanation: We can follow the following steps to reach sum equals 4:
- Multiply the 2 elements in the first row by -1.
- Multiply the 2 elements in the first column by -1.
Example 2:


Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
Output: 16
Explanation: We can follow the following step to reach sum equals 16:
- Multiply the 2 last elements in the second row by -1.
 

Constraints:

n == matrix.length == matrix[i].length
2 <= n <= 250
-105 <= matrix[i][j] <= 105
Accepted
8,734
Submissions
19,931
 * @author jojo
 * Jan 18, 2022 10:03:39 PM
 */
public class MaximumMatrixSum {
	public long maxMatrixSum(int[][] matrix) {
        // if there is a zero we can make the entire matrix as + ve
        // if # of - ves are even then we can make them all + ve
        // if # of - ves are odd then we need to keep the smallest abs value as -ve
        
        boolean zeroFound = false;
        int len = matrix.length, nCount = 0, smallest = Integer.MAX_VALUE;
        long sum = 0;
        
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                if(matrix[i][j] < 0){
                    nCount++;
                }
                
                if(matrix[i][j] == 0){
                    zeroFound = true;
                }
                
                int absVal = Math.abs(matrix[i][j]);
                smallest = Math.min(smallest, absVal);
                sum += absVal;
                
                //System.out.println("sum:" + sum + "  smallest:" + smallest) ;
            }
        }
        
        if(zeroFound || nCount % 2 == 0){
            return sum;
        }
        
        return sum - (2 * smallest); // * by 2 because the initial sum already had the abs value added.
    }
}
