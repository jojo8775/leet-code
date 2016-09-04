package interview.leetcode.prob;

/**
 * Given a string, find the first non-repeating character in it and return it's
 * index. If it doesn't exist, return -1.
 * 
 * Examples:
 * 
 * s = "leetcode" return 0.
 * 
 * s = "loveleetcode", return 2.
 * 
 * @author jojo
 *
 */
public class FirstUniqueCharacterInAString {
	public int firstUniqChar(String s) {
		int[] arr = new int[26];

		// index the result
		for (int i = 0; i < s.length(); i++) {
			arr[s.charAt(i) - 'a']++;
		}

		// parse the result
		for (int i = 0; i < s.length(); i++) {
			if (arr[s.charAt(i) - 'a'] == 1) {
				return i;
			}
		}

		return -1;
	}
}
