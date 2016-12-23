package interview.leetcode.prob;

/**
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Example 1:
Input: "abab"

Output: True

Explanation: It's the substring "ab" twice.
Example 2:
Input: "aba"

Output: False
Example 3:
Input: "abcabcabcabc"

Output: True

Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 * @author jojo
 *
 */
public class RepeatedSubStringPattern {
    public boolean repeatedSubstringPattern(String str) {
        int[] arr = new int[str.length()];

        int i = 1, j = 0;

        while (i < str.length()) {
            if (str.charAt(i) == str.charAt(j)) {
                arr[i] = j + 1;
                j++;
                i++;
            } else if (j > 0) {
                j = arr[j - 1];
            } else {
                i++;
            }
        }

        int prefixLength = arr[arr.length - 1];

        return prefixLength > 0 && str.length() % (str.length() - prefixLength) == 0;
    }
    
    public static void main(String[] args){
        System.err.println(new RepeatedSubStringPattern().repeatedSubstringPattern("abab"));
    }
}
