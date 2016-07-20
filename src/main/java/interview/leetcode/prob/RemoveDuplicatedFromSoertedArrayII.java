package interview.leetcode.prob;

/**
 * Follow up for "Remove Duplicates": What if duplicates are allowed at most
 * twice?
 * 
 * For example, Given sorted array nums = [1,1,1,2,2,3],
 * 
 * Your function should return length = 5, with the first five elements of nums
 * being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new
 * length.
 * 
 * @author jojo
 *
 */
public class RemoveDuplicatedFromSoertedArrayII {
	public int removeDuplicates(int[] nums) {
		int count = 0, prev = 0;

		for (int i = 1; i < nums.length; i++) {
			if (nums[prev] == nums[i] && count < 1) {
				count++;
				nums[++prev] = nums[i];
			} else if (nums[prev] != nums[i]) {
				count = 0;
				nums[++prev] = nums[i];
			}
		}

		return ++prev;
	}
}
