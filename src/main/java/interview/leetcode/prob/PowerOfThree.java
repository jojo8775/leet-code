package interview.leetcode.prob;

/**
 * Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?
 * @author jojo
 *
 */
public class PowerOfThree {
	public boolean isPowerOfThree(int n) {
		if (n != 1 && n < 3) {
			return false;
		}

		while (n > 3) {
			if (n % 3 != 0) {
				return false;
			}

			n = n / 3;

			if (n % 3 != 0) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
	}
}
