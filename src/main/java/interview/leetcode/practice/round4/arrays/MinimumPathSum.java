package interview.leetcode.practice.round4.arrays;

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        
        int m = grid.length, n = grid[0].length;
        for(int i=m-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                if(j==n-1 && i==m-1){
                    continue;
                }
                else if(j == n-1){
                    grid[i][j] += grid[i+1][j];
                }
                else if(i == m-1){
                    grid[i][j] += grid[i][j+1];
                }
                else{
                    grid[i][j] += Math.min(grid[i+1][j], grid[i][j+1]);
                }
            }
        }
        
        return grid[0][0];
    }
}
