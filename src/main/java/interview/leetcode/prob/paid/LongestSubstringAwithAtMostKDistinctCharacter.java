package interview.leetcode.prob.paid;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring T that contains at
 * most k distinct characters.
 * 
 * For example, Given s = “eceba” and k = 2,
 * 
 * T is "ece" which its length is 3.
 * 
 * @author jojo
 *
 */
public class LongestSubstringAwithAtMostKDistinctCharacter {
	// idea is to implement maximum sliding window logic
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		Map<Character, Integer> charMap = new HashMap<Character, Integer>();
		int beg = 0, end = s.length() - 1, cur = beg, maxLength = 0;

		while (cur <= end) {
			// expanding the window size
			while (cur <= end && charMap.size() <= k) {
				char ch = s.charAt(cur++);
				charMap.computeIfPresent(ch, (u, v) -> v + 1);
				charMap.computeIfAbsent(ch, v -> 1);
			}

			// window is invalid so need to remove the last character
			if (charMap.size() > k) {
				maxLength = Math.max(maxLength, cur - beg - 1);
			}
			// window is valid so the last character can be considered
			else {
				maxLength = Math.max(maxLength, cur - beg);
			}

			// shrinking the window size
			while (charMap.size() > k) {
				char ch = s.charAt(beg++);
				charMap.put(ch, charMap.get(ch) - 1);

				if (charMap.get(ch) == 0) {
					charMap.remove(ch);
				}
			}
		}

		return maxLength;
	}
}
