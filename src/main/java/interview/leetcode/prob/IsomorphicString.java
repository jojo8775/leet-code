package interview.leetcode.prob;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * 
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * 
 * All occurrences of a character must be replaced with another character while
 * preserving the order of characters. No two characters may map to the same
 * character but a character may map to itself.
 * 
 * For example, Given "egg", "add", return true.
 * 
 * Given "foo", "bar", return false.
 * 
 * Given "paper", "title", return true.
 * 
 * @author jojo
 *
 */
public class IsomorphicString {
	public boolean isIsomorphic(String s, String t) {
		if (s.isEmpty()) {
			return true;
		}

		for (int i = 0; i < s.length() - 1; i++) {
			if (s.indexOf(s.charAt(i), i + 1) != t.indexOf(t.charAt(i), i + 1)) {
				return false;
			}
		}

		return true;
	}
}
