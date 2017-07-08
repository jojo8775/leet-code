package interview.leetcode.practice.round4.arrays;

import java.util.List;

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 0){
            return 0;
        }
        
        int[][] dp = new int[triangle.size()][triangle.size()];
        
        dp[0][0] = triangle.get(0).get(0);
        
        for(int i=1; i<triangle.size(); i++){
            // since the input is in triangular shape
            dp[i-1][i] = Integer.MAX_VALUE;
            for(int j=0; j<triangle.get(i).size(); j++){
                int val = triangle.get(i).get(j);
                
                // if j == 0 there is no other option apart from going straigth down
                if(j==0){
                    dp[i][j] = dp[i-1][j] + val;
                }
                // if this is the first element in that col (Since in put is in truangle fashion) diagonal is the only way
                else if(dp[i-1][j] == Integer.MAX_VALUE){
                    dp[i][j] = dp[i-1][j-1] + val;
                }
                // time to choose between going diagonal or straight
                else{
                    dp[i][j] = Math.min(dp[i-1][j-1] + val, dp[i-1][j] + val);
                }
            }
        }
        
        int lastRow = dp.length - 1, result = Integer.MAX_VALUE;
        for(int i=0; i<dp[0].length; i++){
            result = Math.min(result, dp[lastRow][i]);
        }
        
        return result;
    }
}
