package interview.leetcode.practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordBreak {
	public boolean wordBreak(String str, Set<String> dictinary) {
		boolean[] dp = new boolean[str.length() + 1];
		dp[0] = true;
		
		for (int i = 1; i <= str.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] && dictinary.contains(str.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}

		return dp[str.length()];
	}

	public static void main(String[] args) {
		System.out.println(new WordBreak().wordBreak("catsanddogs",
				new HashSet<String>(Arrays.asList("dogs", "cats", "cat", "sand", "and"))));
	}
}
