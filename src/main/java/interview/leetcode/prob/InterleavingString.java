package interview.leetcode.prob;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * 
 * For example, Given: s1 = "aabcc", s2 = "dbbca",
 * 
 * When s3 = "aadbbcbcac", return true. When s3 = "aadbbbaccc", return false.
 * 
 * @author jojo
 *
 */
public class InterleavingString {
	public boolean isInterleave(String s1, String s2, String s3) {
		int n1 = s1.length(), n2 = s2.length();

		if (n1 + n2 != s3.length()) {
			return false;
		}

		boolean[][] arr = new boolean[n1 + 1][n2 + 1];
		arr[0][0] = true;

		// assigning zeroth row
		for (int i = 1; i < arr[0].length; i++) {
			arr[0][i] = arr[0][i - 1] && s3.charAt(i - 1) == s2.charAt(i - 1);
		}

		// assigning zeroth col
		for (int i = 1; i < arr.length; i++) {
			arr[i][0] = arr[i - 1][0] && s3.charAt(i - 1) == s1.charAt(i - 1);
		}

		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[i].length; j++) {
				char ch = s3.charAt(i + j - 1);
				arr[i][j] = (ch == s1.charAt(i - 1) && arr[i - 1][j]) || (ch == s2.charAt(j - 1) && arr[i][j - 1]);
			}
		}

		return arr[s1.length()][s2.length()];
	}
}
