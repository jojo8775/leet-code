package interview.leetcode.prob;

public class ShortestCommonSupersequence {
	public String shortestCommonSupersequence(String str1, String str2) {
		int[][] dp = new int[str1.length() + 1][str2.length() + 1];

		for (int i = 1; i < dp.length; i++) {
			char ch1 = str1.charAt(i - 1);

			for (int j = 1; j < dp[0].length; j++) {
				if (ch1 == str2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		int i = dp.length - 1, j = dp[0].length - 1;

		while (i > 0 && j > 0) {
			if (dp[i - 1][j] == dp[i][j]) {
				sb.append(str1.charAt(i - 1));
				i--;
			} else if (dp[i][j - 1] == dp[i][j]) {
				sb.append(str2.charAt(j - 1));
				j--;
			} else {
				// if the diagonal is 1 less than current that means both the chars are same so
				// take any
				sb.append(str1.charAt(i - 1));
				i--;
				j--;
			}
		}

		while (i-- > 0) {
			sb.append(str1.charAt(i));
		}

		while (j-- > 0) {
			sb.append(str2.charAt(j));
		}

		return sb.reverse().toString();
	}
}
