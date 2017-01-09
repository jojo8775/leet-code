package interview.leetcode.prob;

import java.util.List;

/**
 * Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon definition).

Note:

There are at least 3 and at most 10,000 points.
Coordinates are in the range -10,000 to 10,000.
You may assume the polygon formed by given points is always a simple polygon (Simple polygon definition). In other words, we ensure that exactly two edges intersect at each vertex, and that edges otherwise don't intersect each other.
Example 1:

[[0,0],[0,1],[1,1],[1,0]]

Answer: True

Explanation:
Example 2:

[[0,0],[0,10],[10,10],[10,0],[5,5]]

Answer: False

Explanation:
 * @author jojo
 *
 */
public class ConvexPolygon {
    public boolean isConvex(List<List<Integer>> points) {
        // idea is for a regular convex, cross product of all three adjacent sides should be eighter positive (< 180) or negative (> 180)
        // depending on the order which they are visited. 
        
        boolean vertexMoreThan180 = false, vertexLessThan180 = false;
        int len = points.size();
        
        for(int A=0; A<len; A++){
            // this is required to calculate combinations  like n-1, 0, 1
            int B = (A + 1) % len;
            int C = (B + 1) % len;
            
            long crossProduct = createCrossProduct(points.get(A).get(0), points.get(A).get(1), 
                                                    points.get(B).get(0), points.get(B).get(1), 
                                                    points.get(C).get(0), points.get(C).get(1));
                                                    
            if(crossProduct > 0){
                vertexMoreThan180 = true;
            }
            else if (crossProduct < 0){
                vertexLessThan180 = true;
            }
            
            if(vertexLessThan180 && vertexMoreThan180){
                return false;
            }
        }
        
        return true;
    }
    
    private long createCrossProduct(int aX, int aY, int bX, int bY, int cX, int cY){
        long baX = aX - bX;
        long baY = aY - bY;
        long bcX = cX - bX;
        long bcY = cY - bY;
        
        return (baX * bcY) - (baY * bcX);
    }
}
