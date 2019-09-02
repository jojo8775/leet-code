package interview.leetcode.prob;

/**
 * On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

 

Example 1:

Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:

Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
Example 3:

Input: [[0,1],[2,0]]
Output: 0
Explanation: 
There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.
 

Note:

1 <= grid.length * grid[0].length <= 20
Accepted
13,150
Submissions
18,433
 * @author jojo
 * Sep 2, 2019 2:02:00 AM
 */
public class UniquePathsIII {
	int result = 0, emptyCount = 1, startX, startY, endX, endY;

	public int uniquePathsIII(int[][] grid) {
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				if (grid[i][j] == 0) {
					emptyCount++;
				}
				else if (grid[i][j] == 1) {
					startX = i;
					startY = j;
				} else if (grid[i][j] == 2) {
					endX = i;
					endY = j;
				}
			}
		}
		
		dfs(grid, startX, startY);
		return result;
	}

	public void dfs(int[][] grid, int i, int j) {
		if (!checkCell(grid, i, j)){
			return;
		}
		
		if (i == endX && j == endY) {
			if (emptyCount == 0) {
				result++;
			}
			
			return;
		}
		grid[i][j] = -2;
		emptyCount--;
		dfs(grid, i + 1, j);
		dfs(grid, i - 1, j);
		dfs(grid, i, j + 1);
		dfs(grid, i, j - 1);
		grid[i][j] = 0;
		emptyCount++;
	}

	public boolean checkCell(int[][] grid, int x, int y) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] < 0) {
			return false;
		}

		return true;
	}
}
