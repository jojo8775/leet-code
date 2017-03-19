package interview.leetcode.prob;

/**
 * 
Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

Example:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]
Explanation:

Note:
The total number of elements of the given matrix will not exceed 10,000.
 * @author jojo
 *
 */
public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        // base case 
        if(matrix.length == 0 || matrix[0].length == 0){
            return new int[0];
        }
        
        int len = matrix.length, width = matrix[0].length, row = 0, col  = 0, offset = -1;
        
        // the result in a flattened matrix
        int[] result = new int[len * width];
        int resultLen = result.length, idx = 0;
        
        while(idx < resultLen){
            // storing the result
            result[idx++] = matrix[col][row];
            
            // moving diagonaly by one step
            col += offset;
            row -= offset;
            
            if(row >= width){
                row = width - 1;
                
                // need to +2 to correct the approaching angle. need to draw this to visualize. 
                col += 2;
                offset = -offset;
            }
            
            if(col >= len){
                col = len - 1;
                
                // need to +2 to correct the approaching angle. need to draw this to visualize. 
                row += 2;
                offset = -offset;
            }
            
            if(row < 0){
                row = 0;
                offset = -offset;
            }
            
            if(col < 0){
                col = 0;
                offset = -offset;
            }
        }
        
        return result;
    }
}
