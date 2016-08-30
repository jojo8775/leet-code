package interview.leetcode.prob;

/**
 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.

Example 1:
nums = [1, 3], n = 6
Return 1.

Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.

Example 2:
nums = [1, 5, 10], n = 20
Return 2.
The two patches can be [2, 4].

Example 3:
nums = [1, 2, 2], n = 5
Return 0.
 * @author jojo
 *
 */
public class PatchingArray {
	// idea is 1, 2 can make atmost 3
	// 1,2,3 can make atmost 6
	// so for 1,2,3 to make 7, patch should be 4 which will extend the range
	// till 8
	public int minPatches(int[] nums, int n) {
		int patch = 0, idx = 0;
		long numberCanBeReached = 1;
		while (numberCanBeReached <= n) {
			if (idx < nums.length) {
				int nextExist = nums[idx];

				// represents existing number which can potientally contribute
				// later
				if (nextExist > numberCanBeReached) {
					patch++;
					numberCanBeReached += numberCanBeReached;
				}

				// add the existing number to the range which can be reached.
				else {
					numberCanBeReached += nextExist;
					idx++;
				}
			}

			// if all the exiting numbers are consumed keep increasing the range
			// to its maximum reach
			else {
				numberCanBeReached += numberCanBeReached;
				patch++;
			}
		}

		return patch;
	}
}
