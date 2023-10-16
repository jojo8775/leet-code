import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 * @author jojo
 *
 */
public class PacificAtlanticWaterFlow {
	public List<List<Integer>> pacificAtlantic_dfs(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<>();

        if(matrix.length == 0 || matrix[0].length == 0){
            return result;
        }

        int m = matrix.length, n = matrix[0].length;

        boolean[][] pacific = new boolean[m][n], atlantic = new boolean[m][n];

        // simulating the flood coming from each ocean to the land from left and right
        for(int i=0; i<m; i++){
            dfs(matrix, pacific, i, 0, matrix[i][0]);
            dfs(matrix, atlantic, i, n-1, matrix[i][n-1]);
        }

       // simulating the flood coming from each ocean to the land from top and bottom
        for(int i=0; i<n; i++){
            dfs(matrix, pacific, 0, i, matrix[0][i]);
            dfs(matrix, atlantic, m-1, i, matrix[m-1][i]);
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(pacific[i][j] && atlantic[i][j]){
                    result.add(Arrays.asList(i,j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] matrix, boolean[][] visited, int i, int j, int curMin){
        int[][] moves = {{0,1},{0,-1},{1,0},{-1,0}};
        int m = matrix.length, n = matrix[0].length;

        visited[i][j] = true;

        for(int[] move : moves){
            int x = i + move[0], y = j + move[1];

            if(x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || matrix[x][y] < curMin){
                continue;
            }

            visited[x][y] = true;
            dfs(matrix, visited, x, y, matrix[x][y]);
        }
    }
	
	
	
	public List<int[]> pacificAtlantic_bfs(int[][] matrix) {
		List<int[]> res = new ArrayList<int[]>();
		int m = matrix.length;
		if (m == 0)
			return res;
		int n = matrix[0].length;
		int[][] state = new int[m][n];
		Queue<int[]> q = new LinkedList<int[]>();
		for (int i = 0; i < m; i++) {
			state[i][0] = 1;
			if (i == m - 1 || n == 1) {
				state[i][0] = 3;
				res.add(new int[] { i, 0 });
			}
			q.add(new int[] { i, 0 });

			if (n > 1) {
				state[i][n - 1] = 2;
				if (i == 0) {
					state[i][n - 1] = 3;
					res.add(new int[] { i, n - 1 });
				}

				q.add(new int[] { i, n - 1 });
			}
		}
		for (int j = 1; j < n - 1; j++) {
			state[0][j] = 1;
			if (m == 1) {
				state[0][j] = 3;
				res.add(new int[] { 0, j });
			}

			q.add(new int[] { 0, j });

			if (m > 1) {
				state[m - 1][j] |= 2;
				if (state[m - 1][j] == 3) {
					res.add(new int[] { m - 1, j });
				}
				q.add(new int[] { m - 1, j });
			}
		}
		int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		while (!q.isEmpty()) {
			int[] cell = q.poll();
			for (int[] dir : dirs) {
				int row = cell[0] + dir[0];
				int col = cell[1] + dir[1];
				if (row < 0 || col < 0 || row == m || col == n || matrix[row][col] < matrix[cell[0]][cell[1]]
						|| ((state[cell[0]][cell[1]] | state[row][col]) == state[row][col])) {
					continue;
				}

				state[row][col] |= state[cell[0]][cell[1]];
				if (state[row][col] == 3) {
					res.add(new int[] { row, col });
				}

				q.add(new int[] { row, col });
			}
		}
		return res;
	}
}
