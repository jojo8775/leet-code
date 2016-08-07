package interview.leetcode.prob;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
 * @author jojo
 *
 */
public class SearchA2DMatrix {
	 public boolean searchMatrix(int[][] matrix, int target) {
        int beg=0, end = matrix.length * matrix[0].length - 1, len = matrix[0].length;
        
        while(beg < end){
            int mid = beg + (end - beg)/2;
            
            if(matrix[mid/len][mid%len] < target){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
        }
        
        if(matrix[beg/len][beg%len] == target){
            return true;
        }
        
        return false;
    }
}
