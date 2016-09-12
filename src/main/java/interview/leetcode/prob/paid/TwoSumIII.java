package interview.leetcode.prob.paid;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a TwoSum class. It should support the following
 * operations: add and find.
 * 
 * add - Add the number to an internal data structure. find - Find if there
 * exists any pair of numbers which sum is equal to the value.
 * 
 * For example, add(1); add(3); add(5); find(4) -> true find(7) -> false
 * 
 * @author jojo
 *
 */
public class TwoSumIII {
	private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	// Add the number to an internal data structure.
	public void add(int number) {
		map.computeIfPresent(number, (k, v) -> v + 1);
		map.computeIfAbsent(number, v -> 1);
	}

	// Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
		boolean isEven = (value % 2 == 0);

		if (isEven) {
			Integer count = map.get(value / 2);

			if (count != null && count >= 2) {
				return true;
			}
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (isEven && entry.getKey() == value / 2) {
				continue;
			}

			int diff = value - entry.getKey();
			if (map.containsKey(diff)) {
				return true;
			}
		}

		return false;
	}
}
