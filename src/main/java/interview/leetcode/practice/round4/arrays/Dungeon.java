package interview.leetcode.practice.round4.arrays;

public class Dungeon {
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        
        int m = dungeon.length, n = dungeon[0].length;
        
        for(int i=m-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                dp[i][j] = Math.max(0, 0 - dungeon[i][j]);
                
                int right = j == n-1 ? 0 : Math.max(0, dp[i][j + 1] - Math.max(0, dungeon[i][j]));
                int down = i == m-1 ? 0 : Math.max(0, dp[i+1][j] - Math.max(0, dungeon[i][j]));
                
                if(i != m-1 && j != n-1){
                    dp[i][j] = Math.min(dp[i][j] + right, dp[i][j] + down);
                }
                else if(i != m-1){
                    dp[i][j] += down;
                }
                else if(j != n-1){
                    dp[i][j] += right;
                }
            }
        }
        
        return dp[0][0] + 1;
    }
}
