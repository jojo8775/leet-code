package interview.leetcode.prob;

/**
 * Given a binary string s and a positive integer n, return true if the binary representation of all the integers in the range [1, n] are substrings of s, or false otherwise.

A substring is a contiguous sequence of characters within a string.

 

Example 1:

Input: s = "0110", n = 3
Output: true
Example 2:

Input: s = "0110", n = 4
Output: false
 

Constraints:

1 <= s.length <= 1000
s[i] is either '0' or '1'.
1 <= n <= 109
Accepted
31,738
Submissions
55,147
 * @author jojo
 * Nov 22, 2022 9:04:59 AM
 */
public class BinaryStringsWithSubstringsRepresenting1ToN {
	public boolean queryString(String s, int n) {

		// if the n = 2000, the representation if 1001 - 2000 can be represented
		// then 0 - 1000 will be covered.
		for (int i = n; i > n / 2; i--) {
			if (!s.contains(Integer.toBinaryString(i))) {
				return false;
			}
		}

		return true;
	}
}
