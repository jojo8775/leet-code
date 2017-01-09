package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

Hint:

Find the smallest and largest x-value for all points.
If there is a line then it should be at y = (minX + maxX) / 2.
For each point, make sure that it has a reflected point in the opposite side.

 * @author jojo
 *
 */
public class LineReflection {
    public boolean isReflected(int[][] points) {
        Set<String> strSet = new HashSet<String>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        
        for(int[] point : points){
            min = Math.min(min, point[0]);
            max = Math.max(max, point[0]);
            strSet.add(point[0] + "-" + point[1]);
        }
        
        // total sum of x - coordinates will be min + max
        int sum = min + max;
        
        for(int[] point : points){
            // if there is a parallel line then for each point there should be a replica which will be sum - crrent val
            String str = (sum - point[0]) + "-" + point[1];
            if(!strSet.contains(str)){
                return false;
            }
        }
        
        return true;
    }
}
