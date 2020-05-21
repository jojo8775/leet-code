package interview.leetcode.prob;

import java.util.Arrays;

/**
 * There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 

Accepted
59,997
Submissions
122,617
 * @author jojo
 * May 21, 2020  12:23:30 AM
 */
public class MinimumNumberOfArrowsToBurstBalloons {
	public int findMinArrowShots(int[][] points) {
		if (points.length < 2) {
			return points.length;
		}

		// sorting based on the right most edge of each balloon in ascending order. 
		Arrays.sort(points, (a,b) -> a[1] - b[1]);

		int numOfArrows = 1;
		int arrowPos = points[0][1];

		for (int i = 1; i < points.length; i++) {
			// if arrow position is more than the next balloon left edge, then it means that 
			// balloon is already busted.
			if (arrowPos >= points[i][0]) {
				continue;
			}

			// using a new arrow for the non overlapping balloon edge.
			arrowPos = points[i][1];
			numOfArrows++;
		}

		return numOfArrows;
	}
}
