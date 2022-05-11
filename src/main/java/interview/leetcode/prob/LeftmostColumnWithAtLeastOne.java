package interview.leetcode.prob;

import java.util.List;

/**
 * A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.

You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:

BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.
Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.

 

Example 1:


Input: mat = [[0,0],[1,1]]
Output: 0
Example 2:


Input: mat = [[0,0],[0,1]]
Output: 1
Example 3:


Input: mat = [[0,0],[0,0]]
Output: -1
 

Constraints:

rows == mat.length
cols == mat[i].length
1 <= rows, cols <= 100
mat[i][j] is either 0 or 1.
mat[i] is sorted in non-decreasing order.
Accepted
147,558
Submissions
280,779
 * @author jojo
 * May 9, 2022 10:53:43 PM
 */
public class LeftmostColumnWithAtLeastOne {
	public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
		// get the co-ordinates
		List<Integer> dimensions = binaryMatrix.dimensions();

		int m = dimensions.get(0), n = dimensions.get(1);

		// start at the topLeft corner
		int cM = 0, cN = n - 1;

		while (cM < m && cN >= 0) {
			// if the current col is 1 go left
			if (binaryMatrix.get(cM, cN) == 1) {
				cN--;
			}

			// if the current (col,row) == 0 then check the next row
			else {
				cM++;
			}
		}

		return cN == n - 1 ? -1 : cN + 1;
	}

	private static class BinaryMatrix {
		public List<Integer> dimensions() {
			return null;
		}

		public int get(int col, int row) {
			return 1;
		}
	}
}
