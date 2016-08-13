package interview.leetcode.prob;

/**
 * Validate if a given string is numeric.
 * 
 * Some examples: "0" => true " 0.1 " => true "abc" => false "1 a" => false
 * "2e10" => true Note: It is intended for the problem statement to be
 * ambiguous. You should gather all requirements up front before implementing
 * one.
 * 
 * @author jojo
 *
 */
public class IsValid {
	public boolean isNumber(String s) {
		// checking for null
		if (s == null) {
			return false;
		}

		s = s.trim();

		if (s.isEmpty()) {
			return false;
		}

		boolean hasDot = false, hasE = false, hasDigit = false;
		char prev = 'X', cur = 'X';
		for (int i = 0; i < s.length(); i++) {
			cur = s.charAt(i);

			// +/- can only be the first element or after e
			if (cur == '-' || cur == '+') {
				if (i != 0 && prev != 'e') {
					return false;
				}
			}

			// checking for '.' e should not be present
			else if (cur == '.') {
				if (hasDot || hasE) {
					return false;
				}

				hasDot = true;
			}

			// checking for 'e' should not be present
			else if (cur == 'e') {
				if (hasE || !hasDigit) {
					return false;
				}

				hasE = true;
			}

			// checking for digit
			else if (cur < '0' || cur > '9') {
				return false;
			}

			else {
				hasDigit = true;
			}

			prev = cur;
		}

		// checking for input with only +, -, e
		if (cur == 'e' || cur == '+' || cur == '-') {
			return false;
		}

		// checking for input like "." or ending with "+."
		if (cur == '.') {
			if (s.length() == 1 || s.charAt(s.length() - 2) > '9' || s.charAt(s.length() - 2) < '0') {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(new IsValid().isNumber("-1."));
	}
}
