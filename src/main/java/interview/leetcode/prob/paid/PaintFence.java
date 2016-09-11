package interview.leetcode.prob.paid;

/**
 * There is a fence with n posts, each post can be painted with one of the k
 * colors.
 * 
 * You have to paint all the posts such that no more than two adjacent fence
 * posts have the same color.
 * 
 * Return the total number of ways you can paint the fence.
 * 
 * Note: n and k are non-negative integers.
 * 
 * @author jojo
 *
 */
public class PaintFence {
	public int numWays(int n, int k) {
		// not enough color option to paint the third fence
		if (k == 1 && n > 2) {
			return 0;
		}

		// no fence to paint
		if (n == 0) {
			return 0;
		}

		// number of ways to paint one fence = N(1) = k*1
		int waysToPaint1 = k;
		if (n == 1) {
			return waysToPaint1;
		}

		// number of ways to paint two fence = N(2) = k*(k-1) + k*1
		// where number of ways to color it with diff color is k*(k-1) and with
		// same color is k*1;
		int waysToPaint2 = k * (k - 1) + k * 1;
		if (n == 2) {
			return waysToPaint2;
		}

		for (int i = 3; i <= n; i++) {
			// k-1 since atmost two adjacent fence can be of same color
			int waysToPaint3 = (k - 1) * (waysToPaint1 + waysToPaint2);
			waysToPaint1 = waysToPaint2;
			waysToPaint2 = waysToPaint3;
		}

		return waysToPaint2;
	}
}
