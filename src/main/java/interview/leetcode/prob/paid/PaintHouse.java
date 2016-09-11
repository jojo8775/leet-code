package interview.leetcode.prob.paid;

/**
 * There are a row of n houses, each house can be painted with one of the three
 * colors: red, blue or green. The cost of painting each house with a certain
 * color is different. You have to paint all the houses such that no two
 * adjacent houses have the same color.
 * 
 * The cost of painting each house with a certain color is represented by a n x
 * 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with
 * color red; costs[1][2] is the cost of painting house 1 with color green, and
 * so on... Find the minimum cost to paint all houses.
 * 
 * @author jojo
 *
 */
public class PaintHouse {
	// idea is to make a greedy approach for each new house and then add
	// memorization for first and second min value
	public int minCost(int[][] costs) {
		int minCost1 = 0, minCost2 = 0, prevColor = -1;

		for (int i = 0; i < costs.length; i++) {
			int curColor = 0, curMin1 = -1, curMin2 = -1;
			for (int j = 0; j < 3; j++) {
				// handling usecase where there is min color index conflict with
				// adjacent house
				int cost = costs[i][j] + ((j != prevColor) ? minCost1 : minCost2);

				// storing the first and second min for each house
				if (curMin1 < 0 || curMin1 > cost) {
					curMin2 = curMin1;
					curMin1 = cost;
					curColor = j;
				} else if (curMin2 < 0 || curMin2 > cost) {
					curMin2 = cost;
				}
			}

			minCost1 = curMin1;
			minCost2 = curMin2;
			prevColor = curColor;
		}

		return minCost1;
	}
}
