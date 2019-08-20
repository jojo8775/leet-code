package interview.leetcode.prob;

/**
 * There's a tree, a squirrel, and several nuts. Positions are represented by the cells in a 2D grid. Your goal is to find the minimal distance for the squirrel to collect all the nuts and put them under the tree one by one. The squirrel can only take at most one nut at one time and can move in four directions - up, down, left and right, to the adjacent cell. The distance is represented by the number of moves.
Example 1:

Input: 
Height : 5
Width : 7
Tree position : [2,2]
Squirrel : [4,4]
Nuts : [[3,0], [2,5]]
Output: 12
Explanation:
​​​​​
Note:

All given positions won't overlap.
The squirrel can take at most one nut at one time.
The given positions of nuts have no order.
Height and width are positive integers. 3 <= height * width <= 10,000.
The given positions contain at least one nut, only one tree and one squirrel.

 * @author jojo
 * Aug 19, 2019 10:32:02 PM
 */
public class SquirrelSimulation {
	public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        
        int sum = 0, maxDiff = Integer.MIN_VALUE;
        
        for(int[] nut : nuts){
            int dist = findManhattenDist(nut, tree);
            
            sum += (dist * 2);
            
            maxDiff = Math.max(maxDiff, dist - findManhattenDist(nut, squirrel));
        }
        
        return sum - maxDiff;
    }
    
    private int findManhattenDist(int[] a, int[] b){
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
