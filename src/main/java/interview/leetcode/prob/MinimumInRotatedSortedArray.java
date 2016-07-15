package interview.leetcode.prob;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * @author jojo
 *
 */
public class MinimumInRotatedSortedArray {
	public int findMin(int[] nums) {
		int beg = 0, end = nums.length - 1, result = 0;

		while (beg <= end) {
			if (beg == end) {
				return nums[beg];
			}

			int mid = (beg + end) / 2;
			int prev = (mid - 1) >= beg ? nums[mid - 1] : nums[beg];
			int next = (mid + 1) <= end ? nums[mid + 1] : nums[end];

			if (prev >= nums[mid] && next >= nums[mid]) {
				result = nums[mid];
				break;
			}

			if (nums[mid] > nums[end]) {
				beg = mid + 1;
			} else {
				end = mid;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 1 };
		System.out.println(new MinimumInRotatedSortedArray().findMin(arr));
	}
}
