package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * @author jojo
 *
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] grid = new int[size][size];
        grid[0][0] = triangle.get(0).get(0);
        
        for(int i=1; i<size; i++){
            grid[i-1][i] = Integer.MAX_VALUE;
            for(int j=0; j<triangle.get(i).size(); j++){
                int val = triangle.get(i).get(j);
                if(j == 0){
                    grid[i][j] = grid[i-1][j] + val;
                }
                else if(grid[i-1][j] == Integer.MAX_VALUE){
                    grid[i][j] = grid[i-1][j-1] + val;
                }
                else{
                    grid[i][j] = Math.min(val + grid[i-1][j-1], val + grid[i-1][j]);
                }
            }
        }
        
        int result = grid[size-1][0];
        for(int i=1; i<size; i++){
            result = Math.min(result, grid[size - 1][i]);
        }
        
        return result;
    }
    
    public int minimumTotal2 (List<List<Integer>> triangle) {
        int size = triangle.size();
        int[] grid = new int[size];
        grid[0] = triangle.get(0).get(0);
        
        for(int i=1; i<size; i++){
        	List<Integer> list = triangle.get(i);
        	int previous = grid[0];
        	grid[0] += list.get(0);
        	
        	for(int j=1; j<i; j++){
        		int temp = grid[j];
        		grid[j] = list.get(j) + Math.min(previous, grid[j]);
        		previous = temp;
        	}
        	
        	grid[i] = previous + list.get(i);
        }
        
        int result = grid[0];
        for(int i=1; i<size; i++){
            result = Math.min(result, grid[i]);
        }
        
        return result;
    }
	
	public static void main(String[] args){
		List<List<Integer>> nodes = new ArrayList<List<Integer>>();
		nodes.add(Arrays.asList(-1));
		nodes.add(Arrays.asList(2,3));
		nodes.add(Arrays.asList(1,-1,-3));
//		nodes.add(Arrays.asList(4,1,8,3));
		
//		System.out.println(new Triangle().minimumTotal(nodes));
		System.out.println(new Triangle().minimumTotal2(nodes));
	}
}
