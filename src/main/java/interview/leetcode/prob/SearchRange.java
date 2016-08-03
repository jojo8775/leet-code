package interview.leetcode.prob;

/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
 * @author jojo
 *
 */
public class SearchRange {
	public int[] searchRange(int[] nums, int target) {
		int[] result = { -1, -1 };
		int index = find(nums, target, 0, nums.length - 1);

		if (nums[index] == target) {
			result[0] = index;

			result[1] = find(nums, target + 1, 0, nums.length - 1);
			if (nums[result[1]] != target) {
				result[1] -= 1;
			}
		}

		return result;
	}

	private int find(int[] nums, int target, int beg, int end) {
		while (beg < end) {
			int mid = beg + (end - beg) / 2;

			if (nums[mid] < target) {
				beg = mid + 1;
			} else {
				end = mid;
			}
		}

		return beg;
	}

	public static void main(String[] args) {
		int[] nums = { 0, 2 };
		int[] result = new SearchRange().searchRange(nums, 0);
		System.out.println(result[0]);
		System.out.println(result[1]);
	}
}
