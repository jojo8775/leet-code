package interview.leetcode.prob;

/**
 * 
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

 

Example 1:

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation: 
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
Example 2:

Input: matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation: 
There are 6 squares of side 1.  
There is 1 square of side 2. 
Total number of squares = 6 + 1 = 7.
 

Constraints:

1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1
Accepted
106,583
Submissions
145,899
 * @author jojo
 * Jun 16, 2021  10:57:37 PM
 */
public class CountSquareSubmatricesWithAllOne {
	public int countSquares_1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        
        int[][] dp = new int[m][n];
        
        int count = 0;
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                dp[i][j] = matrix[i][j];
                
                if(i != 0 && j != 0 && matrix[i][j] != 0){
                    // min of all three corners + 1
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
                
                count += dp[i][j];
            }
        }
        
        return count;
    }
	

    public int countSquares(int[][] matrix) {
        return bottomup(matrix);
        //return topdown(matrix);
    }
    
    private int bottomup(int[][] matrix){
        int m = matrix.length, n = matrix[0].length;
        
        int[][] dp = new int[m][n];
        
        int ans = 0;
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(matrix[i][j] == 0){
                    continue;
                }
                
                if(i == 0 || j == 0){
                    // base case
                    dp[i][j] = matrix[i][j]; 
                    ans += matrix[i][j];
                }
                else{
                    int left = dp[i][j-1];
                    int up = dp[i-1][j];
                    int dia = dp[i-1][j-1];
                    
                    int min = Math.min(left, up);
                    min = Math.min(min, dia);
                    
                    dp[i][j] = 1 + min;
                    
                    ans += dp[i][j];
                }
            }
        }
        
        return ans;
    }
    
    private int topdown(int[][] matrix){
        int ans = 0;
        
        Integer[][] memo = new Integer[matrix.length][matrix[0].length];
        
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(matrix[i][j] == 1){
                    ans += dp(i, j, matrix, memo);
                }
            }
        }
        
        return ans;
    }
    
    private int dp(int i, int j, int[][] matrix, Integer[][] memo){
        if(i == matrix.length || j == matrix[0].length){
            return 0;
        }
        
        if(matrix[i][j] == 0){
            return 0;
        }
        
        if(memo[i][j] != null){
            return memo[i][j];
        }
        
        int right = dp(i, j+1, matrix, memo);
        int bottom = dp(i+1, j, matrix, memo);
        int dia = dp(i+1, j+1, matrix, memo);
        
        int min = Math.min(right, bottom);
        min = Math.min(min, dia);
        
        return memo[i][j] = 1 + min;
    }
}
