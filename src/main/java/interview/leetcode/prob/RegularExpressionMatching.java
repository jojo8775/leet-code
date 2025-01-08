package interview.leetcode.prob;

/**
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

 

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
 

Constraints:

1 <= s.length <= 20
1 <= p.length <= 20
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
1.1M
Submissions
3.7M
Acceptance Rate
28.7%
 * 
 * Jan 4, 2025 - 10:08:43 AM
 * Jojo 
 */
public class RegularExpressionMatching {
	public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;

        for(int i=1; i<dp.length; i++){
            if(p.charAt(i-1) == '*'){
                dp[i][0] = dp[i-2][0];
            }
        }


        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                if(p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(p.charAt(i-1) == '*'){
                    // not contributing 
                    dp[i][j] = dp[i-2][j];

                    if(p.charAt(i-2) == s.charAt(j-1) || p.charAt(i-2) == '.'){
                        // contrinuting only once
                        dp[i][j] |= dp[i-2][j-1];

                        // contributing multiple times
                        dp[i][j] |= dp[i][j-1];
                    }
                }
            }
        }

        return dp[p.length()][s.length()];
    }
	
	public boolean isMatch_1(String s, String p) {
        boolean[][] grid = new boolean[p.length() + 1][s.length() + 1];
        
        //when both are empty
        grid[0][0] = true;
        
        //marking zeroth row 
        /*
        for(int i=1; i<grid[0].length; i++){
            if(s.charAt(i-1) == ' '){
                grid[0][i] = grid[0][i-1];
            }
        }
        */
        
        //marking zeroth col
        for(int i=1; i<grid.length; i++){
            if(p.charAt(i-1) == '*'){
                grid[i][0] = grid[i-2][0];
            }
            
            /*
            if(p.charAt(i-1) == ' '){
                grid[i][0] = grid[i-1][0];
            }
            // considering not making any contribution 
            else if(p.charAt(i-1) == '*'){
                grid[i][0] = grid[i-2][0];
                grid[i-1][0] = grid[i-2][0];
            }
            */
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
