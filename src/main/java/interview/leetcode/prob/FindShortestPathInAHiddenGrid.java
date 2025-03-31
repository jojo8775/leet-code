package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * This is an interactive problem.

There is a robot in a hidden grid, and you are trying to get it from its starting cell to the target cell in this grid. The grid is of size m x n, and each cell in the grid is either empty or blocked. It is guaranteed that the starting cell and the target cell are different, and neither of them is blocked.

You want to find the minimum distance to the target cell. However, you do not know the grid's dimensions, the starting cell, nor the target cell. You are only allowed to ask queries to the GridMaster object.

Thr GridMaster class has the following functions:

boolean canMove(char direction) Returns true if the robot can move in that direction. Otherwise, it returns false.
void move(char direction) Moves the robot in that direction. If this move would move the robot to a blocked cell or off the grid, the move will be ignored, and the robot will remain in the same position.
boolean isTarget() Returns true if the robot is currently on the target cell. Otherwise, it returns false.
Note that direction in the above functions should be a character from {'U','D','L','R'}, representing the directions up, down, left, and right, respectively.

Return the minimum distance between the robot's initial starting cell and the target cell. If there is no valid path between the cells, return -1.

Custom testing:

The test input is read as a 2D matrix grid of size m x n where:

grid[i][j] == -1 indicates that the robot is in cell (i, j) (the starting cell).
grid[i][j] == 0 indicates that the cell (i, j) is blocked.
grid[i][j] == 1 indicates that the cell (i, j) is empty.
grid[i][j] == 2 indicates that the cell (i, j) is the target cell.
There is exactly one -1 and 2 in grid. Remember that you will not have this information in your code.

 

Example 1:

Input: grid = [[1,2],[-1,0]]
Output: 2
Explanation: One possible interaction is described below:
The robot is initially standing on cell (1, 0), denoted by the -1.
- master.canMove('U') returns true.
- master.canMove('D') returns false.
- master.canMove('L') returns false.
- master.canMove('R') returns false.
- master.move('U') moves the robot to the cell (0, 0).
- master.isTarget() returns false.
- master.canMove('U') returns false.
- master.canMove('D') returns true.
- master.canMove('L') returns false.
- master.canMove('R') returns true.
- master.move('R') moves the robot to the cell (0, 1).
- master.isTarget() returns true. 
We now know that the target is the cell (0, 1), and the shortest path to the target cell is 2.
Example 2:

Input: grid = [[0,0,-1],[1,1,1],[2,0,0]]
Output: 4
Explanation: The minimum distance between the robot and the target cell is 4.
Example 3:

Input: grid = [[-1,0],[0,2]]
Output: -1
Explanation: There is no path from the robot to the target cell.
 

Constraints:

1 <= n, m <= 500
m == grid.length
n == grid[i].length
grid[i][j] is either -1, 0, 1, or 2.
There is exactly one -1 in grid.
There is exactly one 2 in grid.
Accepted
3,426
Submissions
7,928
 * @author jojo
 * Dec 28, 2021 11:34:50 AM
 */
public class FindShortestPathInAHiddenGrid {
	/*
	private static final int UN_EXPLORED = 0;
	private static final int BLOCKED = -1;
	private static final int PATH = 2;
	private static final int TARGET = 3;
	private static final int START = 4;
		
	public int findShortestPath(GridMaster master) {
		// 501 to accomodate corner case. 
		int m = 501;
		int[][] grid = new int[m*2][m*2];
		grid[m][m] = START; 
		
		int[] target = dfs(master, grid, m, m);
		
		// if target cannot be reached
		if(target == null)
		{
			return -1;
		}
		
		return bfs(grid, target);
	}
	
	private int[] dfs(GridMaster master, int[][] grid, int i, int j) {
		if(master.isTarget()) {
			grid[i][j] = TARGET;
			return new int[] {i,j};
		}
		
		int[] target = null;
		
		for(Direction d : Direction.values()) {
			int x = i + d.x, y = j + d.y;
			
			if(grid[x][y] == UN_EXPLORED) {
				if(master.canMove(d.cur)) {
					grid[x][y] = PATH;
					master.move(d.cur);
					
					target = dfs(master, grid, x, y);
					
					master.move(d.prev);
				}
				else {
					grid[x][y] = BLOCKED;	
				}
			}
		}
		
		return target;
	}
	
	private int bfs(int[][] grid, int[] target) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(target);
		
		int distance = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			distance++;
			while(size-- > 0) {
				int[] top = queue.poll();
				
				for(Direction d : Direction.values()) {
					int x = top[0] + d.x, y = top[1] + d.y;
					if(grid[x][y] == START) {
						return distance;
					}
					else if(grid[x][y] == PATH) {
						grid[x][y] = BLOCKED; 
						queue.offer(new int[] {x,y});
					}
				}
			}
		}
		
		return distance;
	}
	
	private enum Direction{
		UP('D', 'U', 0, 1),
		DOWN('U', 'D', 0, -1),
		LEFT('R', 'L', -1, 0),
		RIGHT('L', 'R', 1, 0);
		
		char cur, prev;
		int x, y;
		
		private Direction(char prev, char cur, int x, int y) {
			this.cur = cur;
			this.prev = prev;
			this.x = x;
			this.y = y;
		}
	}
	*/
	
	char[] dirs = {'U', 'D', 'L', 'R'};
    Map<Integer, Map<Integer, Integer>> grid = new HashMap<>();
    private int left = 0, right = 0, up = 0, down = 0;
    private int[] target = null;

    public int findShortestPath(GridMaster master) {
        // 1. build graph
        grid.put(0, new HashMap<>());
        grid.get(0).put(0, -1);
        build(master, 0, 0);
        // System.out.println("l=" + leftbound + ",r=" + rightbound + ",u=" + upperbound + ",d=" + lowerbound);

        // 2. bfs find Shortest Distance
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[]{0,0});
        visited.add(0 + "-" + 0);

        int dist = 0;
        int[][] idirs = {{1,0}, {0,1}, {-1,0}, {0,-1}};

        if(target == null){
            return -1;
        }

        while (!queue.isEmpty()) {
            int size =queue.size();

            for (int i = 0; i < size; ++i) {
                int[] top = queue.poll();
                if (top[0] == target[0] && top[1] == target[1]) {
                    return dist;
                }

                for (int[] d : idirs) {
                    int x = top[0] + d[0], y = top[1] + d[1];

                    if(x < left || x > right || y < down || y > up || grid.get(x).get(y) == 0){
                        continue;
                    }
                    
                    int[] next = new int[]{x, y};

                    String nextKey = x + "-" + y;

                    if (!visited.contains(nextKey)) {
                        visited.add(nextKey);
                        queue.add(next);
                    }                    
                }                
            }

            ++dist;
        }
        
        return -1;
    }

    private void build(GridMaster master, int i, int j) {
        left = Math.min(left, i);
        right = Math.max(right, i);
        up = Math.max(up, j);
        down = Math.min(down, j);

        if (master.isTarget()) {
            grid.get(i).put(j, 2);
            target = new int[]{i, j};
        }

        for (char d : dirs) {
            int x = i, y = j;
            char back ;
            if (d == 'U') {
                --y;
                back = 'D';
            }
            else if (d == 'D') {
                ++y;
                back = 'U';
            }
            else if (d == 'L') {
                --x;
                back = 'R';
            }
            else {
                ++x;
                back = 'L';
            }
            
            grid.putIfAbsent(x, new HashMap<>());
            if (grid.get(x).containsKey(y)) {
                continue;
            }
            if (master.canMove(d)) {
                grid.get(x).put(y, 1);
                master.move(d);
                build(master, x, y);
                master.move(back);
            }
            else {
                grid.get(x).put(y, 0);
            }
        }
    }
	
	private interface GridMaster{
		boolean canMove(char ch);
		boolean isTarget();
		void move(char ch);
	}
	
	
}
