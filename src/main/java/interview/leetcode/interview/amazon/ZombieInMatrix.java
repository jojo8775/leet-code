package interview.leetcode.interview.amazon;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a 2D grid, each cell is either a zombie 1 or a human 0. Zombies can turn adjacent (up/down/left/right) human beings into zombies every hour. Find out how many hours does it take to infect all humans?

Example:

Input:
[[0, 1, 1, 0, 1],
 [0, 1, 0, 1, 0],
 [0, 0, 0, 0, 1],
 [0, 1, 0, 0, 0]]

Output: 2

Explanation:
At the end of the 1st hour, the status of the grid:
[[1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1],
 [0, 1, 0, 1, 1],
 [1, 1, 1, 0, 1]]

At the end of the 2nd hour, the status of the grid:
[[1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1]]
int minHours(int rows, int columns, List<List<Integer>> grid) {
	// todo
}
 * @author jojo
 * Apr 26, 2020 1:42:32 PM
 */
public class ZombieInMatrix {
	public int minHours(int rows, int columns, List<List<Integer>> grid) {
		Queue<int[]> queue = new LinkedList<>();
		for(int i=0; i<grid.size(); i++) {
			for(int j=0; j<grid.get(0).size(); j++) {
				if(grid.get(i).get(j) == 1) {
					queue.offer(new int[] {i,j});
				}
			}
		}
		
		int[][] moves = {{0,-1}, {-1,0}, {0,1}, {1,0}};
		
		int hours = 0;
		while(!queue.isEmpty()) {
			hours ++;
			int size = queue.size();
			for(int i=0; i<size; i++) {
				int[] top = queue.poll();
				
				for(int[] move : moves) {
					int x = move[0] + top[0];
					int y = move[1] + top[1];
					
					if(x < 0 || x >= grid.size() || y < 0 || y >= grid.get(0).size() || grid.get(x).get(y) != 0) {
						continue;
					}
					
					grid.get(x).set(y, 1);
					queue.offer(new int[] {x,y});
				}
			}
		}
		
		return hours;
	}
}
