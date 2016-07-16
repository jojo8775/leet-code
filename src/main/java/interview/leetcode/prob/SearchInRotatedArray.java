package interview.leetcode.prob;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * @author jojo
 *
 */
public class SearchInRotatedArray {
	public int search(int[] nums, int target) {
		int beg = 0, end = nums.length - 1;

		while (beg <= end) {
			int mid = (beg + end) / 2;

			if (target == nums[mid]) {
				return mid;
			}

			if (nums[mid] == nums[beg]) {
				beg++;
			} else if (nums[mid] > nums[beg]) {
				if (target >= nums[beg] && target < nums[mid]) {
					end = mid - 1;
				} else {
					beg = mid + 1;
				}
			} else {
				if (target > nums[mid] && target <= nums[end]) {
					beg = mid + 1;
				} else {
					end = mid - 1;
				}
			}
		}

		return -1;
	}
}
