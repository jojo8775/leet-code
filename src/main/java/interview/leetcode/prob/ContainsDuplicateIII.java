package interview.leetcode.prob;

import java.util.TreeSet;

/**
 * Given an array of integers, find out whether there are two distinct indices i
 * and j in the array such that the difference between nums[i] and nums[j] is at
 * most t and the difference between i and j is at most k.
 * 
 * @author jojo
 *
 */
public class ContainsDuplicateIII {
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (nums == null || nums.length == 0 || k <= 0) {
			return false;
		}

		TreeSet<Integer> set = new TreeSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			Integer floor = set.floor(nums[i] + t);
			Integer ceiling = set.ceiling(nums[i] - t);

			if ((floor != null && floor >= nums[i]) || (ceiling != null && ceiling <= nums[i])) {
				return true;
			}

			set.add(nums[i]);

			if (i >= k) {
				set.remove(nums[i - k]);
			}
		}

		return false;
	}

	public static void main(String[] args) {
		int[] arr = { -1, -1 };
		System.out.println(new ContainsDuplicateIII().containsNearbyAlmostDuplicate(arr, 1, -1));
	}
}
