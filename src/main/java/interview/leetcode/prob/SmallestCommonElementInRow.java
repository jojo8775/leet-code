package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * Given a matrix mat where every row is sorted in strictly increasing order, return the smallest common element in all rows.

If there is no common element, return -1.

 

Example 1:

Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
Output: 5
 

Constraints:

1 <= mat.length, mat[i].length <= 500
1 <= mat[i][j] <= 10^4
mat[i] is sorted in strictly increasing order.
Accepted
9,391
Submissions
12,559
 * @author jojo
 * Aug 6, 2020  12:05:26 AM
 */
public class SmallestCommonElementInRow {
	public int smallestCommonElement_adv(int[][] mat) {
        for(int i=0; i<mat[0].length; i++){
            if(findCommon(mat, mat[0][i])){
                return mat[0][i];
            }
        }
        
        return -1;
    }
    
    private boolean findCommon(int[][] grid, int val){
        for(int i=0; i<grid.length; i++){
            if(!binarySearch(grid[i], val)){
                return false;
            }
        }
        
        return true;
    }
    
    private boolean binarySearch(int[] arr, int val){
        int beg = 0, end = arr.length - 1;
        
        while(beg <= end){
            int mid = beg + (end - beg) / 2;
            
            if(arr[mid] == val){
                return true;
            }
            else if(arr[mid] > val){
                end = mid - 1;
            }
            else{
                beg = mid + 1;
            }
        }
        
        return false;
    }
	
	
	public int smallestCommonElement(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        for(int i=0; i<n; i++){
            pq.offer(new int[]{mat[i][0], i, 0});
        }
        
        while(!checkQueue(pq)) {
        	int[] top = pq.poll();
        	top[2]++;
        	if(top[2] == m) {
        		return -1;
        	}
        	
        	top[0] = mat[top[1]][top[2]];
        	pq.offer(top);
        }
        
        return pq.peek()[0];
    }
    
    private boolean checkQueue(PriorityQueue<int[]> pQueue) {
    	int prev = pQueue.peek()[0];
    	
    	for(int[] entry : pQueue) {
    		if(entry[0] != prev) {
    			return false;
    		}
    	}
    	
    	return true;
    }
}
