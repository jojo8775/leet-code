package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function to generate the generalized abbreviations of a word.
 * 
 * Example: Given word = "word", return the following list (order does not
 * matter): ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2",
 * "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * 
 * @author jojo
 *
 */
public class GeneralizeAbbreviation {
	public static void main(String[] args) {
		List<String> results = new GeneralizeAbbreviation().generateAbbreviations("abc");

		for (String result : results) {
			System.out.println(result);
		}
	}

	public List<String> generateAbbreviations(String word) {
		List<String> list = new ArrayList<String>();
		findAbbreviation(list, word, 0, 0, "");

		return list;
	}

	private void findAbbreviation(List<String> list, String word, int curIdx, int abbrCount, String str) {
		// base case
		if (curIdx == str.length()) {
			if (abbrCount > 0) {
				str = str + abbrCount;
			}

			list.add(str);
			return;
		}

		// abbreviate a character
		findAbbreviation(list, word, curIdx + 1, abbrCount + 1, str);

		// abbreviate a character
		findAbbreviation(list, word, curIdx + 1, 0,
				str = str + ((abbrCount > 0) ? abbrCount : "") + word.charAt(curIdx));
	}
}
