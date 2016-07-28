package interview.leetcode.prob;

/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])

Hint:

A direct way is to use the backtracking approach.
Backtracking should contains three states which are (the current number, number of steps to get that number and a bitmask which represent which number is marked as visited so far in the current number). Start with state (0,0,0) and count all valid number till we reach number of steps equals to 10n.
This problem can also be solved using a dynamic programming approach and some knowledge of combinatorics.
Let f(k) = count of numbers with unique digits with length equals k.
f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [The first factor is 9 because a number cannot start with 0].
 * @author jojo
 *
 */
public class CountNumberWithUniqueDigits {
	private int res = 0;
	private boolean[] used = new boolean[10];

	public int countNumbersWithUniqueDigits(int n) {
		helper(n, false);
		return res;
	}

	public void helper(int n, boolean includeZero) {
		res++;
		if (n == 0)
			return;
		for (int i = includeZero ? 0 : 1; i <= 9; i++) {
			if (!used[i]) {
				used[i] = true;
				helper(n - 1, true);
				used[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(new CountNumberWithUniqueDigits().countNumbersWithUniqueDigits(11));
		System.out.println(new CountNumberWithUniqueDigits().countNumbersWithUniqueDigits(10));
	}
}
