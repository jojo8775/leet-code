package interview.leetcode.prob;

/**
 * Implement strStr().
 * 
 * Returns the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 * 
 * 
 * @author jojo
 *
 */
public class StrStr {
	public int find(String needle, String hayStack) {
		if (needle.isEmpty()) {
			return 0;
		}

		int[] pattern = new int[needle.length()];

		// creating the pattern index
		for (int j = 0, i = 1; i < pattern.length; i++) {
			while (needle.charAt(j) != needle.charAt(i) && j != 0) {
				j = pattern[j - 1];
			}

			if (needle.charAt(j) == needle.charAt(i)) {
				pattern[i] = j + 1;
				j++;
			}
		}

		// using pattern index to search in the real string
		int idx = 0;
		for (int i = 0; i < hayStack.length(); i++) {
			while (needle.charAt(idx) != hayStack.charAt(i) && idx != 0) {
				idx = pattern[idx - 1];
			}

			if (needle.charAt(idx) == hayStack.charAt(i)) {
				idx++;
			}

			if (idx == pattern.length) {
				return i - pattern.length + 1;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		System.out.println(new StrStr().find("issip", "mississippi"));
	}
}
