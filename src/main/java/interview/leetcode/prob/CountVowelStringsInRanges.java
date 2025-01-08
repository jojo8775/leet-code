package interview.leetcode.prob;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * You are given a 0-indexed array of strings words and a 2D array of integers queries.

Each query queries[i] = [li, ri] asks us to find the number of strings present in the range li to ri (both inclusive) of words that start and end with a vowel.

Return an array ans of size queries.length, where ans[i] is the answer to the ith query.

Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.

 

Example 1:

Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
Output: [2,3,0]
Explanation: The strings starting and ending with a vowel are "aba", "ece", "aa" and "e".
The answer to the query [0,2] is 2 (strings "aba" and "ece").
to query [1,4] is 3 (strings "ece", "aa", "e").
to query [1,1] is 0.
We return [2,3,0].
Example 2:

Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
Output: [3,2,1]
Explanation: Every string satisfies the conditions, so we return [3,2,1].
 

Constraints:

1 <= words.length <= 105
1 <= words[i].length <= 40
words[i] consists only of lowercase English letters.
sum(words[i].length) <= 3 * 105
1 <= queries.length <= 105
0 <= li <= ri < words.length
 * 
 * Jan 1, 2025 - 10:15:11 PM Jojo
 */
public class CountVowelStringsInRanges {
	public int[] vowelStrings(String[] words, int[][] queries) {
		int len = words.length;
		int[] countArr = new int[len];

		Set<Character> set = new HashSet<>();
		set.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u'));

		for (int i = 0, count = 0; i < len; i++) {
			String s = words[i];

			if (set.contains(s.charAt(0)) && set.contains(s.charAt(s.length() - 1))) {
				count++;
			}

			countArr[i] = count;
		}

		int[] result = new int[queries.length];

		for (int i = 0; i < queries.length; i++) {
			int[] q = queries[i];

			int left = q[0] == 0 ? 0 : countArr[q[0] - 1];
			int right = countArr[q[1]];

			result[i] = right - left;
		}

		return result;
	}
}
