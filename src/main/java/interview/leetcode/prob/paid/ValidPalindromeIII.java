package interview.leetcode.prob.paid;

/**
 * Given a string s and an integer k, return true if s is a k-palindrome.

A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.

 

Example 1:

Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.
Example 2:

Input: s = "abbababa", k = 1
Output: true
 

Constraints:

1 <= s.length <= 1000
s consists of only lowercase English letters.
1 <= k <= s.length
Accepted
50.7K
Submissions
101.8K
 * @author jojo
 * Dec 24, 2023 9:24:11 PM
 */
public class ValidPalindromeIII {
	 public boolean isValidPalindrome(String s, int k) {
	        return topDown(s, k);
	    }
	    
	    private boolean topDown(String s, int k){
	        // this is for memonization
	        Integer[][] memo = new Integer[s.length()][s.length()];
	        
	        return dp(s, 0, s.length() - 1, memo) <= k;
	    }
	    
	    private int dp(String s, int i, int j, Integer[][] memo){
	        // base case 0: when there is no char left
	        if(i == j){
	            return 0;
	        }
	        
	        // base case 1: when only 2 char left 
	        if(i == j - 1){
	            if(s.charAt(i) != s.charAt(j)){
	                // if two char doesnt match then we need to remove atleast one.
	                return 1;
	            }            
	            else{
	                // if both char matched, then there is nothing to be removed.
	                return 0;
	            }
	        }
	        
	        // if the previous value calculated, then re-use it.
	        if(memo[i][j] != null){
	            return memo[i][j];
	        }
	        
	        // relation:
	        // if both char matched, then move to substring. 
	        if(s.charAt(i) == s.charAt(j)){
	            memo[i][j] = dp(s, i + 1, j - 1, memo);
	        }
	        else{
	            // relation:
	            // if two char are different then one char needs to be deleted.
	            // need to find the min of substring by deleting left or deleting right
	            memo[i][j] = 1 + Math.min(dp(s, i + 1, j, memo), dp(s, i, j-1, memo));   
	        }
	        
	        return memo[i][j];
	    }
}
