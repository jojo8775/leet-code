package interview.leetcode.prob.paid;

/**
 * There are a row of n houses, each house can be painted with one of the k
 * colors. The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the
 * same color.
 * 
 * The cost of painting each house with a certain color is represented by a n x
 * k cost matrix. For example, costs[0][0] is the cost of painting house 0 with
 * color 0; costs[1][2] is the cost of painting house 1 with color 2, and so
 * on... Find the minimum cost to paint all houses.
 * 
 * Note: All costs are positive integers.
 * 
 * Follow up: Could you solve it in O(nk) runtime?
 * 
 * @author jojo
 *
 */
public class PaintHouseII {
	// This problem takes a greedy approach with memorization logic
	public int minCostII(int[][] costs) {
		int prevColor = -1, prevMin1 = 0, prevMin2 = 0;
		for (int i = 0; i < costs.length; i++) {
			int curColor = -1, curMin1 = -1, curMin2 = -1;

			for (int j = 0; j < costs[i].length; j++) {
				// takes care when there is a min cost color index conflict
				int cost = costs[i][j] + ((j != prevColor) ? prevMin1 : prevMin2);

				if (curMin1 < 0 || curMin1 > cost) {
					curMin2 = curMin1;
					curMin1 = cost;
					curColor = j;
				} else if (curMin2 < 0 || curMin2 > cost) {
					curMin2 = cost;
				}
			}

			prevMin1 = curMin1;
			prevMin2 = curMin2;
			prevColor = curColor;
		}

		return prevMin1;
	}
}
