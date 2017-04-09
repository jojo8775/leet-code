package interview.leetcode.practice.round2;

public class Candy {
    public int candy(int[] ratings) {
        if(ratings.length < 1){
            return 0;
        }
        
        int[] dp = new int[ratings.length];
        dp[0] = 1;
        for(int i=1; i<ratings.length; i++){
            dp[i] = 1;
            if(ratings[i] > ratings[i-1]){
                dp[i] = dp[i-1] + 1;
            }
        }
        
        for(int i = ratings.length-1; i>0; i--){
            if(ratings[i-1] > ratings[i] && dp[i-1] <= dp[i]){
                dp[i-1] = dp[i] + 1;
            }
        }
        
        int candyCount = 0;
        for(int n : dp){
            candyCount += n;
        }
        
        return candyCount;
    }
}
