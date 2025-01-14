package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given the array houses where houses[i] is the location of the ith house along a street and an integer k, allocate k mailboxes in the street.

Return the minimum total distance between each house and its nearest mailbox.

The test cases are generated so that the answer fits in a 32-bit integer.

 

Example 1:


Input: houses = [1,4,8,10,20], k = 3
Output: 5
Explanation: Allocate mailboxes in position 3, 9 and 20.
Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5 
Example 2:


Input: houses = [2,3,5,12,18], k = 2
Output: 9
Explanation: Allocate mailboxes in position 3 and 14.
Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.
 

Constraints:

1 <= k <= houses.length <= 100
1 <= houses[i] <= 104
All the integers of houses are unique.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
27.6K
Submissions
49.6K
Acceptance Rate
55.5%
 * 
 * Jan 9, 2025 - 10:24:20 PM Jojo
 */
public class AllocateMailBoxes {
	public int minDistance(int[] houses, int k) {
		Arrays.sort(houses);

		int[][] precomputedCost = new int[houses.length][houses.length];

		for (int i = 0; i < houses.length; i++) {
			for (int j = i; j < houses.length; ++j) {
				int mid = i + (j - i) / 2;
				int median = houses[mid];

				for (int l = i; l <= j; ++l) {
					precomputedCost[i][j] += Math.abs(median - houses[l]);
				}
			}
		}

		return dp(houses, 0, k, 0, new Integer[houses.length][k], precomputedCost);
	}

	private int dp(int[] houses, int idx, int k, int curK, Integer[][] memo, int[][] precomputedCost) {
		if (idx == houses.length && k == curK) {
			return 0;
		}

		if (idx == houses.length || k == curK) {
			return Integer.MAX_VALUE;
		}

		if (memo[idx][curK] != null) {
			return memo[idx][curK];
		}

		int ans = Integer.MAX_VALUE;

		// idx <-> i is the window and median is always the best spot to put a mailbox
		for (int i = idx; i < houses.length; i++) {
			int cost = 0;

			// this cost is computed everytime. this can be improved by
			// precomputing it.
			/*
			 * int mid = idx + (i - idx)/2;
			 * 
			 * int median = houses[mid];
			 * 
			 * for(int j=idx; j<=i; j++){ cost += Math.abs(houses[j] - median); }
			 */

			cost = precomputedCost[idx][i];

			int next = dp(houses, i + 1, k, curK + 1, memo, precomputedCost);

			if (next != Integer.MAX_VALUE) {
				ans = Math.min(ans, cost + next);
			}
		}

		return memo[idx][curK] = ans;
	}
}
