package interview.leetcode.prob;

import java.util.Arrays;

/**
 * You are given an integer array nums.

You should move each element of nums into one of the two arrays A and B such that A and B are non-empty, and average(A) == average(B).

Return true if it is possible to achieve that and false otherwise.

Note that for an array arr, average(arr) is the sum of all the elements of arr over the length of arr.

 

Example 1:

Input: nums = [1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have an average of 4.5.
Example 2:

Input: nums = [3,1]
Output: false
 

Constraints:

1 <= nums.length <= 30
0 <= nums[i] <= 104
Accepted
19,050
Submissions
70,728
 * @author jojo
 * Mar 21, 2021  5:12:04 PM
 */
public class SplitArrayWithSameAverage {
	public boolean splitArraySameAverage(int[] nums) {
		Arrays.sort(nums);

		int sum = 0;
		for (int i : nums) {
			sum += i;
		}

		return backtrack(nums, 0, 0, 0, sum, nums.length);
	}

	public boolean backtrack(int[] nums, int idx, int aSum, int aLen, int bSum, int bLen) {

		if (aLen != 0 && bLen != 0 && (double) aSum / aLen == (double) bSum / bLen) {
			return true;
		}
		for (int i = idx; i < nums.length; i++) {
			// this is to avoid computing duplicate combinations.
			if (i != idx && nums[i] == nums[i - 1]) {
				continue;
			}

			if (backtrack(nums, i + 1, aSum + nums[i], aLen + 1, bSum - nums[i], bLen - 1)) {
				return true;
			}
		}
		return false;
	}
}
