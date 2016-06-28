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
