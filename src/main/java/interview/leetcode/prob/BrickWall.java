package interview.leetcode.prob;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is a brick wall in front of you. The wall is rectangular and has
 * several rows of bricks. The bricks have the same height but different width.
 * You want to draw a vertical line from the top to the bottom and cross the
 * least bricks.
 * 
 * The brick wall is represented by a list of rows. Each row is a list of
 * integers representing the width of each brick in this row from left to right.
 * 
 * If your line go through the edge of a brick, then the brick is not considered
 * as crossed. You need to find out how to draw the line to cross the least
 * bricks and return the number of crossed bricks.
 * 
 * You cannot draw a line just along one of the two vertical edges of the wall,
 * in which case the line will obviously cross no bricks.
 * 
 * 
 * @author jojo May 14, 201712:02:19 AM
 */
public class BrickWall {
    public int leastBricks(List<List<Integer>> wall) {
        // map to index the brick break in a row
        Map<Integer, Integer> map = new HashMap<>();

        // number of rows where brick is not crossed
        int maxCount = 0;

        for (List<Integer> row : wall) {
            int len = 0;
            // -1 because the end one brick needs to be ignored.
            for (int i = 0; i < row.size() - 1; i++) {
                len += row.get(i);
                map.put(len, map.getOrDefault(len, 0) + 1);
                maxCount = Math.max(maxCount, map.get(len));
            }
        }

        // number of rows where brick is crossed
        return wall.size() - maxCount;
    }
}
