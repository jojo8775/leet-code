package interview.leetcode.practice.round4.queue;

import java.util.LinkedList;
import java.util.Queue;

public class TheMazeII {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] dirs = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
        
        Integer[][] dp = new Integer[maze.length][maze[0].length];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(start[0], start[1], 0));
        
        while(!queue.isEmpty()) {
        	Point top = queue.poll();
        	
        	for(int i=0; i<4; i++) {
        		int curX = top.x, curY = top.y, steps = 0;
        		
        		while(isEmpty(maze, curX + dirs[i][0], curY + dirs[i][1])) {
        			curX += dirs[i][0];
        			curY += dirs[i][1];
        			steps += 1;
        		}
        		
        		if(dp[curX][curY] != null && dp[curX][curY] < steps) {
        			continue;
        		}
        		
        		queue.offer(new Point(curX, curY, steps));
        	}
        }
        
        return dp[destination[0]][destination[1]] == null ? -1 : dp[destination[0]][destination[1]];
    }
    
    private boolean isEmpty(int[][] maze, int x, int y) {
    	if(x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) {
    		return false;
    	}
    	
    	return maze[x][y] == 0;
    }
    
    private class Point{
    	int x, y, steps;
    	
    	public Point(int x, int y, int steps) {
    		this.x = x;
    		this.y = y;
    		this.steps = steps;
    	}
    }
}
