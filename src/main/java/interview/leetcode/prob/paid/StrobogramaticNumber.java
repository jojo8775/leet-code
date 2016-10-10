package interview.leetcode.prob.paid;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180
 * degrees (looked at upside down).
 * 
 * Write a function to determine if a number is strobogrammatic. The number is
 * represented as a string.
 * 
 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
 * 
 * @author jojo
 *
 */
public class StrobogramaticNumber {
	public boolean isStrobogrammatic(String num) {
		String referenceStr = "96 88 11 00 69";
		int beg = 0, end = num.length() - 1;
		while (beg < end) {
			if (!referenceStr
					.contains(new StringBuilder().append(num.charAt(beg++)).append(num.charAt(end--)).toString())) {
				return false;
			}
		}

		if (beg == end) {
			char ch = num.charAt(beg);
			if (ch != '8' && ch != '0' && ch != '1') {
				return false;
			}
		}

		return true;
	}
}
