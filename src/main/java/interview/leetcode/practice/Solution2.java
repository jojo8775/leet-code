package interview.leetcode.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution2 {
	public static void main(String[] args) {
		int result = new Solution2()
				.earliestAcq(
						new int[][] {{20190101,0,1},{20190104,3,4},{20190107,2,3},{20190211,1,5},{20190224,2,4},{20190301,0,3},{20190312,1,2},{20190322,4,5}},
						6);
		System.out.println(result);
	}

	public int earliestAcq(int[][] logs, int N) {
		Arrays.sort(logs, (a, b) -> a[0] - b[0]);

		int[] arr = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			arr[i] = i;
		}

		int count = N, result = -1;

		for (int[] log : logs) {
			int p1 = findRoot(log[1], arr);
			int p2 = findRoot(log[2], arr);

			if (p1 != p2) {
				arr[p1] = arr[p2];
				System.out.println("count " + count);
				count--;
			}

			if (count == 1) {
				result = log[0];
				break;
			}
		}

		return result;
	}

	private int findRoot(int n, int[] arr) {
		while (n != arr[n]) {
			n = arr[n];
		}

		return n;
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
