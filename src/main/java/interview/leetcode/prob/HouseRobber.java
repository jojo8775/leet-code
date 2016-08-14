package interview.leetcode.prob;

/**
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed, the only constraint stopping you
 * from robbing each of them is that adjacent houses have security system
 * connected and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight without
 * alerting the police.
 * 
 * 
 * @author jojo
 *
 */
public class HouseRobber {
	public int rob(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		int[] dp = new int[nums.length];
		dp[0] = nums[0];

		for (int i = 1; i < nums.length; i++) {
			int maxTakingCurHouse = ((i - 2) < 0 ? 0 : dp[i - 2]) + nums[i];
			dp[i] = Math.max(maxTakingCurHouse, dp[i - 1]);
		}

		return dp[nums.length - 1];
	}
}
