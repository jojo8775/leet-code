package interview.leetcode.prob;

/**
 * Given an array of n positive integers and a positive integer s, find the
 * minimal length of a subarray of which the sum ≥ s. If there isn't one, return
 * 0 instead.
 * 
 * For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has
 * the minimal length under the problem constraint.
 * 
 * @author jojo
 *
 */
public class MinimumSizeSubArraySum {
	public int minSubArrayLen(int s, int[] nums) {
		int j = 0, i = 0, len = Integer.MAX_VALUE, sum = 0;

		while (i < nums.length) {
			sum += nums[i++];

			while (sum >= s) {
				len = Math.min(len, (i - j));
				sum -= nums[j++];
			}
		}

		return len == Integer.MAX_VALUE ? 0 : len;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 4, 4 };
		System.out.println(new MinimumSizeSubArraySum().minSubArrayLen(4, arr));
	}
}
