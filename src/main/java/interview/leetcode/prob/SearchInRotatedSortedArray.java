package interview.leetcode.prob;

/**
 * Follow up for "Search in Rotated Sorted Array": What if duplicates are
 * allowed?
 * 
 * Would this affect the run-time complexity? How and why?
 * 
 * Write a function to determine if a given target is in the array.
 * 
 * 
 * @author jojo
 *
 */
public class SearchInRotatedSortedArray {
	public boolean search(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return false;

		int beg = 0, end = nums.length - 1;
		while (beg <= end) {
			int mid = (end + beg) / 2;

			if (nums[mid] == target) {
				return true; // find target
			}

			if (nums[mid] == nums[beg]) {
				beg++; // find a duplicates num
			} else if (nums[mid] > nums[beg]) {
				// treating first half
				if (target >= nums[beg] && target < nums[mid]) {
					end = mid - 1;
				} else {
					beg = mid + 1;
				}
			} else {
				// treating second half
				if (target > nums[mid] && target <= nums[end]) {
					beg = mid + 1;
				} else {
					end = mid - 1;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] arr1 = { 1, 2, 3, 4, 5 };
		System.out.println(new SearchInRotatedSortedArray().search(arr1, 5));

		int[] arr2 = { 5, 1, 2, 3, 4 };
		System.out.println(new SearchInRotatedSortedArray().search(arr2, 5));

		int[] arr3 = { 4, 5, 1, 2, 3 };
		System.out.println(new SearchInRotatedSortedArray().search(arr3, 5));

		int[] arr4 = { 3, 4, 5, 1, 2 };
		System.out.println(new SearchInRotatedSortedArray().search(arr4, 5));

		int[] arr5 = { 2, 3, 4, 5, 1 };
		System.out.println(new SearchInRotatedSortedArray().search(arr5, 5));

		int[] arr6 = { 2, 3, 4, 4, 4, 4, 4, 4, 5, 1 };
		System.out.println(new SearchInRotatedSortedArray().search(arr6, 5));

		int[] arr7 = { 3, 1 };
		System.out.println(new SearchInRotatedSortedArray().search(arr7, 1));

		int[] arr8 = { 1, 3, 1, 1, 1 };
		System.out.println(new SearchInRotatedSortedArray().search(arr8, 3));

	}
}
