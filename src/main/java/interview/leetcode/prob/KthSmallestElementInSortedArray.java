package interview.leetcode.prob;

import java.util.PriorityQueue;

public class KthSmallestElementInSortedArray {
	/**
     *Things to consider since this is a sorted matrix
     * 1. First element of each row is the smallest in that row
     * 2. First element of row 1 will be less than first element in row 2
     */
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a,b) -> a[0] - b[0]);
        
        //inserting all the elements in column one
        for(int i=0; i<matrix.length; i++){
            //int[] represents a tupule with value, col and row info
            heap.offer(new int[]{matrix[i][0], i, 0});
        }
        
        int result = 0, rowLen = matrix[0].length;
        while(k-- > 0 && !heap.isEmpty()){
            int[] top = heap.poll();
            result = top[0];
            
            if(top[2] < rowLen - 1){
                heap.offer(new int[]{matrix[top[1]][top[2] + 1], top[1], top[2] + 1});
            }
        }
        
        return result;
    }
}
