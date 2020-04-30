package interview.leetcode.interview.amazon;

import java.util.Stack;

/**
 * Find number of islands. 
 * @author jojo
 * Apr 27, 2020  10:09:47 PM
 */
public class NumberOfIslands {
	public int findNumberOfIsland(char[][] grid){
		int count = 0;
		int[][] moves = {{0,-1}, {-1,0}, {1,0}, {0,1}};
		
		for(int i=0; i<grid.length; i++){
			for(int j=0; j<grid[0].length; j++){
				if(grid[i][j] == '1'){
					count++;
					Stack<int[]> stack = new Stack<>();
					stack.push(new int[]{i,j});

					while(!stack.isEmpty()){
						int[] top = stack.pop();
						grid[top[0]][top[1]] = '#';

						for(int[] move : moves){
							int x = top[0] + move[0];
							int y = top[1] + move[1];
		
							if(x < 0 || x >= grid.length 
								|| y < 0 || y >= grid[0].length 
								|| grid[x][y] != '1'){
									continue;
							}

							stack.push(new int[]{x,y});
						}
					}
				}
			}
		}

		return count; 
	}
}
