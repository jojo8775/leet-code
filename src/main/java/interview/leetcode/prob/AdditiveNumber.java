package interview.leetcode.prob;

/**
 * Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
1 + 99 = 100, 99 + 100 = 199
Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Follow up:
How would you handle overflow for very large input integers?
 * @author jojo
 *
 */
public class AdditiveNumber {
	public boolean isAdditiveNumber(String num) {
		char[] cArr = num.toCharArray();
		long num1 = 0;
		for (int i = 0; i < cArr.length; i++) {
			if (i > 0 && cArr[0] == '0') {
				return false;
			}

			num1 = num1 * 10 + (long) (cArr[i] - '0');
			long num2 = 0;
			for (int j = i + 1; j < cArr.length; j++) {
				if (j > i + 1 && cArr[i + 1] == '0') {
					break;
				}

				num2 = num2 * 10 + (long) (cArr[j] - '0');

				if (isAdditiveNumber(num1, num2, cArr, j + 1)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isAdditiveNumber(long num1, long num2, char[] cArr, int idx) {
		long num3 = 0;
		int i = idx;
		for (; i < cArr.length; i++) {
			if (i > idx && cArr[idx] == '0') {
				return false;
			}

			num3 = num3 * 10 + (long) (cArr[i] - '0');

			if (num3 >= num1 + num2) {
				break;
			}
		}

		if (num3 != num1 + num2) {
			return false;
		}

		if (i == cArr.length - 1) {
			return true;
		}

		return isAdditiveNumber(num2, num3, cArr, i + 1);
	}

	public static void main(String[] args) {
		// System.out.println(new
		// AdditiveNumber().isAdditiveNumber("199100199"));
		System.out.println(new AdditiveNumber().isAdditiveNumber("121474836472147483648"));
		// System.out.println(new AdditiveNumber().isAdditiveNumber("1203"));
	}
}
