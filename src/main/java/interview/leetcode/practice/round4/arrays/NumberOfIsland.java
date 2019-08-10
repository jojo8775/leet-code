package interview.leetcode.practice.round4.arrays;

import java.util.Stack;

public class NumberOfIsland {
	public int numIslands(char[][] grid) {
        int[][] moves = {{0, 1}, {0,-1}, {-1, 0}, {1,0}};
        int count = 0;
        
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
            	if(grid[i][j] == '1') {
            		Stack<int[]> stack = new Stack<>();
            		stack.push(new int[] {i,j});
            		count++;
            		while(!stack.isEmpty()) {
            			int[] top = stack.pop();
            			grid[top[0]][top[1]] = '#';
            			
            			for(int k=0; k<4; k++) {
            				int x = top[0] + moves[k][0], y = top[1] + moves[k][1];
            				
            				if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != '1') {
            					continue;
            				}
            				
            				stack.push(new int[] {x,y});
            			}
            		}
            	}
            }
        }
        
        return count;
    }
}
