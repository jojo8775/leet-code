package interview.leetcode.prob.paid;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180
 * degrees (looked at upside down).
 * 
 * Write a function to count the total strobogrammatic numbers that exist in the
 * range of low <= num <= high.
 * 
 * For example, Given low = "50", high = "100", return 3. Because 69, 88, and 96
 * are three strobogrammatic numbers.
 * 
 * Note: Because the range might be a large number, the low and high numbers are
 * represented as string.
 * 
 * @author jojo
 *
 */
public class StrobogramaticNumberIII {
	private char[][] refCombinations = { { '0', '0' }, { '1', '1' }, { '8', '8' }, { '9', '6' }, { '6', '9' } };
	private int count = 0;

	public int strobogrammaticInRange(String low, String high) {
		int startingDigitCount = low.length(), endDigitCount = high.length();

		while (startingDigitCount <= endDigitCount) {
			find(0, startingDigitCount - 1, new char[startingDigitCount], low, high);
			startingDigitCount++;
		}

		return count;
	}

	private void find(int leftIdx, int rightIdx, char[] cArr, String low, String high) {
		if (leftIdx > rightIdx) {
			String str = String.valueOf(cArr);

			// checking if within range, comparison of length is required as
			// string comparison happens at character level.
			if ((low.length() < str.length() || low.compareTo(str) <= 0)
					&& (high.length() > str.length() || high.compareTo(str) >= 0)) {
				count++;
			}

			return;
		}

		// handling one digit
		if (leftIdx == rightIdx) {
			for (int i = 0; i < 3; i++) {
				cArr[leftIdx] = refCombinations[i][0];
				find(leftIdx + 1, rightIdx - 1, cArr, low, high);
			}
		} else {
			for (int i = leftIdx == 0 ? 1 : 0; i < 5; i++) {
				cArr[leftIdx] = refCombinations[i][0];
				cArr[rightIdx] = refCombinations[i][1];
				find(leftIdx + 1, rightIdx - 1, cArr, low, high);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(new StrobogramaticNumberIII().strobogrammaticInRange("10", "200"));
	}
}
