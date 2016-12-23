package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).

Example:
Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 * @author jojo
 *
 */
public class NumberOfBoomerangs {
	public int numberOfBoomerangs(int[][] points) {
        int totalBoomerangs = 0;
        
        for(int i=0; i<points.length; i++){
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            
            for(int j=0; j<points.length; j++){
                if(i == j){
                    continue;
                }
                
                int dist = getDistance(points[i], points[j]);
                map.put(dist, map.getOrDefault(dist, 0) + 1);
            }
            
            for(int val : map.values()){
                // -1 because points which dont have same distance with other cannot form a boomerang
                // so all count 1's will be 0. Incase count is 3. then three equidistance points can make 
                // at most two boomerangs so val * (val - 1) still holds.
                totalBoomerangs += val * (val - 1);
            }
        }
        
        return totalBoomerangs;
    }
    
    private int getDistance(int[] p1, int[] p2){
        int dx = p1[0] - p2[0];
        int dy = p1[1] - p2[1];
        
        return (dx * dx) + (dy * dy);
    }
}
