package interview.leetcode.prob;

/**
 * Given two strings a and b, return true if you can swap two letters in a so the result is equal to b, otherwise, return false.

Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at a[i] and a[j].

For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 

Example 1:

Input: a = "ab", b = "ba"
Output: true
Explanation: You can swap a[0] = 'a' and a[1] = 'b' to get "ba", which is equal to b.
Example 2:

Input: a = "ab", b = "ab"
Output: false
Explanation: The only letters you can swap are a[0] = 'a' and a[1] = 'b', which results in "ba" != b.
Example 3:

Input: a = "aa", b = "aa"
Output: true
Explanation: You can swap a[0] = 'a' and a[1] = 'a' to get "aa", which is equal to b.
Example 4:

Input: a = "aaaaaaabc", b = "aaaaaaacb"
Output: true
 

Constraints:

1 <= a.length, b.length <= 2 * 104
a and b consist of lowercase letters.
Accepted
89,080
Submissions
306,142
 * @author jojo
 * Apr 18, 2021  5:35:44 PM
 */
public class BuddyString {
	public boolean buddyStrings(String a, String b) {
		if (a.length() != b.length()) {
			return false;
		}

		int[] arr = new int[26];

		boolean containsDuplicateChar = false;
		for (char ch : a.toCharArray()) {
			arr[ch - 'a']++;

			if (arr[ch - 'a'] > 1) {
				containsDuplicateChar = true;
			}
		}

		for (char ch : b.toCharArray()) {
			if (--arr[ch - 'a'] < 0) {
				return false;
			}
		}

		int count = 0;
		for (int i = 0; i < a.length() && count < 3; i++) {
			if (a.charAt(i) != b.charAt(i)) {
				count++;
			}
		}

		if (count == 0) {
			return containsDuplicateChar;
		}

		return count < 3;
	}
}
