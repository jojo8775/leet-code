package interview.leetcode.prob;

/**
 * 
A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), followed by some number of '1's (also possibly 0.)

We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.

Return the minimum number of flips to make S monotone increasing.

 

Example 1:

Input: "00110"
Output: 1
Explanation: We flip the last digit to get 00111.
Example 2:

Input: "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.
Example 3:

Input: "00011000"
Output: 2
Explanation: We flip to get 00000000.
 

Note:

1 <= S.length <= 20000
S only consists of '0' and '1' characters.
 * @author jojo
 * Nov 1, 2020  12:29:50 AM
 */
public class FlipStringToMonotoneIncreasing {
	public int minFlipsMonoIncr(String s) {
        int[][] dp = new int[s.length() + 1][2];
        
        for(int i=1; i<=s.length(); i++){
            char ch = s.charAt(i-1);
     
            // minimum flips to keep the current number as 0
            dp[i][0] = ch == '0' ? dp[i-1][0] : dp[i-1][0] + 1;
            
            // since if the current number is 1, the previous number can be either 0 or 1 
            int val = Math.min(dp[i-1][0], dp[i-1][1]);
            
            // minimum flips to keep the current number as 1
            dp[i][1] = ch == '1' ? val : val + 1;
        }
        
        return Math.min(dp[s.length()][0], dp[s.length()][1]);
    }
	
	
	public int minFlipsMonoIncr_adv(String s) {
        int f0 = 0, f1 = 0;
        
        for(int i=1; i<=s.length(); i++){
            char ch = s.charAt(i-1);
     
            int prevF0 = f0;
            // minimum flips to keep the current number as 0
            f0 = ch == '0' ? f0 : f0 + 1;
            
            // since if the current number is 1, the previous number can be either 0 or 1 
            int val = Math.min(prevF0, f1);
            
            // minimum flips to keep the current number as 1
            f1 = ch == '1' ? val : val + 1;
        }
        
        return Math.min(f0, f1);
    }
}
