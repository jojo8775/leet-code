package interview.leetcode.interview.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of positive integers nums and an int target, return indices of the two numbers such that they add up to a target - 30.

Conditions:

You will pick exactly 2 numbers.
You cannot pick the same element twice.
If you have muliple pairs, select the pair with the largest number.
 * @author jojo
 * Apr 29, 2020  11:17:44 PM
 */
public class FindPairWithGivenSum {
	public int[] findPair(int[] a, int target) {
		Map<Integer, Integer> map = new HashMap<>();

		int[] result = null;

		for (int i = 0; i < a.length; i++) {
			int val = target - 30 - a[i];
			if (map.containsKey(val)) {
				result = new int[] { map.get(val), i };
			}
			map.put(a[i], i);
		}

		return result;
	}
}
