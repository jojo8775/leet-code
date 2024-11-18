package interview.leetcode.prob;

/**
 * '.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
 * @author jojo
 *
 */
public class RegularExpressionII
{
	public boolean isMatch(String s, String p) {
        boolean[][] grid = new boolean[p.length() + 1][s.length() + 1];
        
        //when both are empty
        grid[0][0] = true;
        
        //marking zeroth row 
        for(int i=1; i<grid[0].length; i++){
            if(s.charAt(i-1) == ' '){
                grid[0][i] = grid[0][i-1];
            }
        }
        
        //marking zeroth col
        for(int i=1; i<grid.length; i++){
            if(p.charAt(i-1) == ' '){
                grid[i][0] = grid[i-1][0];
            }
            // considering not making any contribution 
            else if(p.charAt(i-1) == '*'){
                grid[i][0] = grid[i-2][0];
                grid[i-1][0] = grid[i-2][0];
            }
        }
        
        for(int i=1; i<grid.length; i++){
            if(p.charAt(i-1) == '*'){
                //considering not making any contribution
                for(int j=1; j<grid[0].length; j++){
                    grid[i][j] = grid[i-2][j];
                }
                
                //considering making contribution
                for(int j=1; j<grid[0].length; j++){
                    if(p.charAt(i-2) == '.' || p.charAt(i-2) == s.charAt(j-1)){
                    	 // grid[i][j-1] -- checks if more than one occurance of prev make it true 
                        // grid[i-2][j-1] -- checks if one occurance of prev make it true 
                        // grid[i-2][j] -- checks if no occurance of prev will make it true                     	
                        grid[i][j] = grid[i][j] || grid[i-2][j-1] || grid[i][j-1];
                    }
                }
            }
            
            else if(p.charAt(i-1) == '.'){
                for(int j=1; j<grid[0].length; j++){
                    grid[i][j] = grid[i-1][j-1];
                }
            }
            
            else{
                for(int j=1; j<grid[0].length; j++){
                    if(p.charAt(i-1) == s.charAt(j-1)){
                        grid[i][j] = grid[i-1][j-1];
                    }
                }
            }
        }
        
        return grid[p.length()][s.length()];
    }
}
