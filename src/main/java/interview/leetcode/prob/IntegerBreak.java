package interview.leetcode.prob;

/**
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58.

Hint:

There is a simple O(n) solution to this problem.
You may check the breaking results of n ranging from 7 to 10 to discover the regularities.

 * @author jojo
 *
 */
public class IntegerBreak {
	public int integerBreak(int n) {
		// this can be solved by dynamic programming memorization
		int[] dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j < i + 1; j++) {
				if (i + j <= n) {
					// using the memorization of the dynamic programming
					dp[i + j] = Math.max(Math.max(dp[i], i) * Math.max(dp[j], j), dp[i + j]);
				}
			}
		}

		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(new IntegerBreak().integerBreak(5));
	}
}
