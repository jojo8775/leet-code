package interview.leetcode.interview.amazon;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You have a map that marks the locations of treasure islands. Some of the map area has jagged rocks and dangerous reefs. Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure out a shortest route to one of the treasure islands.

Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start from one of the starting point (marked as S) of the map and can move one block up, down, left or right at a time. The treasure island is marked as X. Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous blocks. You cannot leave the map area. Other areas O are safe to sail in. Output the minimum number of steps to get to any of the treasure islands.

Example:

Input:
[['S', 'O', 'O', 'S', 'S'],
 ['D', 'O', 'D', 'O', 'D'],
 ['O', 'O', 'O', 'O', 'X'],
 ['X', 'D', 'D', 'O', 'O'],
 ['X', 'D', 'D', 'D', 'O']]

Output: 3
Explanation:
You can start from (0,0), (0, 3) or (0, 4). The treasure locations are (2, 4) (3, 0) and (4, 0). Here the shortest route is (0, 3), (1, 3), (2, 3), (2, 4).
 * @author jojo
 * Apr 30, 2020  11:35:24 PM
 */
public class TreasureIslandII {
	public int findMinSteps(char[][] grid) {
		Queue<int[]> queue = new LinkedList<>();
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j] == 'S') {
					queue.offer(new int[] {i,j});
				}
			}
		}
		
		int[][] moves = {{0,-1}, {0,1}, {-1, 0}, {1,0}};
		
		int steps = 0;
		while(!queue.isEmpty()) {
			steps++;
			
			for(int i=queue.size(); i>0; i--) {
				int[] top = queue.poll();
				
				for(int[] move : moves) {
					int x = move[0] + top[0];
					int y = move[1] + top[1];
					
					if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 'O') {
						continue;
					}
					
					if(grid[x][y] == 'X') {
						return steps;
					}
					
					grid[x][y] = 'V';
					queue.offer(new int[] {x,y});
				}
			}
		}
		
		return -1;
	}
}
