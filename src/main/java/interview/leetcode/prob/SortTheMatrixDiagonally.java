package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].

Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.

 

Example 1:


Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
Example 2:

Input: mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
Output: [[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 100
1 <= mat[i][j] <= 100
Accepted
58,519
Submissions
71,698
 * @author jojo
 * May 8, 2021  10:30:36 PM
 */
public class SortTheMatrixDiagonally {
    public int[][] diagonalSort(int[][] mat) {
        if(mat.length == 0 || mat[0].length == 0){
            return mat;
        }
        
        for(int i=0; i<mat.length; i++){
            sort(mat, i, 0);
        }
        
        for(int i=1; i<mat[0].length; i++){
            sort(mat, 0, i);
        }
        
        return mat;
    }
    
    private void sort(int[][] mat, int x, int y){
        List<Integer> list = new ArrayList<>();
        
        for(int i=x, j=y; i<mat.length && j < mat[0].length; i++, j++){
            list.add(mat[i][j]);
        }
        
        Collections.sort(list);
        int idx = 0;
        for(int i=x, j=y; i<mat.length && j < mat[0].length; i++, j++){
            mat[i][j] = list.get(idx++);
        }
    }
}
