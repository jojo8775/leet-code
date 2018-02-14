package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.
Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
Note: The length of each dimension in the given grid does not exceed 50.
 * @author jojo
 *Feb 4, 20181:18:01 PM
 */
public class NumberOfDistinctIslands {
    public int numDistinctIslands_rec(int[][] grid) {
        Set<String> set = new HashSet<>();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] != 0) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, "o"); // origin
                    grid[i][j] = 0;
                    set.add(sb.toString());
                }
            }
        }
           return set.size();
    }

    private void dfs(int[][] grid, int i, int j, StringBuilder sb, String dir) {
        if(i < 0 || i == grid.length || j < 0 || j == grid[i].length 
           || grid[i][j] == 0) return;
        sb.append(dir);
        grid[i][j] = 0;
        dfs(grid, i-1, j, sb, "u");
        dfs(grid, i+1, j, sb, "d");
        dfs(grid, i, j-1, sb, "l");
        dfs(grid, i, j+1, sb, "r");
        // this information is required to preserve uniqueness.
        sb.append("b"); // back
    }
    
    public int numDistinctIslands(int[][] grid) {
        int[][] moves = {{0,1}, {0,-1}, {-1,0}, {1,0}};
        char[] dir = { 'R', 'L', 'U', 'D' };

        Set<String> islands = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[] { i, j });

                    StringBuilder sb = new StringBuilder();
                    sb.append('S');
                    while (!stack.isEmpty()) {
                        int[] top = stack.pop();
                        grid[top[0]][top[1]] = 8;

                        for (int k = 0; k < 4; k++) {
                            int x = top[0] + moves[k][0], y = top[1] + moves[k][1];
                            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                                sb.append(dir[k]);
                                stack.push(new int[] { x, y });
                            }
                            else{
                                sb.append("B");
                            }
                        }
                    }

                    System.out.println(sb.toString());
                    printGrid(grid);
                    
                    islands.add(sb.toString());
                }
            }
        }

        return islands.size();
    }
    
    private void printGrid(int[][] grid){
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                System.out.print(grid[i][j] + ",");
            }
            
            System.out.println();
        }
        
        System.out.println("\n=====================\n");
    }

    public static void main(String[] args) {
        int[][] grid = 
                   {{1,1,0,0,1,0,1,1,1,1,1,0,0,0,1,1,0,1,0,1,1,1,1,0,0},
                    {0,0,0,1,0,1,0,1,1,0,0,0,0,1,0,1,1,1,1,0,1,0,0,0,1},
                    {1,0,1,0,1,1,0,0,1,1,1,0,0,0,1,0,0,1,1,1,1,1,1,0,1},
                    {0,0,1,1,0,1,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,0,1,1},
                    {1,1,0,1,1,1,1,1,0,0,1,0,0,1,1,1,1,1,1,0,0,0,0,0,1},
                    {0,1,0,1,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1},
                    {1,1,1,1,0,0,0,1,0,0,1,0,0,1,0,1,1,1,1,1,0,0,0,0,0},
                    {0,1,1,1,1,1,0,1,0,0,0,1,0,1,1,1,0,0,1,0,0,0,0,1,1},
                    {1,1,0,0,1,1,0,1,0,1,1,0,0,0,0,1,1,1,1,0,1,1,0,0,0},
                    {0,1,1,1,1,0,1,1,0,1,1,0,1,1,0,1,0,0,0,0,1,0,1,1,1},
                    {0,1,1,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0},
                    {1,0,0,1,0,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,0,1,1,1},
                    {1,1,1,1,1,1,1,1,0,0,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1},
                    {1,1,1,0,1,0,1,1,1,1,1,1,0,1,1,0,1,0,1,1,1,0,0,1,1},
                    {0,0,0,1,1,0,1,1,1,0,1,1,1,1,1,1,0,1,1,0,0,0,0,1,0},
                    {0,0,1,0,0,1,0,1,1,1,1,0,1,0,0,0,1,0,1,1,1,1,1,1,1},
                    {0,0,1,1,1,1,0,1,1,0,0,1,1,1,0,0,1,0,1,0,1,1,0,1,1},
                    {1,0,0,1,0,1,0,0,1,1,1,1,0,0,0,1,0,1,0,1,1,1,0,1,1},
                    {0,0,0,0,1,0,0,1,0,1,0,0,0,1,1,1,0,1,0,0,0,1,0,0,0},
                    {1,1,1,1,0,0,0,0,0,1,0,0,0,0,0,1,1,0,1,1,1,0,0,0,1},
                    {1,0,0,1,1,1,1,1,1,1,0,1,0,0,1,1,1,1,1,1,0,1,0,1,1},
                    {1,1,0,0,0,0,0,0,1,0,1,0,1,0,0,1,0,1,0,1,0,0,0,0,0},
                    {1,0,0,0,0,1,1,0,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,0,0},
                    {0,1,1,0,1,1,0,1,0,1,0,1,1,1,1,1,0,1,0,0,1,1,0,1,1},
                    {1,1,0,1,1,0,1,1,1,0,1,0,1,0,0,1,0,1,0,0,0,1,0,0,0},
                    {0,1,0,0,1,0,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,0,0,0},
                    {0,1,1,1,1,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1},
                    {1,0,0,1,1,1,0,0,0,1,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0},
                    {1,0,0,0,1,0,1,0,0,0,0,1,0,0,0,0,1,1,1,0,1,0,0,0,0},
                    {0,0,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,0,1,1,0},
                    {1,0,1,1,1,0,1,1,1,0,1,1,1,0,0,0,0,0,1,1,0,0,1,0,1},
                    {0,1,1,0,0,0,0,1,0,1,0,0,1,0,1,1,1,1,0,0,0,1,1,0,0},
                    {0,1,0,0,1,1,0,1,1,0,1,0,1,0,0,0,0,1,1,0,0,1,1,1,1},
                    {0,0,1,0,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,1,0,0},
                    {1,0,1,0,1,1,0,1,1,0,0,0,1,1,1,1,1,1,1,0,1,1,1,1,1},
                    {1,0,1,0,1,1,1,0,1,0,1,0,1,1,0,0,1,1,0,0,1,1,0,0,1},
                    {0,1,0,0,0,1,0,1,1,1,1,1,0,0,0,1,0,0,0,0,1,0,1,0,1},
                    {0,0,1,0,1,0,1,1,0,0,0,0,0,0,0,0,1,0,0,1,1,1,0,0,1},
                    {1,1,0,1,0,0,1,1,0,0,1,1,1,0,0,1,1,1,1,0,0,0,0,1,0},
                    {1,0,0,0,0,0,0,1,0,0,0,1,0,1,0,1,0,1,1,1,0,0,0,0,0},
                    {1,0,0,0,1,0,0,1,0,0,0,0,1,1,1,1,1,1,0,0,1,0,0,0,1},
                    {0,1,0,1,0,1,1,0,0,1,1,1,0,1,0,0,0,1,1,0,0,1,1,1,0},
                    {1,1,1,1,0,1,0,0,1,0,1,1,0,0,1,1,0,1,0,1,0,0,1,0,1},
                    {1,0,0,1,0,1,0,1,0,0,1,0,1,0,0,0,1,1,0,1,1,1,0,0,0},
                    {1,1,1,0,1,0,0,0,1,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
                    {1,1,1,0,0,0,1,0,0,1,0,1,1,0,1,1,1,1,0,0,1,1,0,0,1},
                    {0,1,1,0,1,0,0,1,0,0,0,0,1,0,1,1,1,1,1,0,1,1,1,0,1},
                    {0,1,1,1,1,1,1,1,1,1,0,1,0,0,1,1,1,0,0,0,0,1,1,0,1},
                    {0,0,1,0,1,1,0,0,1,1,1,0,1,0,0,0,1,0,1,0,1,0,1,1,0},
                    {1,0,0,1,1,0,1,0,0,1,0,0,1,1,0,1,0,0,1,0,0,1,0,1,0}};
        
        System.out.println(new NumberOfDistinctIslands().numDistinctIslands(grid));
    }
}
