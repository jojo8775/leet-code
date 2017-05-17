package interview.leetcode.prob;

/**
 * There's a tree, a squirrel, and several nuts. Positions are represented by
 * the cells in a 2D grid. Your goal is to find the minimal distance for the
 * squirrel to collect all the nuts and put them under the tree one by one. The
 * squirrel can only take at most one nut at one time and can move in four
 * directions - up, down, left and right, to the adjacent cell. The distance is
 * represented by the number of moves.
 * 
 * 
 * @author jojo May 16, 20179:51:18 PM
 */
public class SuirrellSimulation {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int totalDistance = 0;

        for (int[] nut : nuts) {
            totalDistance += (2 * absoluteDistance(nut, tree));
        }

        int minDistance = Integer.MAX_VALUE;
        for (int[] nut : nuts) {
            minDistance = Math.min(minDistance,
                    totalDistance - absoluteDistance(nut, tree) + absoluteDistance(nut, squirrel));
        }

        return minDistance;
    }

    private int absoluteDistance(int[] nut, int[] tree) {
        return Math.abs(nut[0] - tree[0]) + Math.abs(nut[1] - tree[1]);
    }
}
