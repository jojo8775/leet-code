package interview.leetcode.prob.paid;

/**
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)

 * @author jojo
 *
 */
public class BombEnemy {
    public int maxKilledEnemies(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        
        int len = grid.length, width = grid[0].length;
        
        int arr[][] = new int[len][width];
        
        for(int i=0; i<len; i++){
            int count = 0;
            
            // left to right
            for(int j=0; j<width; j++){
                if(grid[i][j] == 'E'){
                    count++;
                }
                else if(grid[i][j] == '0'){
                    arr[i][j] += count;
                }
                else{
                    count = 0;
                }
            }
            
            count = 0;
            
            // right to left 
            for(int j=width - 1; j>=0; j--){
                if(grid[i][j] == 'E'){
                    count++;
                }
                else if(grid[i][j] == '0'){
                    arr[i][j] += count;
                }
                else{
                    count = 0;
                }
            }
        }
        
        int max = 0;
        for(int j=0; j < width; j++){
            int count = 0;
            
            // from top to bottom
            for(int i=0; i<len; i++){
                if(grid[i][j] == 'E'){
                    count++;
                }
                else if(grid[i][j] == '0'){
                    arr[i][j] += count;
                }
                else{
                    count = 0;
                }
            }
            
            count = 0;
            
            // bottom to top
            for(int i=len - 1; i>=0; i--){
                if(grid[i][j] == 'E'){
                    count++;
                }
                else if(grid[i][j] == '0'){
                    arr[i][j] += count;
                    max = Math.max(max, arr[i][j]);
                }
                else{
                    count = 0;
                }
            }
        }
        
        return max;
    }
}
