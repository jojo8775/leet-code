package interview.leetcode.prob;

/**
 * Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise. 

Return the number of negative numbers in grid.

 

Example 1:

Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
Output: 8
Explanation: There are 8 negatives number in the matrix.
Example 2:

Input: grid = [[3,2],[1,0]]
Output: 0
Example 3:

Input: grid = [[1,-1],[-1,-1]]
Output: 3
Example 4:

Input: grid = [[-1]]
Output: 1
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
-100 <= grid[i][j] <= 100
Accepted
45,401
Submissions
59,082
 * @author jojo
 * Jun 17, 2020  10:06:36 PM
 */
public class CountNegativeNumbersInASortedMatrix {
	 public int countNegatives(int[][] grid) {
	        if(grid.length == 0 || grid[0].length == 0){
	            return 0;
	        }
	        
	        int left = 0, right = grid[0].length - 1, result = 0;
	        
	        for(int i=0; i<grid.length && right >=0; i++){
	            int index = findIndex(grid, i, left, right);
	            
	            if(index == -1){
	                continue;
	            }
	            
	            int subGrid = (right - index + 1) * (grid.length - i);
	            
	            result += subGrid;
	            right = index - 1;
	        }
	        
	        return result;
	    }
	    
	    private int findIndex(int[][] grid, int i, int left, int right){
	        int len = right;
	        while(left <= right){
	            int mid = left + (right - left)/2;
	            
	            // reverse the regular binary search as the elements are sorted desc.
	            if(grid[i][mid] >= 0){
	                left = mid + 1;
	            }
	            else{
	                right = mid - 1;
	            }
	        }
	        
	        // left will be > len when there is no negative number in the row. 
	        return left > len ? -1 : left;
	    }
}
