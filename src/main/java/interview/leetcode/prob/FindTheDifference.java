package interview.leetcode.prob;

/**
 * Given two strings s and t which consist of only lowercase letters.
 * 
 * String t is generated by random shuffling string s and then add one more
 * letter at a random position.
 * 
 * Find the letter that was added in t.
 * 
 * @author jojo
 *
 */
public class FindTheDifference {
	public char findTheDifference(String s, String t) {
		int[] arr = new int[26];

		// indexing String s
		for (int i = 0; i < s.length(); i++) {
			arr[s.charAt(i) - 'a']++;
		}

		char result = '\0';
		for (int i = 0; i < t.length(); i++) {
			if (arr[t.charAt(i) - 'a'] == 0) {
				result = t.charAt(i);
				break;
			}

			arr[t.charAt(i) - 'a']--;
		}

		return result;
	}
}
