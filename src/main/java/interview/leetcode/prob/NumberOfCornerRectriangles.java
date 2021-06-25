package interview.leetcode.prob;

/**
 * Given an m x n integer matrix grid where each entry is only 0 or 1, return the number of corner rectangles.

A corner rectangle is four distinct 1's on the grid that forms an axis-aligned rectangle. Note that only the corners need to have the value 1. Also, all four 1's used must be distinct.

 

Example 1:


Input: grid = [[1,0,0,1,0],[0,0,1,0,1],[0,0,0,1,0],[1,0,1,0,1]]
Output: 1
Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
Example 2:


Input: grid = [[1,1,1],[1,1,1],[1,1,1]]
Output: 9
Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
Example 3:


Input: grid = [[1,1,1,1]]
Output: 0
Explanation: Rectangles must have four distinct corners.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[i][j] is either 0 or 1.
The number of 1's in the grid is in the range [1, 6000].
Accepted
33,033
Submissions
49,110
 * @author jojo
 * Jun 17, 2021  11:54:26 PM
 */
public class NumberOfCornerRectriangles {
	public int countCornerRectangles(int[][] grid) {
        int m  = grid.length, n = grid[0].length;
        int count = 0;
        
        //Map<String, Integer> map = new HashMap<>();
        int[][] map = new int[n][n];
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0){
                    continue;
                }
                
                for(int k=j+1; k<n; k++){
                    if(grid[i][k] == 0){
                        continue;
                    }
                    
                    //String key = j + "-" + k;
                    // int val = map.getOrDefault(key, 0);
                    // count += val;
                    // map.put(key, val + 1);
                    
                    count += map[j][k];
                    map[j][k]++;
                }
            }
        }
        
        return count;
    }
}
