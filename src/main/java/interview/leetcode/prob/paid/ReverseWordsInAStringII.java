package interview.leetcode.prob.paid;

/**
 * Given an input string, reverse the string word by word. A word is defined as
 * a sequence of non-space characters.
 * 
 * The input string does not contain leading or trailing spaces and the words
 * are always separated by a single space.
 * 
 * For example, Given s = "the sky is blue", return "blue is sky the".
 * 
 * Could you do it in-place without allocating extra space?
 * 
 * @author jojo
 *
 */
public class ReverseWordsInAStringII {
	// the idea is to parse this string twice
	public void reverseWords(char[] s) {
		// reversing the string charecter by character
		for (int i = 0, j = s.length - 1; i < j; i++, j--) {
			char ch = s[i];
			s[i] = s[j];
			s[j] = ch;
		}

		// reversing each word in the string
		for (int i = 0; i < s.length; i++) {
			int blankIdx = i;
			while (blankIdx < s.length && s[blankIdx] != ' ') {
				blankIdx++;
			}
			int j = blankIdx - 1;
			while (i < j) {
				char ch = s[i];
				s[i] = s[j];
				s[j] = ch;
				i++;
				j--;
			}
			i = blankIdx;
		}
	}
}
