package interview.leetcode.prob;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
 * @author jojo
 *
 */
public class WordBreak {
	public boolean wordBreak(String s, Set<String> wordDict) {
		boolean[] f = new boolean[s.length() + 1];

		f[0] = true;

		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (f[j] && wordDict.contains(s.substring(j, i))) {
					f[i] = true;
					break;
				}
			}
		}

		return f[s.length()];
	}

	public static void main(String[] args) {
		System.out.println(new WordBreak().wordBreak("leetcode", new HashSet<String>(Arrays.asList("leet", "code"))));
	}
}
