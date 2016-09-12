package interview.leetcode.prob.paid;

/**
 * Given two strings S and T, determine if they are both one edit distance
 * apart.
 * 
 * @author jojo
 *
 */
public class OneEditDistance {
	public boolean isOneEditDistance(String s, String t) {
		if (s.equals(t)) {
			return false;
		}

		if (s.length() < t.length()) {
			String temp = t;
			t = s;
			s = temp;
		}

		if (s.length() - t.length() > 1) {
			return false;
		}

		// insert, modify or delete character
		int i = 0, j = 0, missMatch = 0;
		while (i < s.length() && j < t.length()) {
			if (s.charAt(i++) != t.charAt(j)) {
				if (missMatch == 0) {
					missMatch = 1;
				} else {
					return false;
				}
				if (s.length() == t.length()) {
					j++;
				}
			} else {
				j++;
			}
		}

		return true;
	}
}
