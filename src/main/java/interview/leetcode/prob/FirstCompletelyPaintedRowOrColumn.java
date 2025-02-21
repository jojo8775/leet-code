package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 0-indexed integer array arr, and an m x n integer matrix mat. arr and mat both contain all the integers in the range [1, m * n].

Go through each index i in arr starting from index 0 and paint the cell in mat containing the integer arr[i].

Return the smallest index i at which either a row or a column will be completely painted in mat.

 

Example 1:

image explanation for example 1
Input: arr = [1,3,4,2], mat = [[1,4],[2,3]]
Output: 2
Explanation: The moves are shown in order, and both the first row and second column of the matrix become fully painted at arr[2].
Example 2:

image explanation for example 2
Input: arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
Output: 3
Explanation: The second column becomes fully painted at arr[3].
 

Constraints:

m == mat.length
n = mat[i].length
arr.length == m * n
1 <= m, n <= 105
1 <= m * n <= 105
1 <= arr[i], mat[r][c] <= m * n
All the integers of arr are unique.
All the integers of mat are unique.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
66.1K
Submissions
110.4K
Acceptance Rate
59.9%
 * 
 * Jan 20, 2025 - 12:30:00 AM
 * Jojo 
 */
public class FirstCompletelyPaintedRowOrColumn {
	public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;

        int[] rows = new int[m], cols = new int[n];

        Map<Integer, int[]> map = new HashMap<>();

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                map.put(mat[i][j], new int[]{i,j});
            }
        }

        int result = -1;
        for(int i=0; i<arr.length; i++){
            int[] val = map.get(arr[i]);
            int x = val[0], y = val[1];

            rows[x]++;
            cols[y]++;

            if(rows[x] == n || cols[y] == m){
                result = i;
                break;
            } 
        }

        return result;
    }
}
