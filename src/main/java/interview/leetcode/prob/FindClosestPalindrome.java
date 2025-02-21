package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given a string n representing an integer, return the closest integer (not including itself), which is a palindrome. If there is a tie, return the smaller one.

The closest is defined as the absolute difference minimized between two integers.

 

Example 1:

Input: n = "123"
Output: "121"
Example 2:

Input: n = "1"
Output: "0"
Explanation: 0 and 2 are the closest palindromes but we return the smallest which is 0.
 

Constraints:

1 <= n.length <= 18
n consists of only digits.
n does not have leading zeros.
n is representing an integer in the range [1, 1018 - 1].
 * 
 * Feb 1, 2025 - 2:26:27 PM
 * Jojo 
 */
public class FindClosestPalindrome {
	public String nearestPalindromic(String n) {
		long num = Long.parseLong(n);
		long higher = findHigher(num + 1);
		long lower = findLower(num - 1);

        return higher - num < num - lower ?  String.valueOf(higher) : String.valueOf(lower);
		//return Math.abs(num - lower) > Math.abs(higher - num) ? String.valueOf(higher) : String.valueOf(lower);
	}

	private long findHigher(long num) {
		String str = String.valueOf(num);

		char[] s = str.toCharArray(), t = str.toCharArray();

		int len = str.length();

		// make it as palindrome. This is also the best assumed palindrom as 1223 ->
		// best is 1221
		for (int i = 0, j = len - 1; i < len / 2; i++, j--) {
			//t[len - 1 - i] = t[i];
            t[j] = t[i];
		}

		for (int i = 0; i < len; i++) {
			if (s[i] < t[i]) {
				// our assumed palindrom is the lowest
				return Long.parseLong(String.valueOf(t));
			}
			else if (s[i] > t[i]) {
                int mid = (len - 1)/2;
                for(int j=mid; j>=0; j--){
				//for (int j = (len - 1) / 2; j >= 0; j--) {

					// bumping the best assumed palindrom to next higher possible value;
					if (++t[j] > '9') {
						t[j] = '0';
					} else {
						break;
					}
				}

				// converting the number back to palindrom
				//for (int j = 0; j < len / 2; j++) {
                for(int k=len-1, j=0; j<=mid; j++, k--){
					//t[len - 1 - j] = t[j];
                    t[k] = t[j];
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
        int mid = (len - 1)/2;

		// make it as palindrome. This is also the best assumed palindrom as 1223 ->
		// best is 1221
		for (int i = 0, j=len-1; i <= mid; i++, j--) {
			t[j] = t[i];
		}

		for (int i = 0; i < len; i++) {
			if (s[i] > t[i]) {
				// our assumed palindrom is the lowest
				return Long.parseLong(String.valueOf(t));
			} 
			else if (s[i] < t[i]) {
				//for (int j = (len - 1) / 2; j >= 0; j--) {
                for(int j=mid; j>=0; j--){
					// bumping the best assumed palindrom to next higher possible value;
					if (--t[j] < '0') {
						t[j] = '9';
					} else {
						break;
					}
				}
				
				if(t[0] == '0') {
					char[] cArr = new char[len - 1];
					Arrays.fill(cArr, '9');
					t = cArr;
					return Long.parseLong(String.valueOf(t));
				}

				// converting the number back to palindrom
				for (int j = 0, k=len-1; j <= mid; j++, k--) {
					t[k] = t[j];
				}

				return Long.parseLong(String.valueOf(t));
			}
		}

		// the best assumed palindrome was same as num
		return Long.parseLong(String.valueOf(t));
	}
}