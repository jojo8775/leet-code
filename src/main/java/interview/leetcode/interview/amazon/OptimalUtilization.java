package interview.leetcode.interview.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given 2 lists a and b. Each element is a pair of integers where the first
 * integer represents the unique id and the second integer represents a value.
 * Your task is to find an element from a and an element form b such that the
 * sum of their values is less or equal to target and as close to target as
 * possible. Return a list of ids of selected elements. If no pair is possible,
 * return an empty list.
 * 
 * @author jojo Apr 29, 2020 10:33:23 PM
 */
public class OptimalUtilization {
	public List<int[]> findOptimalUtilization(int[][] a, int[][] b, int target) {
		Arrays.sort(a, (x, y) -> x[1] - y[1]);
		Arrays.sort(b, (x, y) -> x[1] - y[1]);

		int max = Integer.MIN_VALUE;

		List<int[]> result = new ArrayList<>();

		int left = 0, right = b.length - 1;
		while (left < a.length && right >= 0) {
			int val = a[left][1] + b[right][1];
			if (val > target) {
				right--;
			} else {
				if (max < val) {
					max = val;
					result.clear();
				}

				result.add(new int[] { a[left][1], b[right][1] });

				int idx = right - 1;
				while (idx >= 0 && b[idx][1] == b[right][1]) {
					result.add(new int[] { a[left][1], b[idx][1] });
					idx--;
				}
			}
		}

		return result;
	}
}
