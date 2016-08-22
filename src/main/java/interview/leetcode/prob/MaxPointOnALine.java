package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on
 * the same straight line.
 * 
 * @author jojo
 *
 */
public class MaxPointOnALine {
	// this can be computed by considering the slot, duplicate and vertical
	// points.
	public int maxPoints(Point[] points) {
		if (points.length == 0) {
			return 0;
		}

		int maxCount = 0;
		for (int i = 0; i < points.length; i++) {
			Map<Double, Integer> slopeMap = new HashMap<Double, Integer>();
			int verticals = 0, duplicates = 1;
			for (int j = i + 1; j < points.length; j++) {
				if (points[i].x == points[j].x) {
					// calculating the veticals
					if (points[i].y != points[j].y) {
						verticals++;
					}
					// calculating duplicates
					else {
						duplicates++;
					}
				}

				// calculating slope
				else {
					double slope = points[i].y == points[j].y ? 0.0
							: (1.0 * (points[j].y - points[i].y)) / (points[j].x - points[i].x);
					slopeMap.computeIfPresent(slope, (k, v) -> v + 1);
					slopeMap.computeIfAbsent(slope, v -> 1);
				}
			}

			// calculating current max points
			for (int count : slopeMap.values()) {
				if ((count + duplicates) > maxCount) {
					maxCount = count + duplicates;
				}
			}

			// considering the verticals
			maxCount = Math.max(maxCount, verticals + duplicates);
		}

		return maxCount;
	}

	private static class Point {
		int x;
		int y;

		Point() {
			x = 0;
			y = 0;
		}

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}
}
