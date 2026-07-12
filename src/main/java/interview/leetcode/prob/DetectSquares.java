package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a stream of points on the X-Y plane. Design an algorithm that:

Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
Given a query point, counts the number of ways to choose three points from the data structure such that the three points and the query point form an axis-aligned square with positive area.
An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.

Implement the DetectSquares class:

DetectSquares() Initializes the object with an empty data structure.
void add(int[] point) Adds a new point point = [x, y] to the data structure.
int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.
 

Example 1:


Input
["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
[[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
Output
[null, null, null, null, 1, 0, null, 2]

Explanation
DetectSquares detectSquares = new DetectSquares();
detectSquares.add([3, 10]);
detectSquares.add([11, 2]);
detectSquares.add([3, 2]);
detectSquares.count([11, 10]); // return 1. You can choose:
                               //   - The first, second, and third points
detectSquares.count([14, 8]);  // return 0. The query point cannot form a square with any points in the data structure.
detectSquares.add([11, 2]);    // Adding duplicate points is allowed.
detectSquares.count([11, 10]); // return 2. You can choose:
                               //   - The first, second, and third points
                               //   - The first, third, and fourth points
 

Constraints:

point.length == 2
0 <= x, y <= 1000
At most 3000 calls in total will be made to add and count.
 
Seen this question in a real interview before?
1/6
Yes
No
Accepted
110,607/210.1K
Acceptance Rate
52.6%
 * 
 * chiranjeebnandy
 * Jun 7, 2026  2026  6:20:31 PM
 */
public class DetectSquares {
	class DetectSquaresImpl {

	    List<int[]> points = new ArrayList<>();
	    // int[][] grid = new int[1001][1001];

	    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

	    public DetectSquaresImpl() {
	        
	    }
	    
	    public void add(int[] point) {
	        // grid[point[0]][point[1]]++;

	        map.computeIfAbsent(point[0], v -> new HashMap<>());
	        Map<Integer, Integer> val = map.get(point[0]);
	        val.put(point[1], val.getOrDefault(point[1], 0) + 1);

	        points.add(point);
	    }
	    
	    public int count(int[] point) {
	        int x1 = point[0], y1 = point[1];

	        int count = 0;

	        for(int[] p : points){
	            int x3 = p[0], y3 = p[1];

	            // to form a square we need to find the remaining 3 points. 
	            // if we know the diagonal point then the remaining points are easy to compute 
	            // to find the diagonal the dist between the x1 and x3 should be same as y1 and y3. And the distance cannot be 0
	            // if the distance is 0 then they are essentially the same point
	            if(Math.abs(x1 - x3) == 0 || Math.abs(x1 - x3) != Math.abs(y1 - y3)){
	                continue;
	            }

	            // count += (grid[x1][y3] * grid[x3][y1]);

	            int secondPoint = getPoint(x1, y3);
	            int thirdPoint = getPoint(x3, y1);

	            count += (secondPoint * thirdPoint);
	        }

	        return count;
	    }

	    private int getPoint(int x, int y){
	        Map<Integer, Integer> val = map.get(x);
	        if(val == null){
	            return 0;
	        }

	        return val.getOrDefault(y, 0);
	    }
	}

	/**
	 * Your DetectSquares object will be instantiated and called as such:
	 * DetectSquares obj = new DetectSquares();
	 * obj.add(point);
	 * int param_2 = obj.count(point);
	 */
}
