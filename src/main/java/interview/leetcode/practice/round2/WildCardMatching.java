package interview.leetcode.practice.round2;

public class WildCardMatching {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        
        for(int i=1; i<dp.length; i++){
            if(p.charAt(i-1) == '*'){
                dp[i][0] = dp[i-1][0];
            }
        }
        
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                char ch1 = p.charAt(i-1), ch2 = s.charAt(j-1);
                if(ch1 == ch2 || ch1 == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(ch1 == '*'){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-1] || dp[i][j-1];
                }
            }
        }
        
        return dp[p.length()][s.length()];
    }
}
