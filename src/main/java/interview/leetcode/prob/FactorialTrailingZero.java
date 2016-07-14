package interview.leetcode.prob;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * 
 * Note: Your solution should be in logarithmic time complexity.
 * 
 * @author jojo
 *
 */
public class FactorialTrailingZero {
	public int trailingZeroes(int n) {
		long result = 0;
		for (long i = 5; n / i >= 1; i = i * 5) {
			result = result + (n / i);
		}

		return (int) result;
	}
}