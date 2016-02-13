package interview.leetcode.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class LongestSubstring
{
	public static void main(String[] args)
	{
		for (String s : Arrays.asList("aaaa", "aaab", "dvdt", "a", "anviaj", "pwwkew", "abba", "abcb", "ckilbkd", "aa", "aaabb"))
//		for (String s : Arrays.asList("aaaa", "abc", "aaabb"))
		{
			LongestSubstring longestSubstring = new LongestSubstring();
			System.out.println("longest substring = " + longestSubstring.lengthOfLongestSubstring(s));
		}
	}

	/**
	 * Given a string, find the length of the longest substring without
	 * repeating characters. For example, the longest substring without
	 * repeating letters for "abcabcbb" is "abc", which the length is 3. For
	 * "bbbbb" the longest substring is "b", with the length of 1.
	 */
	public int lengthOfLongestSubstring(String s)
	{
		if (s.isEmpty())
		{
			return 0;
		}

		if (s.length() == 1)
		{
			return 1;
		}

		int beg = 0, end = 1, maxlength = 1;

		Map<Character, Integer> charIndexTable = new HashMap<Character, Integer>();
		charIndexTable.put(s.charAt(beg), beg);

		while (end < s.length())
		{
			if (charIndexTable.containsKey(s.charAt(end)))
			{
			    maxlength = Math.max(maxlength, end - beg);
				beg = Math.max(beg, charIndexTable.get(s.charAt(end)) + 1);
			}

			charIndexTable.put(s.charAt(end), end);
			end++;
		}

		return  Math.max(maxlength, end - beg);
	}
}
