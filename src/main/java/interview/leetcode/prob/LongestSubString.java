package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

public class LongestSubString {
	public int longestSubstring(String s, int k) {
		if (s == null || s.length() < 1)
			return 0;

		int[][] letters = new int[26][s.length() + 1];
		for (int i = 0; i < s.length(); i++) {
			for (int c = 0; c < 26; c++) {
				letters[c][i + 1] = letters[c][i];
			}
			letters[s.charAt(i) - 'a'][i + 1] += 1;
		}
		// May also optimize by deleting letters entries with 0 at end

		int longest = 0;
		for (int start = 0; start < s.length(); start++) {
			if (longest >= s.length() - start)
				return longest;
			for (int end = s.length(); end > start; end--) {
				boolean works = true;
				for (int c = 0; c < 26; c++) {
					int num = letters[c][end] - letters[c][start];
					if (num < k && num > 0) {
						works = false;
						end = s.indexOf('a' + c, start) + 1;
						break;
					}
				}
				if (works) {
					if (end - start > longest)
						longest = end - start;
					break;
				}
			}
		}

		return longest;
	}

	public int sol(String s, int k) {
		helper(0, s.length() - 1, s, k);

		return longest;
	}

	private int longest = 0;

	private void helper(int beg, int end, String str, int limit) {
		if (beg > end || longest > (end - beg) || limit > (end - beg + 1)) {
			return;
		}

		Map<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = beg; i <= end; i++) {
			char ch = str.charAt(i);
			map.computeIfPresent(ch, (k, v) -> v + 1);
			map.computeIfAbsent(ch, v -> 1);
		}

		int idx = 0;
		for (idx = beg; idx <= end; idx++) {
			char ch = str.charAt(idx);
			if (map.get(ch) < limit) {
				break;
			}
		}

		if (idx > end) {
			longest = Math.max(longest, end - beg + 1);
		} else {
			int prev = beg;
			char ch = str.charAt(idx);
			for(int i = beg; i<=end; i++){
				if(str.charAt(i) == ch){
					helper(prev, i - 1, str, limit);
					prev = i + 1;
				}
			}
		}
	}

	/**
	 * This is
	 */
	public int longestSubstring_recur(String s, int k) {
		if (s == null || s.length() == 0 || k == 0 || (s.length() < k))
			return 0;
		if (s.length() == 1 && k == 1)
			return 1;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : s.toCharArray()) {
			if (!map.containsKey(c)) {
				map.put(c, 1);
			} else {
				map.put(c, map.get(c) + 1);
			}
		}
		int min = Integer.MAX_VALUE;
		char c = 'a';
		for (char m : map.keySet()) {
			if (min > map.get(m)) {
				min = map.get(m);
				c = m;
			}
		}

		if (min >= k)
			return s.length();
		else {
			String parts[] = s.split("" + c);
			int len = 0;
			for (int i = 0; i < parts.length; i++) {
				len = Math.max(len, longestSubstring(parts[i], k));
			}
			return len;
		}
	}

	public static void main(String[] args) {
		String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcabb";
		System.out.println(new LongestSubString().sol(s, 3));
	}
}
