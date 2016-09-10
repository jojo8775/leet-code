package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an unsorted array, find the maximum difference between the successive
 * elements in its sorted form.
 * 
 * Try to solve it in linear time/space.
 * 
 * Return 0 if the array contains less than 2 elements.
 * 
 * You may assume all elements in the array are non-negative integers and fit in
 * the 32-bit signed integer range.
 * 
 * @author jojo
 *
 */
public class MaximumGap {
	public int maximumGap(int[] nums) {
		nums = radixSort(nums);
		int max = Integer.MIN_VALUE;

		for (int i = 1; i < nums.length; i++) {
			max = Math.max(max, nums[i] - nums[i - 1]);
		}

		return max;
	}

	private int[] radixSort(int[] nums) {
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			max = Math.max(max, nums[i]);
		}

		int exp = 1;
		while (max / exp > 0) {
			Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

			for (int i = 0; i < nums.length; i++) {
				int idx = (nums[i] / exp) % 10;
				map.computeIfAbsent(idx, v -> new ArrayList<Integer>()).add(nums[i]);
			}

			exp *= 10;

			int arrIdx = 0;
			for (int i = 0; i <= 9; i++) {
				List<Integer> list = map.get(i);
				if (list != null) {
					for (int n : list) {
						nums[arrIdx++] = n;
					}
				}
			}
		}

		return nums;
	}

	public static void main(String[] args) {
		System.out.println(new MaximumGap().maximumGap(new int[] { 4, 23, 1, 3, 33, 1234, 212, 1, 2 }));
	}
}
