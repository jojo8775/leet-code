package interview.leetcode.prob;

/**
 * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.

A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.

 

Example 1:

Input: n = 1
Output: 5
Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
Example 2:

Input: n = 2
Output: 15
Explanation: The 15 sorted strings that consist of vowels only are
["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
Example 3:

Input: n = 33
Output: 66045
 

Constraints:

1 <= n <= 50 
Accepted
95,813
Submissions
126,209
 * @author jojo
 * May 10, 2022 11:41:01 PM
 */
public class CountSortedVowelStrings {
	public int countVowelStrings(int n) {
        // return topDown(n);
        return bottomUp(n);
    }
    
    private int bottomUp(int n){
        int[][] dp = new int[n+1][6];
        
        for(int i=1; i<=n; i++){
            for(int j=1; j <= 5; j++){
                dp[i][j] = dp[i][j-1] + (i > 1 ? dp[i-1][j] : 1);
            }
        }
        
        return dp[n][5];
    }
    
    private int topDown(int n){
        int[][] memo = new int[n + 1][6];
        
        return dp(n, 5, memo);
    }
    
    private int dp(int n, int vowels, int[][] memo){
        // if there is only one letter to select then it will be all vowels "a,e,i,o,u"
        if(n == 1){
            return vowels;
        }
        
        // if there is only one vowel, then only 1 way to select. eg n = 3 and vowels = 'a' then only aaa is possible.
        if(vowels == 1){
            return 1;
        }
        
        // memory
        if(memo[n][vowels] != 0){
            return memo[n][vowels];
        }
        
        // previous combination of n-1 letters + n time of vowels - 1 as 
        int val = dp(n-1, vowels, memo) + dp(n, vowels - 1, memo);
        
        return memo[n][vowels] = val;
    }
}
