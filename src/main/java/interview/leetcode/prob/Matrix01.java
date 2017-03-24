package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1: 
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
Example 2: 
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right
 * @author jojo
 *Mar 23, 201711:23:34 PM
 */
public class Matrix01 {
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        int len = matrix.size(), width = matrix.get(0).size();
        Queue<int[]> queue = new LinkedList<int[]>();
        
        for(int i=0; i<len; i++){
            for(int j=0; j<width; j++){
                if(matrix.get(i).get(j) == 0){
                    queue.offer(new int[]{i,j});
                }
                else{
                    matrix.get(i).set(j, Integer.MAX_VALUE);
                }
            }
        }
        
        int[][] moves = {{0,-1}, {-1, 0}, {0,1}, {1,0}};
        while(!queue.isEmpty()){
            int[] top = queue.poll();
            int val = matrix.get(top[0]).get(top[1]);
            
            for(int[] move : moves){
                int x = move[0] + top[0], y = move[1] + top[1];

                if(x < 0 || x >= len || y < 0 || y >= width || matrix.get(x).get(y) < val + 1){
                    continue;
                }
                else{
                    matrix.get(x).set(y, val + 1);
                    queue.offer(new int[]{x,y});  
                }
            }
        }
        
        return matrix;
    }
}
