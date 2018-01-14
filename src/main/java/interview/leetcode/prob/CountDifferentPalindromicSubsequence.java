package interview.leetcode.prob;

/**
 * 
Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.

A subsequence of a string S is obtained by deleting 0 or more characters from S.

A sequence is palindromic if it is equal to the sequence reversed.

Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.

Example 1:
Input: 
S = 'bccb'
Output: 6
Explanation: 
The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
Note that 'bcb' is counted only once, even though it occurs twice.
Example 2:
Input: 
S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
Output: 104860361
Explanation: 
There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
Note:

The length of S will be in the range [1, 1000].
Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.
 * @author jojo
 *
 */
public class CountDifferentPalindromicSubsequence {
    private static long MOD = 1000000007;

    public int countPalindromicSubsequences(String S) {
        int len = S.length();
        int[][] dp = new int[len][len];

        // all single letters are a palindrome
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }

        for (int i = 1; i < len; i++) {
            // iterating over each substring.
            for (int beg = 0, end = i; end < len; end++, beg++) {
                // if the extreme character of current substring match then it contributes to palindrome count
                if (S.charAt(beg) == S.charAt(end)) {
                    // eg : aba, 'b' = 1. Contribution of 'b' in 'abc' are 'b', 'aba' so (contribution of 'b') * 2
                    dp[beg][end] = 2 * dp[beg + 1][end - 1];

                    // idea is to find occurrence of extreme characters with in the substring (excluding the extreme characters)
                    int leftIdx = beg + 1, rightIdx = end - 1;
                    char ch = S.charAt(beg);
                    while (leftIdx <= rightIdx && ch != S.charAt(leftIdx)) {
                        leftIdx++;
                    }

                    while (leftIdx <= rightIdx && ch != S.charAt(rightIdx)) {
                        rightIdx--;
                    }

                    // if there is no occurrence then we need to add max possible unique palindrome count of 2 characters, which is 2
                    // e,g 'a','a' can form 'a' and 'aa'
                    if (leftIdx > rightIdx) {
                        dp[beg][end] += 2;
                    }

                    // if there is one occurrence then we need to add max possible unique palindrome count of 1 character, which is 1
                    else if (leftIdx == rightIdx) {
                        dp[beg][end] += 1;
                    }

                    // if there are two occurrence then we need to remove the repetitive palindromes.
                    else {
                        dp[beg][end] -= dp[leftIdx + 1][rightIdx - 1];
                    }
                }
                // else preserve the max count so far.
                else {
                    dp[beg][end] = dp[beg][end - 1] + dp[beg + 1][end] - dp[beg + 1][end - 1];
                }

                // this is the modulus logic to prevent overflow.
                dp[beg][end] = (int) ((dp[beg][end] + MOD) % MOD);
            }
        }

        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        int result = new CountDifferentPalindromicSubsequence().countPalindromicSubsequences("bccb");
        System.out.println(result);
    }
}
