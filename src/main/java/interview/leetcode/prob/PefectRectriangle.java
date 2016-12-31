package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).


Example 1:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]

Return true. All 5 rectangles together form an exact cover of a rectangular region.

Example 2:

rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]

Return false. Because there is a gap between the two rectangular regions.

Example 3:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]

Return false. Because there is a gap in the top center.

Example 4:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]

Return false. Because two of the rectangles overlap with each other.

 * @author jojo
 *
 */
public class PefectRectriangle {
    public boolean isRectangleCover(int[][] rectangles) {
        int totalArea = 0, maxArea = 0;
        int top = Integer.MIN_VALUE, right = Integer.MIN_VALUE;
        int bottom = Integer.MAX_VALUE, left = Integer.MAX_VALUE;
        
        Set<String> edges = new HashSet<String>();
        
        for(int[] rectangle : rectangles){
            bottom = Math.min(bottom, rectangle[0]);
            left = Math.min(left, rectangle[1]);
            top = Math.max(top, rectangle[2]);
            right = Math.max(right, rectangle[3]);
            
            totalArea += ((rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]));
            
            String topLeft = rectangle[2] + "-" + rectangle[1];
            String topRight = rectangle[2] + "-" + rectangle[3];
            String bottomLeft = rectangle[0] + "-" + rectangle[1];
            String bottomRight = rectangle[0] + "-" + rectangle[3];
            
            if(!edges.add(topLeft)){
                edges.remove(topLeft);
            }
            
            if(!edges.add(topRight)){
                edges.remove(topRight);
            }
            
            if(!edges.add(bottomLeft)){
                edges.remove(bottomLeft);
            }
            
            if(!edges.add(bottomRight)){
                edges.remove(bottomRight);
            }
        }
        
        if(!edges.contains(top + "-" + left) || !edges.contains(top + "-" + right)
                || !edges.contains(bottom + "-" + left) || !edges.contains(bottom + "-" + right)
                || edges.size() != 4){
            return false;
        }
        
        maxArea = (top - bottom) * (right - left);
        
        return maxArea == totalArea;
    }
    
    public static void main(String[] args){
        int[][] rec = {{0,0,1,1},{1,0,2,1}, {2,0,3,1}, {0,1,1,2}, {1,1,2,2}, {2,1,3,2}};
        System.out.println(new PefectRectriangle().isRectangleCover(rec));
    }
}
