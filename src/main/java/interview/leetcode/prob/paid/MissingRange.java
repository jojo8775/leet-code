package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array where the range of elements are [lower, upper]
 * inclusive, return its missing ranges.
 * 
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2",
 * "4->49", "51->74", "76->99"].
 * 
 * Show Company Tags Show Tags Show Similar Problems
 * 
 * @author jojo
 *
 */
public class MissingRange {
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		// minus 1 as we always pre increment count
		int count = lower - 1;

		List<String> result = new ArrayList<String>();
		for (int i = 0; i < nums.length; i++) {
			if (++count != nums[i]) {
				result.add(createMissingRange(count, nums[i]));
				count = nums[i];
			}
		}

		if (count < upper) {
			// adding 1 to upper as createMissingRange, end is exclusive
			result.add(createMissingRange(count + 1, upper + 1));
		}

		return result;
	}

	private String createMissingRange(int i, int j) {
		StringBuilder sb = new StringBuilder();
		sb.append(i);

		if (i + 1 != j) {
			sb.append("->").append(j - 1);
		}

		return sb.toString();
	}
}
