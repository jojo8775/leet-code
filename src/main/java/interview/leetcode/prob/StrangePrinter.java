package interview.leetcode.prob;

/**
 * There is a strange printer with the following two special properties:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
Given a string s, return the minimum number of turns the printer needed to print it.

 

Example 1:

Input: s = "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
Example 2:

Input: s = "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 

Constraints:

1 <= s.length <= 100
s consists of lowercase English letters.
Accepted
146,044
Submissions
238,649
 * 
 * 
 * Aug 31, 2024 - 3:48:32 PM
 * Jojo 
 */
public class StrangePrinter {
    public int strangePrinter(String s) {
        //return topDown(s);
        return bottomUp(s);
    }
    
    private int bottomUp(String s){
        s = scrubDuplicates(s);
        
        // states
        int[][] dp = new int[s.length()][s.length()];
        
        for(int i=0; i<dp.length; i++){
            dp[i][i] = 1;
        }
        
        for(int substringLen = 1; substringLen < s.length(); substringLen++){
            for(int start = 0; start + substringLen < s.length(); start++){
                int end = start + substringLen;
                
                // this is the usecase when all character in 's' are unique
                int minTurns = end - start + 1;
                
                // interating the substring to identify if there is any duplicate characters.
                for(int split = 0; split < substringLen; split++){
                    int minWithSplit = dp[start][start + split] + dp[start + split + 1][end];
                    
                    if(s.charAt(start + split) == s.charAt(end)){
                        // if both consequitive numbers are same, we should remove 1 because that is what needed 
                        // for each single letter to be printed. 
                        minWithSplit--;
                    }
                    
                    minTurns = Math.min(minTurns, minWithSplit);
                }
                
                dp[start][end] = minTurns;
            }
        }
        
        return dp[0][s.length() - 1];
    }
    
    private int topDown(String s){
        // need to scrub the consequitive letters, because if the input is "abc" or "aabcc" the min turns will be 3
        // for ease of calculation we should scrub the consequitive letters.
        s = scrubDuplicates(s);
        
        // states
        Integer[][] memo = new Integer[s.length()][s.length()];

        return dp(s, 0, s.length() - 1, memo);
    }

    private int dp(String s, int beg, int end, Integer[][] memo){
        if(beg > end){
            return 0;
        }

        if(memo[beg][end] != null){
            return memo[beg][end];
        }

        // when all the characters in the 's' is unique
        int minTurns = 1 + dp(s, beg + 1, end, memo);

        for(int i=beg+1; i<=end; i++){
            if(s.charAt(beg) == s.charAt(i)){
                int minSplitTurn = dp(s, beg, i - 1, memo) + dp(s, i+1, end, memo);

                minTurns = Math.min(minTurns, minSplitTurn); 
            }
        }

        memo[beg][end] = minTurns;

        return minTurns;
    }

    private String scrubDuplicates(String s){
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));

        for(int i=1; i<s.length(); i++){
            if(s.charAt(i) == s.charAt(i-1)){
                continue;
            }

            sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}
