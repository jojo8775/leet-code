package interview.leetcode.prob;

/**
 * You are given four integers sx, sy, fx, fy, and a non-negative integer t.

In an infinite 2D grid, you start at the cell (sx, sy). Each second, you must move to any of its adjacent cells.

Return true if you can reach cell (fx, fy) after exactly t seconds, or false otherwise.

A cell's adjacent cells are the 8 cells around it that share at least one corner with it. You can visit the same cell several times.

 

Example 1:


Input: sx = 2, sy = 4, fx = 7, fy = 7, t = 6
Output: true
Explanation: Starting at cell (2, 4), we can reach cell (7, 7) in exactly 6 seconds by going through the cells depicted in the picture above. 
Example 2:


Input: sx = 3, sy = 1, fx = 7, fy = 3, t = 3
Output: false
Explanation: Starting at cell (3, 1), it takes at least 4 seconds to reach cell (7, 3) by going through the cells depicted in the picture above. Hence, we cannot reach cell (7, 3) at the third second.
 

Constraints:

1 <= sx, sy, fx, fy <= 109
0 <= t <= 109
Accepted
44,118
Submissions
148,529
 * @author jojo
 * Nov. 7, 2023 11:27:05 p.m.
 */
public class DetermineIfCellIsReachableAtAGivenTime {
	public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        // draw a 6X6 grid and choose random two points
        // each time the max dist between them will be max of height or width
        int height = Math.abs(sy - fy), width = Math.abs(sx - fx);
        
        if(height == 0 && width == 0 && t == 1){
            // since the start and end are at the same spot and with t == 1 
            // it is impossible to comeback to the same spot within time
            // the ans is false;
            return false;
        }
        
        // if t is greater than height and width then we can always revisit 
        // previous cells to kill time.
        return t >= Math.max(height, width);
    }
}
