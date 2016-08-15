package interview.leetcode.prob;

/**
 * Given an input string, reverse the string word by word.
 * 
 * For example, Given s = "the sky is blue", return "blue is sky the".
 * 
 * @author jojo
 *
 */
public class ReverseWordInAString {
	public String reverseWords(String s) {
		s = s.trim();
		String[] strArr = s.split(" +");
		StringBuilder sb = new StringBuilder();
		for (int i = strArr.length - 1; i >= 0; i--) {
			sb.append(strArr[i]).append(" ");
		}

		if (sb.length() != 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
	}
}
