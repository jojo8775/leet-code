package interview.leetcode.prob;

/**
 * Given a roman numeral, convert it to an integer.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * @author jojo
 *
 */
public class RpmanToInteger {
	public int romanToInt(String s) {
		int result = 0, prev = 0, cur = 0, count = s.length() - 1;
		while (count >= 0) {
			cur = getNumber(s.charAt(count--));
			if (prev > cur) {
				result -= cur;
			} else {
				result += cur;
			}

			prev = cur;
		}

		return result;
	}

	private int getNumber(char ch) {
		switch (ch) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
		}
	}
}
