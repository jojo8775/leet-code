package interview.leetcode.prob;

/**
 * Given a string S and a string T, count the number of distinct subsequences of
 * T in S.
 * 
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ACE" is a
 * subsequence of "ABCDE" while "AEC" is not).
 * 
 * Here is an example: S = "rabbbit", T = "rabbit"
 * 
 * Return 3.
 * 
 * @author jojo
 *
 */
public class DistinctSubsequence {
	public int numDistinct(String s, String t) {
		// no result when either one of them are null
		if (s == null || t == null) {
			return 0;
		}

		// there will be no result if T is bigger than S
		if (t.length() > s.length()) {
			return 0;
		}

		int[][] grid = new int[t.length() + 1][s.length() + 1];

		// when t is "" it can always be a subsequence of the given string
		for (int i = 0; i <= s.length(); i++) {
			grid[0][i] = 1;
		}

		for (int i = 1; i <= t.length(); i++) {
			for (int j = i; j <= s.length(); j++) {
				if (s.charAt(j - 1) == t.charAt(i - 1)) {
					// addition of T contributing to new charater of S and not
					// contributing
					grid[i][j] = grid[i - 1][j - 1] + grid[i][j - 1];
				} else {
					grid[i][j] = grid[i][j - 1];
				}
			}
		}

		return grid[t.length()][s.length()];
	}
}
