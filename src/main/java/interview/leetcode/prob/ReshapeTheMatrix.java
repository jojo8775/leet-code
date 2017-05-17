package interview.leetcode.prob;

/**
 * In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.

You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.

The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.

If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

Example 1:
Input: 
nums = 
[[1,2],
 [3,4]]
r = 1, c = 4
Output: 
[[1,2,3,4]]
Explanation:
The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
Example 2:
Input: 
nums = 
[[1,2],
 [3,4]]
r = 2, c = 4
Output: 
[[1,2],
 [3,4]]
Explanation:
There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
Note:
The height and width of the given matrix is in range [1, 100].
The given r and c are all positive.
Show Company Tags
Show Tags

 * @author jojo
 *May 16, 20177:55:48 PM
 */
public class ReshapeTheMatrix {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if(nums.length == 0 || nums[0].length == 0){
            return nums;
        }
        
        int[][] copy = new int[r][c];
        int idx1=0,idx2=0, r1 = nums[0].length, c1 = nums.length, len1 = r1*c1, len2 = r*c;
        
        while(idx1 < len1){
            if(idx2 >= len2){
                return nums;
            }
            
            int temp = nums[idx1/r1][idx1%r1];
            copy[idx2/c][idx2%c] = temp;
            idx1++;
            idx2++;
        }
        
        if(idx2 != len2){
            return nums;
        }
        
        return copy;
    }
}
