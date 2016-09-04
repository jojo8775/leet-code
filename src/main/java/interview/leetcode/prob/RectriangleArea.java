package interview.leetcode.prob;

/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * 
 * Each rectangle is defined by its bottom left corner and top right corner as
 * shown in the figure.
 * 
 * Rectangle Area Assume that the total area is never beyond the maximum
 * possible value of int.
 * 
 * 
 * @author jojo
 *
 */
public class RectriangleArea {
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		// area one
		int area1 = (C - A) * (D - B);

		// area two
		int area2 = (G - E) * (H - F);

		int lowestTopCorner = Math.min(D, H);
		int highestBottomCorner = Math.max(B, F);
		int rightmostBottomCorner = Math.max(A, E);
		int leftmostTopCorner = Math.min(C, G);

		int overlap = 0;
		if (lowestTopCorner > highestBottomCorner && leftmostTopCorner > rightmostBottomCorner) {
			overlap = (lowestTopCorner - highestBottomCorner) * (leftmostTopCorner - rightmostBottomCorner);
		}

		return area1 + area2 - overlap;
	}
}
