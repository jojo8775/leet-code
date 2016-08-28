package interview.leetcode.prob;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a
 * number on it represented by array nums. You are asked to burst all the
 * balloons. If the you burst balloon i you will get nums[left] * nums[i] *
 * nums[right] coins. Here left and right are adjacent indices of i. After the
 * burst, the left and right then becomes adjacent.
 * 
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * 
 * Note: (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore
 * you can not burst them. (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 
 * Example:
 * 
 * Given [3, 1, 5, 8]
 * 
 * Return 167
 * 
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> [] coins = 3*1*5 + 3*5*8 +
 * 1*3*8 + 1*8*1 = 167
 * 
 * @author jojo
 *
 */
public class BurstBalloons {
	public int maxCoins(int[] nums) {
		int[] iNums = new int[nums.length + 2];
		int length = 1;

		// ignoring any 0 val
		for (int i : nums) {
			if (i > 0) {
				iNums[length++] = i;
			}
		}

		// marking first and last as 1 as per the problem. They are dummy and
		// dont add to the result
		iNums[0] = 1;
		iNums[length++] = 1;

		// two dimentional array to store all the possible sub array sum
		int[][] dp = new int[length][length];

		for (int k = 2; k < length; k++) {
			for (int left = 0; left < length - k; left++) {
				int right = left + k;
				for (int i = left + 1; i < right; i++) {
					// since after a ballon burst left and right becomes
					// adjacent
					dp[left][right] = Math.max(dp[left][right],
							iNums[left] * iNums[i] * iNums[right] + dp[left][i] + dp[i][right]);
				}
			}
		}

		return dp[0][length - 1];
	}

	public int maxBalloons(int[] nums) {
		int[][] dp = new int[nums.length][nums.length];

		// Considering only one element in the sub array
		for (int i = 0; i < nums.length; i++) {
			int left = i == 0 ? 1 : nums[i - 1];
			int right = i == nums.length - 1 ? 1 : nums[i + 1];
			dp[i][i] = left * nums[i] * right;
		}

		// K represents the length of sub array
		for (int k = 1; k < nums.length; k++) {
			for (int i = 0; i < nums.length - k; i++) {
				for (int j = i; j <= i + k; j++) {
					// value of the left sub array
					int leftSubArray = j == 0 ? 0 : dp[i][j - 1];
					// value of the right sub array
					int rightSubArray = j == nums.length - 1 ? 0 : dp[j + 1][i + k];

					// next available left value
					int leftVal = i == 0 ? 1 : nums[i - 1];
					// next available right value
					int rightVal = i + k + 1 == nums.length ? 1 : nums[i + k + 1];

					// maximum of all the possible combinations
					// The idea is to find the last balloon to burst for each
					// sub array to obtain the maximum result
					dp[i][i + k] = Math.max(dp[i][i + k],
							leftSubArray + rightSubArray + (leftVal * nums[j] * rightVal));
				}
			}
		}

		return dp[0][nums.length - 1];
	}

	public static void main(String[] args) {
		int[] arr = { 3, 1, 5, 8 };
		// System.out.println(new BurstBalloons().maxCoins(arr));
		System.out.println(new BurstBalloons().maxBalloons(arr));
	}
}
