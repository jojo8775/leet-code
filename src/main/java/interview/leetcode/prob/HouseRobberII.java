package interview.leetcode.prob;

/**
 * Note: This is an extension of House Robber.
 * 
 * After robbing those houses on that street, the thief has found himself a new
 * place for his thievery so that he will not get too much attention. This time,
 * all houses at this place are arranged in a circle. That means the first house
 * is the neighbor of the last one. Meanwhile, the security system for these
 * houses remain the same as for those in the previous street.
 * 
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight without
 * alerting the police.
 * 
 * Credits: Special thanks to @Freezen for adding this problem and creating all
 * test cases.
 * 
 * 
 * @author jojo
 *
 */
public class HouseRobberII {
	public int rob(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		if (nums.length == 1) {
			return nums[0];
		}

		int firstInc = nums[0]; // includes first and current
		int firstExc = 0; // includes first and excludes current
		int nonFirstInc = 0; // excludes first andbut includes curent
		int nonFirstExc = 0; // excludes first and current

		for (int i = 1; i < nums.length; i++) {
			int prev = firstInc;
			firstInc = firstExc + nums[i];
			firstExc = Math.max(prev, firstExc);

			prev = nonFirstInc;
			nonFirstInc = nonFirstExc + nums[i];
			nonFirstExc = Math.max(prev, nonFirstExc);
		}

		// minimum by either taking first or by not taking it
		int maxInc = Math.min(firstInc, nonFirstInc);

		return Math.max(maxInc, firstExc);
	}
}
