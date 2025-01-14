package interview.leetcode.prob;

/**
 * Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.

 

Example 1:

Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we do not need any insertions.
Example 2:

Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".
Example 3:

Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
209.9K
Submissions
292.5K
Acceptance Rate
71.8%
 * 
 * Jan 8, 2025 - 7:40:01 PM
 * Jojo 
 */
public class MinimumInsertionStepsToMakeAStringPalindrome {
	public int minInsertions(String s) {
        return dp(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }

    private int dp(String s, int beg, int end, Integer[][] memo){
        if(beg >= end){
            return 0;
        }

        if(memo[beg][end] != null){
            return memo[beg][end];
        }

        if(s.charAt(beg) == s.charAt(end)){
            return memo[beg][end] = dp(s, beg + 1, end - 1, memo);
        }
        else{
            int left = 1 + dp(s, beg + 1, end, memo);
            int right = 1 + dp(s, beg, end - 1, memo);

            return memo[beg][end] = Math.min(left, right);
        }
    }
}
