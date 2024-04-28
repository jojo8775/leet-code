package interview.leetcode.prob;

/**
 * Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.

A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two elements chosen in adjacent rows are in the same column.

 

Example 1:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
Output: 13
Explanation: 
The possible falling paths are:
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the smallest sum is [1,5,7], so the answer is 13.
Example 2:

Input: grid = [[7]]
Output: 7
 

Constraints:

n == grid.length == grid[i].length
1 <= n <= 200
-99 <= grid[i][j] <= 99
AcceptedR
58,714
Submissions
99,721
 * 
 * Apr 25, 2024 - 7:35:22 PM
 * Jojo 
 */
public class MinimumFallingPathSumII {
	public int minFallingPathSum(int[][] grid) {
        int firstMinIdx = -1, secondMinIdx = -1;
        
        // finding the first two min idx in the row: 0
        for(int i=0; i<grid.length; i++){
            if(firstMinIdx == -1 || grid[0][i] <= grid[0][firstMinIdx]){
                secondMinIdx = firstMinIdx;
                firstMinIdx = i;
            }
            else if(secondMinIdx == -1 || grid[0][i] <= grid[0][secondMinIdx]){
                secondMinIdx = i;
            }
        }
        
        for(int i=1; i<grid.length; i++){
            int firstMinIdx_1 = -1, secondMinIdx_1 = -1;
            
            for(int j=0; j<grid.length; j++){
                if(j == firstMinIdx){
                    // current min = current val + second min since first min and current idx are same
                    grid[i][j] += grid[i-1][secondMinIdx];
                }
                else{
                    // current min = current val + first min since first min and current idx are different
                    grid[i][j] += grid[i-1][firstMinIdx];
                }
                
                if(firstMinIdx_1 == -1 || grid[i][j] <= grid[i][firstMinIdx_1]){
                    secondMinIdx_1 = firstMinIdx_1;
                    firstMinIdx_1 = j;
                }
                else if(secondMinIdx_1 == -1 || grid[i][j] <= grid[i][secondMinIdx_1]){
                    secondMinIdx_1 = j;
                }
            }
            
            firstMinIdx = firstMinIdx_1;
            secondMinIdx = secondMinIdx_1;
        }
        
        return grid[grid.length - 1][firstMinIdx];
    }
}
