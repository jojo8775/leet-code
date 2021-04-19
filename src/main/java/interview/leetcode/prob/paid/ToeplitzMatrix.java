package interview.leetcode.prob.paid;

/**
 * 
 * @author jojo
 * Apr 17, 2021  11:18:43 PM
 */
public class ToeplitzMatrix {
    public boolean isToeplitzMatrix(int[][] matrix) {
        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i-1][j-1] != matrix[i][j]){
                    return false;
                }
            }
        }
        
        return true;
    }
}
