package interview.leetcode.prob;

/**
 * '?' Matches any single character. '*' Matches any sequence of characters
 * (including the empty sequence).
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be: bool isMatch(const char *s, const char *p)
 * 
 * Some examples: isMatch("aa","a") → false isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false isMatch("aa", "*") → true isMatch("aa", "a*") →
 * true isMatch("ab", "?*") → true isMatch("aab", "c*a*b") → false
 * 
 * @author jojo
 *
 */
public class WildCardMatching
{
	public boolean isMatch(String s, String p) {
        if(s.equals(p)){
            return true;
        }

        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;

        for(int i=1; i<dp.length; i++){
            // no need to handle '?' because it needs a character from s
            if(p.charAt(i-1) == '*'){
                // not contributing
                dp[i][0] = dp[i-1][0];
            }
        }

        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                if(p.charAt(i-1) == s.charAt(j-1) || p.charAt(i - 1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(p.charAt(i-1) == '*'){
                    // not contributing 
                    dp[i][j] = dp[i-1][j];

                    // contributing once 
                    dp[i][j] |= dp[i-1][j-1];

                    // contributing multiple times
                    dp[i][j] |= dp[i][j-1];
                }
            }
        }

        return dp[p.length()][s.length()];
    }
	
    public boolean isMatch_1(String s, String p) {
        if(s.equals(p)){
            return true;
        }
        
        boolean[][] grid = new boolean[s.length() + 1][p.length() + 1];
        
        grid[0][0] = true;
        
        for(int i=1; i<=s.length(); i++){
            if(s.charAt(i-1) == '*' || s.charAt(i-1) == ' '){
                grid[i][0] = grid[i-1][0];
            }
        }
        
        for(int i=1; i<=p.length(); i++){
            if(p.charAt(i-1) == '*' || p.charAt(i-1) == ' '){
                grid[0][i] = grid[0][i-1];
            }
        }
        
        for(int i=1; i<=s.length(); i++){
            for(int j=1; j<=p.length(); j++){
                if(s.charAt(i-1) == p.charAt(j-1) || s.charAt(i-1) == '?' || p.charAt(j-1) == '?'){
                    grid[i][j] = grid[i-1][j-1];
                }
                else if(s.charAt(i-1) == '*' || p.charAt(j-1) == '*'){
                    grid[i][j] = grid[i-1][j] || grid[i][j-1] || grid[i-1][j-1];
                }
            }
        }
        
        return grid[s.length()][p.length()];
    }
}
