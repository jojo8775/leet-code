package interview.leetcode.practice;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {

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

//	public int maxPoints(Point[] points) {
//		if (points.length < 3) {
//			return points.length;
//		}
//
//		int maxCount = 1;
//		for (int i = 0; i < points.length - 1; i++){
//			int verticals = 1, duplicates = 0, count = 1;
//			Map<Double, Integer> slopeMap = new HashMap<>();
//
//			for (int j = i + 1; j < points.length; j++) {
//				if (points[i].x == points[j].x && points[i].y == points[j].y) {
//					duplicates++;
//				} 
//				else if (points[i].y == points[j].y) {
//					verticals++;
//					count = Math.max(count, verticals);
//				} 
//				else {
//					double slope = (1.0 * (points[i].y - points[j].y)) / (points[i].x - points[j].x);
//					slopeMap.put(slope, slopeMap.getOrDefault(slope, 1) + 1);
//					count = Math.max(count, slopeMap.get(slope));
//				}
//			}
//			
//			maxCount = Math.max(maxCount, count + duplicates);
//		}
//
//		return maxCount;
//	}

	private static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
