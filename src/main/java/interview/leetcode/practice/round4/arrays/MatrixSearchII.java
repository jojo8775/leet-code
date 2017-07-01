package interview.leetcode.practice.round4.arrays;

public class MatrixSearchII {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        
        int m = matrix.length, n = matrix[0].length;
        
        for(int i=0; i<m; i++){
            if(matrix[i][0] > target){
                break;
            }
            
            if(matrix[i][n-1] < target){
                continue;
            }
            
            int index = binarySearch(i, matrix, target);
            
            if(index != -1){
                return true;
            }
        }   
        
        return false;
    }
    
    private int binarySearch(int idx, int[][] matrix, int target){
        int beg = 0, end = matrix[0].length - 1;
        
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            if(matrix[idx][mid] == target){
                return mid;
            }
            
            if(matrix[idx][mid] > target){
                end = mid - 1;
            }
            else{
                beg = mid + 1;
            }
        }
        
        return -1;
    }
}
