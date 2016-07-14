package interview.leetcode.prob;

/**
 * Divide two integers without using multiplication, division and mod operator.
 * 
 * If it is overflow, return MAX_INT.
 * 
 * @author jojo
 *
 */
public class DivideTwoInteger {
	public int divide(int dividend, int divisor) {
		// covering base cased
		if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
			return Integer.MAX_VALUE;
		}

		long pDividend = Math.abs((long) dividend);
		long pDivisor = Math.abs((long) divisor);

		int result = 0;

		// applying the logic
		// num = a*2^0 + a*2^1 + a*2^2 + ...
		while (pDividend >= pDivisor) {
			int leftShift = 0;
			while (pDividend >= (pDivisor << leftShift)) {
				leftShift++;
			}

			leftShift--;
			// taking the highest left shift;
			result += (1 << leftShift);
			// reducing the divident
			pDividend = pDividend - (pDivisor << leftShift);
		}

		// computing the sign of result
		if ((divisor > 0 && dividend > 0) || (divisor < 0 && dividend < 0)) {
			return result;
		}

		return -result;
	}
}
