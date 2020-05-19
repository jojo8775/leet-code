package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.

Given three integers a, b and c, return any string s, which satisfies following conditions:

s is happy and longest possible.
s contains at most a occurrences of the letter 'a', at most b occurrences of the letter 'b' and at most c occurrences of the letter 'c'.
s will only contain 'a', 'b' and 'c' letters.
If there is no such string s return the empty string "".

 

Example 1:

Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.
Example 2:

Input: a = 2, b = 2, c = 1
Output: "aabbc"
Example 3:

Input: a = 7, b = 1, c = 0
Output: "aabaa"
Explanation: It's the only correct answer in this case.
 

Constraints:

0 <= a, b, c <= 100
a + b + c > 0
Accepted
7,353
Submissions
15,878
 * @author jojo
 * May 18, 2020  10:58:27 PM
 */
public class LongestHappyString {
	public String longestDiverseString(int a, int b, int c) {
		
		// taking the max count first. 
		PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> {
			if (x.count == y.count) {
				return x.ch - y.ch;
			}

			return y.count - x.count;
		});

		// inserting only if count is > 0
		if (a > 0) {
			pq.offer(new Pair('a', a));
		}

		if (b > 0) {
			pq.offer(new Pair('b', b));
		}

		if (c > 0) {
			pq.offer(new Pair('c', c));
		}

		if (pq.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		// breaking at 1 as we can add at-most two character of the last king. 
		while (pq.size() > 1) {
			Pair first = pq.poll();
			Pair second = pq.poll();

			// if first pair count >= 2 add two. 
			if (first.count > 1) {
				sb.append(first.ch).append(first.ch);
				first.count -= 2;
			} else {
				sb.append(first.ch);
				first.count -= 1;
			}

			// if second pair count >= 2 and updated count of first is less than second count, 
			// add at-most two characters. 
			if (second.count > 1 && second.count >= first.count) {
				sb.append(second.ch).append(second.ch);
				second.count -= 2;
			} else {
				sb.append(second.ch);
				second.count -= 1;
			}

			// add first and second only if they have count > 0
			if (first.count > 0) {
				pq.offer(first);
			}

			if (second.count > 0) {
				pq.offer(second);
			}

		}

		if (pq.isEmpty()) {
			return sb.toString();
		}

		// if there is a remaining char kind, add at most two count of it.
		Pair top = pq.poll();

		for (int j = 1; j <= 2 && j <= top.count; j++) {
			sb.append(top.ch);
		}

		return sb.toString();
	}

	private static class Pair {
		int count;
		char ch;

		public Pair(char ch, int count) {
			this.ch = ch;
			this.count = count;
		}
	}
	
	public String longestDiverseString_adv(int a, int b, int c) {
		int aCount = 0, bCount = 0, cCount = 0;
		int len = a + b + c;
		
		StringBuilder sb = new StringBuilder();
		
		while(len-- > 0) {
			if((a >= b && a >= c && aCount != 2) || (bCount == 2 && a > 0) || (cCount == 2 && a > 0)) {
				sb.append('a');
				aCount ++;
				a--;
				cCount = 0;
				bCount = 0;
			}
			else if((b >= a && b >= c && bCount != 2) || (aCount == 2 && b > 0) || (cCount == 2 && b > 0)) {
				sb.append('b');
				bCount ++;
				b--;
				cCount = 0;
				aCount = 0;
			}
			else if((c >= a && c >= b && cCount != 2) || (bCount == 2 && c > 0) || (aCount == 2 && c > 0)) {
				sb.append('c');
				cCount ++;
				c--;
				aCount = 0;
				bCount = 0;
			}
		}
		
		return sb.toString();
	}
}
