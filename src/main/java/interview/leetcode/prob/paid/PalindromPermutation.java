package interview.leetcode.prob.paid;

import java.util.Arrays;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:

Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
Show Company Tags
Show Tags
Show Similar Problems

 * @author jojo
 *
 */
public class PalindromPermutation {
	public boolean canPermutePalindrome(String s) {
		boolean[] idxArr = new boolean[256];
		Arrays.fill(idxArr, true);

		int oddCount = 0;

		for (int i = 0; i < s.length(); i++) {
			int idx = (int) s.charAt(i);
			idxArr[idx] = !idxArr[idx];

			if (idxArr[idx]) {
				oddCount--;
			} else {
				oddCount++;
			}
		}

		return (s.length() % 2 == 0) ? oddCount == 0 : oddCount == 1;
	}
}
