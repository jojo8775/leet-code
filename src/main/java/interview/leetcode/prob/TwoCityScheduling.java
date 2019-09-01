package interview.leetcode.prob;

import java.util.Arrays;

/**
 * There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.

 

Example 1:

Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation: 
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 

Note:

1 <= costs.length <= 100
It is guaranteed that costs.length is even.
1 <= costs[i][0], costs[i][1] <= 1000
Accepted
12,986
Submissions
23,949
 * @author jojo
 * Sep 1, 2019 3:06:24 AM
 */
public class TwoCityScheduling {
	public int twoCitySchedCost(int[][] costs) {

		// sorting based on the difference of flying cost to A and B.
		Arrays.sort(costs, (a, b) -> {
			return (a[1] - a[0]) - (b[1] - b[0]);
		});

		int totalCost = 0, len = costs.length, mid = len / 2;

		for (int i = 0; i < len; i++) {
			// since need tp divide 2N people equally,
			if (i < mid) {
				// taking the highest price for scenario where the different between high and
				// low is less.
				totalCost += costs[i][1];
			} else {
				// taking the lowest price for scenaroi where the diffferent between high and
				// low is more.
				// e.g if input is : [20, 30], [50, 150]
				// the best way to divide these two people will be by taking high of first entry
				// and low of second entry.
				totalCost += costs[i][0];
			}
		}

		return totalCost;
	}
}
