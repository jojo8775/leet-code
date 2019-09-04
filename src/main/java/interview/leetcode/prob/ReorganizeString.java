package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].

 * @author jojo
 * Sep 4, 2019 1:27:06 AM
 */
public class ReorganizeString {
	public String reorganizeString(String S) {
		int[] arr = new int[26];
		for (char ch : S.toCharArray()) {
			arr[ch - 'a']++;

			// not possible
			if (arr[ch - 'a'] > (S.length() + 1) / 2) {
				return "";
			}
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

		for (int i = 0; i < 26; i++) {
			if(arr[i] != 0) {
				pq.offer(new int[] { i, arr[i] });
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			int[] first = pq.poll();
			if (sb.length() == 0 || sb.charAt(sb.length() - 1) != (char) (first[0] + 'a')) {
				sb.append((char) (first[0] + 'a'));

				if (--first[1] > 0) {
					pq.offer(first);
				}
			} else {
				int[] second = pq.poll();
				sb.append((char) (second[0] + 'a'));

				if (--second[1] > 0) {
					pq.offer(second);
				}

				pq.offer(first);
			}
		}

		return sb.toString();
	}
	
	public static void main(String[] args) {
		String result = new ReorganizeString().reorganizeString("aab");
		System.out.println(result);
	}
}
