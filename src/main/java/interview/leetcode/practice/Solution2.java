package interview.leetcode.practice;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {
	public static void main(String[] args) {
		boolean result = new Solution2().checkSubarraySum(new int[] {0,1,1,0}, 0);
		System.out.println(result);
	}

	public boolean checkSubarraySum(int[] nums, int k) {

		// idea is x + n*k mod k = x
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		// this is done to handle input like nums = [0,0], k = 0
		map.put(0, -1);

		int sumSoFar = 0;
		for (int i = 0; i < nums.length; i++) {
			sumSoFar += nums[i];

			// to avoid /0
			if (k != 0) {
				sumSoFar %= k;
			}

			// if there is a same mod and the space between is more than 1 then result is
			// found
			if (map.containsKey(sumSoFar)) {
				int prevIdx = map.get(sumSoFar);
				if (i - prevIdx > 1) {
					return true;
				}
			} else {
				map.put(sumSoFar, i);
			}
		}

		return false;
	}
}
