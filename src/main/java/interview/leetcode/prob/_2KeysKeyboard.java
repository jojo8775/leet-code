package interview.leetcode.prob;

/**
 * There is only one character 'A' on the screen of a notepad. You can perform one of two operations on this notepad for each step:

Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.

 

Example 1:

Input: n = 3
Output: 3
Explanation: Initially, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
Example 2:

Input: n = 1
Output: 0
 

Constraints:

1 <= n <= 1000
Accepted
257,446
Submissions
432,060
 * 
 * 
 * Aug 31, 2024 - 4:41:45 PM
 * Jojo 
 */
public class _2KeysKeyboard {
    public int minSteps(int n) {
        return topDown(n);
    }
    
    private int topDown(int n){
        if(n == 1){
            return 0;
        }
        
        // states the second dimension is (n/2) + 1 because the copy paste works in 
        // x * 2 increment. 
        Integer[][] memo = new Integer[n + 1][(n / 2) + 1];
        
        // 1 is added because the initial paste needs a copy action. 
        return 1 + dp(1, 1, n, memo);
    }
    
    private int dp(int curLen, int pasteLen, int totalLen, Integer[][] memo){
        if(curLen == totalLen){
            return 0;
        }
        
        if(curLen > totalLen){
            return 1001;
        }
        
        if(memo[curLen][pasteLen] != null){
            return memo[curLen][pasteLen];
        }
        
        // 1 because only paste action is needed.
        int opt1 = 1 + dp(curLen + pasteLen, pasteLen, totalLen, memo);
        
        // 2 because copy of current + paste action is needed. 
        int opt2 = 2 + dp(curLen * 2, curLen, totalLen, memo);
        
        return memo[curLen][pasteLen] = Math.min(opt1, opt2);
    }
}
