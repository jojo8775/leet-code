package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given an integer n, find the closest integer (not including itself), which is a palindrome.

The 'closest' is defined as absolute difference minimized between two integers.

Example 1:
Input: "123"
Output: "121"
Note:
The input n is a positive integer represented by string, whose length will not exceed 18.
If there is a tie, return the smaller one as answer.
Accepted
15,117
Submissions

 * @author jojo
 * Aug 24, 2019 12:03:53 PM
 */
public class FindTheClosestPalindrome {
	public String nearestPalindromic(String n) {
		long num = Long.parseLong(n);
		long higher = findHigher(num + 1);
		long lower = findLower(num - 1);

		return Math.abs(num - lower) > Math.abs(higher - num) ? String.valueOf(higher) : String.valueOf(lower);
	}

	private long findHigher(long num) {
		String str = String.valueOf(num);

		char[] s = str.toCharArray(), t = str.toCharArray();

		int len = str.length();

		// make it as palindrome. This is also the best assumed palindrom as 1223 ->
		// best is 1221
		for (int i = 0; i < len / 2; i++) {
			t[len - 1 - i] = t[i];
		}

		for (int i = 0; i < len; i++) {
			if (s[i] < t[i]) {
				// our assumed palindrom is the lowest
				return Long.parseLong(String.valueOf(t));
			}
			else if (s[i] > t[i]) {
				for (int j = (len - 1) / 2; j >= 0; j--) {
					// bumping the best assumed palindrom to next higher possible value;
					if (++t[j] > '9') {
						t[j] = '0';
					} else {
						break;
					}
				}

				// converting the number back to palindrom
				for (int j = 0; j < len / 2; j++) {
					t[len - 1 - j] = t[j];
				}

				return Long.parseLong(String.valueOf(t));
			}
		}

		// the best assumed palindrome was same as num
		return Long.parseLong(String.valueOf(t));
	}

	private long findLower(long num) {
		String str = String.valueOf(num);

		char[] s = str.toCharArray(), t = str.toCharArray();

		int len = str.length();

		// make it as palindrome. This is also the best assumed palindrom as 1223 ->
		// best is 1221
		for (int i = 0; i < len / 2; i++) {
			t[len - 1 - i] = t[i];
		}

		for (int i = 0; i < len; i++) {
			if (s[i] > t[i]) {
				// our assumed palindrom is the lowest
				return Long.parseLong(String.valueOf(t));
			} 
			else if (s[i] < t[i]) {
				for (int j = (len - 1) / 2; j >= 0; j--) {
					// bumping the best assumed palindrom to next higher possible value;
					if (--t[j] < '0') {
						t[j] = '9';
					} else {
						break;
					}
				}
				
				// if the lowest number first digit is 0 then time to make it one decimal lower. 01 -> 9
				if(t[0] == '0') {
					char[] cArr = new char[len - 1];
					Arrays.fill(cArr, '9');
					t = cArr;
					return Long.parseLong(String.valueOf(t));
				}

				// converting the number back to palindrom
				for (int j = 0; j < len / 2; j++) {
					t[len - 1 - j] = t[j];
				}

				return Long.parseLong(String.valueOf(t));
			}
		}

		// the best assumed palindrome was same as num
		return Long.parseLong(String.valueOf(t));
	}
}
