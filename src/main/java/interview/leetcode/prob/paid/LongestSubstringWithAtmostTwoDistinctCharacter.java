package interview.leetcode.prob.paid;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtmostTwoDistinctCharacter {
	// idea is to implement minimum window span logic
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		Map<Character, Integer> charMap = new HashMap<Character, Integer>();

		int beg = 0, end = s.length() - 1, cur = 0, maxLength = 0;
		boolean isValid = true;
		while (cur <= end) {
			while (isValid && cur <= end && charMap.size() <= 2) {
				char ch = s.charAt(cur++);
				charMap.computeIfPresent(ch, (k, v) -> v + 1);
				charMap.computeIfAbsent(ch, v -> 1);

				if (charMap.get(ch) > 2) {
					isValid = false;
				}
			}
			
			maxLength = Math.max(maxLength, cur - beg - 1);

			while (!isValid || charMap.size() > 2) {
				char ch = s.charAt(beg++);
				if (charMap.get(ch) > 2) {
					isValid = true;
				}

				charMap.put(ch, charMap.get(ch) - 1);
				if (charMap.get(ch) == 0) {
					charMap.remove(ch);
				}
			}
		}

		return maxLength;
	}

	public static void main(String[] args) {
		System.out.println(
				new LongestSubstringWithAtmostTwoDistinctCharacter().lengthOfLongestSubstringTwoDistinct("eceab"));
	}
}
