package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

import interview.leetcode.practice.MaxPointsOnALine.Point;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on
 * the same straight line.
 * 
 * @author jojo
 *
 */
public class MaxPointOnALine {
	public int maxPoints(Point[] points) {
		if (points.length < 3) {
			return points.length;
		}

		int maxCount = 1; // begin with one point.
		for (int i = 0; i < points.length - 1; i++) {
			Map<Double, Integer> slopeMap = new HashMap<>();
			// if there is only one point, it can contribute to horizontal.
			int horizontals = 1; 
			// this is current count and since we start with one node.
			int currentCount = 1; 
			// since there is only one node at start, no duplicates
			int duplicates = 0; 

			for (int j = i + 1; j < points.length; j++) {
				// if both the points are same
				if ((points[i].x == points[j].x) && (points[i].y == points[j].y)) {
					duplicates++;
				}
				// if they lie on the same x-axis
				else if (points[i].y == points[j].y) {
					horizontals++;
					// keeping track of current max count
					currentCount = Math.max(horizontals, currentCount);
				} else {
					// calculating slope
					double slope = 1.0 * (points[i].x - points[j].x) / (points[i].y - points[j].y) + 0.0;
					// if there is no entry then adding 2 else incrementing by 1
					slopeMap.put(slope, slopeMap.getOrDefault(slope, 1) + 1);
					
					// keeping track of the current max count
					currentCount = Math.max(slopeMap.get(slope), currentCount);
				}
			}

			// considering max of points on the same x-axis VS with in a same slope.
			maxCount = Math.max(maxCount, currentCount + duplicates);
		}
		
		return maxCount;
	}
	
	
	// this can be computed by considering the slot, duplicate and vertical
	// points.
	public int maxPoints_old(Point[] points) {
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
