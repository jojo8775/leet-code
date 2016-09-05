package interview.leetcode.prob;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND
 * of all numbers in this range, inclusive.
 * 
 * For example, given the range [5, 7], you should return 4.
 * 
 * 
 * @author jojo
 *
 */
public class BitwiseAndOfNumberRange {
	// each number odd number has last bit equal to 1 and even as 0
	// if m == 4 and n = 14 then there has to be a number between 4 and 14 which
	// has last bit equal to 1
	// this is the reason we can right shift both m and n at each iteration of
	// while loop as long as m != n;
	public int rangeBitwiseAnd(int m, int n) {
		int leftShift = 0;

		while (m != 0 && m != n) {
			m = m >> 1;
			n = n >> 1;
			leftShift++;
		}

		if (m == 0) {
			return m;
		}

		return m << leftShift;
	}

	public static void main(String[] args) {
		System.out.println(new BitwiseAndOfNumberRange().rangeBitwiseAnd(600000000, 2147483645));
	}
}
