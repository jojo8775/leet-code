package interview.leetcode.prob;

/**
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:

[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:
 * @author jojo
 *
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        int[][] moves = new int[][]{{-1,0}, {0, -1}, {1,0}, {0,1}};
        
        int len = grid.length, width = grid[0].length;
        
        for(int i=0; i<len; i++){
            for(int j=0; j<width; j++){
                if(grid[i][j] == 1){
                    perimeter += getGridPerimeter(i,j,grid,moves);
                }
            }
        }
        
        return perimeter;
    }
    
    private int getGridPerimeter(int i,int j,int[][] grid, int[][] moves){
        int perimeter = 0, len = grid.length, width = grid[0].length;
        for(int[] move : moves){
            int x = i + move[0], y = j+ move[1];
            
            if(x >= len || x < 0 || y >= width || y < 0){
                perimeter++;
            }
            else if(grid[x][y] != 1){
                perimeter ++;
            }
        }
        
        return perimeter;
    }
}
