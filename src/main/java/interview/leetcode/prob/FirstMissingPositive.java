package interview.leetcode.prob;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 * 
 * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 * 
 * Your algorithm should run in O(n) time and uses constant space.
 * 
 * @author jojo
 *
 */
public class FirstMissingPositive {
	public int firstMissingPositive(int[] nums) {
		// implements bucket sort
		for (int i = 0; i < nums.length; i++) {
			while (nums[i] != i + 1) {
				// nums[i] is outside range
				if (nums[i] <= 0 || nums[i] >= nums.length) {
					break;
				}

				// taking care of duplicate numbers
				if (nums[i] == nums[nums[i] - 1]) {
					break;
				}

				// swap numbers
				int temp = nums[i];
				nums[i] = nums[temp - 1];
				nums[temp - 1] = temp;
			}
		}

		// finding first missing positive number
		int idx = 0;
		while (idx < nums.length) {
			if (nums[idx] != idx + 1) {
				return idx + 1;
			}

			idx++;
		}

		return nums.length + 1;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 1000 };
		System.out.println(new FirstMissingPositive().firstMissingPositive(arr));
	}
}
