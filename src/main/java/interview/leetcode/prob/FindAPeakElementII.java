package interview.leetcode.prob;

/**
 * 
 * A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.

Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i,j].

You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.

You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.

 

Example 1:



Input: mat = [[1,4],[3,2]]
Output: [0,1]
Explanation: Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.
Example 2:



Input: mat = [[10,20,15],[21,30,14],[7,16,32]]
Output: [1,1]
Explanation: Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 500
1 <= mat[i][j] <= 105
No two adjacent cells are equal.
 
Seen this question in a real interview before?
1/5
Yes
No
Accepted
179,810/329.5K
Acceptance Rate
54.6%
 * chiranjeebnandy
 * Apr 8, 2026  2026  10:10:25 PM
 */
public class FindAPeakElementII {
	public int[] findPeakGrid(int[][] mat) {
        int startCol = 0;
        int endCol = mat[0].length - 1;

        while (startCol <= endCol) {
            int midCol = startCol + (endCol - startCol) / 2;

            // Find the index of the maximum element in the current middle column
            int maxRow = 0;
            for (int i = 0; i < mat.length; i++) {
                if (mat[i][midCol] > mat[maxRow][midCol]) {
                    maxRow = i;
                }
            }

            // Check neighbors to the left and right
            boolean leftIsBig = midCol - 1 >= startCol && mat[maxRow][midCol - 1] > mat[maxRow][midCol];
            boolean rightIsBig = midCol + 1 <= endCol && mat[maxRow][midCol + 1] > mat[maxRow][midCol];

            if (!leftIsBig && !rightIsBig) {
                // mat[maxRow][midCol] is a peak
                return new int[]{maxRow, midCol};
            } else if (leftIsBig) {
                // If the left neighbor is bigger, move the search range to the left
                endCol = midCol - 1;
            } else {
                // If the right neighbor is bigger, move the search range to the right
                startCol = midCol + 1;
            }
        }

        return new int[]{-1, -1};
    }
}
