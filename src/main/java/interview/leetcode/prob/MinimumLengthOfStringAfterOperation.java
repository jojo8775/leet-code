package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a string s.

You can perform the following process on s any number of times:

Choose an index i in the string such that there is at least one character to the left of index i that is equal to s[i], and at least one character to the right that is also equal to s[i].
Delete the closest character to the left of index i that is equal to s[i].
Delete the closest character to the right of index i that is equal to s[i].
Return the minimum length of the final string s that you can achieve.

 

Example 1:

Input: s = "abaacbcbb"

Output: 5

Explanation:
We do the following operations:

Choose index 2, then remove the characters at indices 0 and 3. The resulting string is s = "bacbcbb".
Choose index 3, then remove the characters at indices 0 and 5. The resulting string is s = "acbcb".
Example 2:

Input: s = "aa"

Output: 2

Explanation:
We cannot perform any operations, so we return the length of the original string.

 

Constraints:

1 <= s.length <= 2 * 105
s consists only of lowercase English letters.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
46.2K
Submissions
74K
Acceptance Rate
62.5%
 * 
 * Jan 12, 2025 - 7:24:26 PM Jojo
 */
public class MinimumLengthOfStringAfterOperation {

	public int minimumLength(String s) {
		// Map<Character, Queue<Integer>> map = new HashMap<>();
		Map<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			// map.computeIfAbsent(ch, v -> new LinkedList<>()).offer(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		int len = 0;
		for (char key : map.keySet()) {
			// Queue<Integer> queue = map.get(key);
			int val = map.get(key);

			if (val > 2) {
				if (val % 2 == 0) {
					len += 2;
				} else {
					len += 1;
				}
			} else {
				len += val;
			}

			/*
			 * if(queue.size() > 2){ if(queue.size() % 2 == 0){ len += 2; } else{ len += 1;
			 * } } else{ len += queue.size(); }
			 */

			/*
			 * while(queue.size() > 2){ int v1 = queue.poll(); int v2 = queue.poll(); int v3
			 * = queue.poll(); queue.offer(v2); }
			 * 
			 * 
			 * len += queue.size();
			 */
		}

		return len;
	}
}
