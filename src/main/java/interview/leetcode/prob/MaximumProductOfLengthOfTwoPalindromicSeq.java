package interview.leetcode.prob;

/**
 * Given a string s, find two disjoint palindromic subsequences of s such that
 * the product of their lengths is maximized. The two subsequences are disjoint
 * if they do not both pick a character at the same index.
 * 
 * Return the maximum possible product of the lengths of the two palindromic
 * subsequences.
 * 
 * A subsequence is a string that can be derived from another string by deleting
 * some or no characters without changing the order of the remaining characters.
 * A string is palindromic if it reads the same forward and backward.
 * 
 * 
 * 
 * Example 1:
 * 
 * example-1 Input: s = "leetcodecom" Output: 9 Explanation: An optimal solution
 * is to choose "ete" for the 1st subsequence and "cdc" for the 2nd subsequence.
 * The product of their lengths is: 3 * 3 = 9. Example 2:
 * 
 * Input: s = "bb" Output: 1 Explanation: An optimal solution is to choose "b"
 * (the first character) for the 1st subsequence and "b" (the second character)
 * for the 2nd subsequence. The product of their lengths is: 1 * 1 = 1. Example
 * 3:
 * 
 * Input: s = "accbcaxxcxx" Output: 25 Explanation: An optimal solution is to
 * choose "accca" for the 1st subsequence and "xxcxx" for the 2nd subsequence.
 * The product of their lengths is: 5 * 5 = 25.
 * 
 * 
 * Constraints:
 * 
 * 2 <= s.length <= 12 s consists of lowercase English letters only. Accepted
 * 19,516 Submissions 35,340
 * 
 * @author jojo Jun. 27, 2023 12:17:07 a.m.
 */
public class MaximumProductOfLengthOfTwoPalindromicSeq {
	public int maxProduct(String s) {
		int[] result = new int[] { 0 };

		dfs(s, 0, "", "", result);

		return result[0];
	}

	private void dfs(String s, int idx, String s1, String s2, int[] max) {
		if (idx == s.length()) {

			if (isPalindrome(s1) && isPalindrome(s2)) {
				max[0] = Math.max(max[0], s1.length() * s2.length());
			}

			return;
		}

		dfs(s, idx + 1, s1 + s.charAt(idx), s2, max);
		dfs(s, idx + 1, s1, s2 + s.charAt(idx), max);
		dfs(s, idx + 1, s1, s2, max);
	}

	private boolean isPalindrome(String s) {
		// if(memo.containsKey(s)){
		// return memo.get(s);
		// }

		int beg = 0, end = s.length() - 1;

		while (beg < end) {
			if (s.charAt(beg++) != s.charAt(end--)) {
				// memo.put(s, false);
				return false;
			}
		}

		// memo.put(s, true);
		return true;
	}
}
