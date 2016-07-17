package interview.leetcode.prob;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Note:
You can assume that you can always reach the last index.
 * @author jojo
 *
 */
public class JumpGameII {
	public int jump(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return 0;
		}

		// using greedy approach
		int bal = nums[0], curBal = bal, steps = 1;

		for (int i = 1; i <= bal; i++) {
			if (i == nums.length - 1) {
				return steps;
			}

			curBal = Math.max(curBal, i + nums[i]);

			if (i == bal) {
				if (curBal >= bal) {
					bal = curBal;
					steps++;
				}
				else{
					return -1;
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		int[] nums = { 2, 3, 1, 1, 1, 4 };
		System.out.println(new JumpGameII().jump(nums));
	}
}
