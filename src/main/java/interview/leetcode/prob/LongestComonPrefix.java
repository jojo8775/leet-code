package interview.leetcode.prob;

/**
 * Write a function to find the longest common prefix string amongst an array of
 * strings.
 * 
 * @author jojo
 *
 */
public class LongestComonPrefix {
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) {
			return "";
		}

		boolean maxPrefixReached = false;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strs[0].length(); i++) {
			char ch = strs[0].charAt(i);

			for (int j = 1; j < strs.length; j++) {
				if (i > strs[j].length() - 1) {
					maxPrefixReached = true;
					break;
				}

				if (strs[j].charAt(i) != ch) {
					maxPrefixReached = true;
					break;
				}
			}

			if (maxPrefixReached) {
				break;
			}

			sb.append(ch);
		}

		return sb.toString();
	}
}
