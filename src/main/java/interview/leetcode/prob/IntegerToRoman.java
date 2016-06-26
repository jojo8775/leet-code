package interview.leetcode.prob;

/**
 * Given an integer, convert it to a roman numeral.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * @author jojo
 *
 */
public class IntegerToRoman {
	public String intToRoman(int a) {
		int[] nums = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] symbol = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

		StringBuilder sb = new StringBuilder();

		while (a > 0) {
			int i = 0;
			for (; i < nums.length; i++) {
				// found the minimum symbol to represent number
				if (a >= nums[i]) {
					break;
				}
			}

			int count = a / nums[i];
			while (count > 0) {
				sb.append(symbol[i]);
				count--;
			}

			a = a % nums[i];
		}

		return sb.toString();
	}
}
