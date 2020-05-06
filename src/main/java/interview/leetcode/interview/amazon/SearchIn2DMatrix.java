package interview.leetcode.interview.amazon;

/**
 * Rows of the matrix is sorted and cols of matrix are sorted. 
 * @author jojo
 * May 5, 2020  10:34:39 PM
 */
public class SearchIn2DMatrix {
	public boolean find(int[][] grid, int target) {
		int m = grid.length - 1, n = grid[0].length -1;
		
		for(int i=0; i<=m; i++) {
			if(grid[i][n] < target) {
				continue;
			}
			if(grid[i][0] > target) {
				break;
			}
			
			int beg = 0, end = n;
			while(beg < end) {
				int mid = beg + (end - beg)/2;
				
				if(grid[i][mid] < target) {
					beg = mid + 1;
				}
				else {
					end = mid;
				}
			}
			
			if(grid[i][beg] == target) {
				return true;
			}
		}
		
		return false;
	}
}
