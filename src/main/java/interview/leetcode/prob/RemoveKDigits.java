package interview.leetcode.prob;

import java.util.Stack;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 * @author jojo
 *
 */
public class RemoveKDigits {
	public String removeKdigits(String num, int k) {
		if (num.isEmpty() || k == num.length()) {
			return "0";
		}

		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < num.length(); i++) {
			char ch = num.charAt(i);

			// if the prev character is greater than current pop prev
			while (k > 0 && !stack.isEmpty() && ch < stack.peek()) {
				k--;
				stack.pop();
			}

			stack.push(ch);
		}

		// if there are repeating character like 11111
		while (k-- > 0) {
			stack.pop();
		}

		// removing zeros in front
		while (!stack.isEmpty() && stack.get(0).equals('0')) {
			stack.remove(0);
		}

		if (stack.isEmpty()) {
			return "0";
		}

		// constructing string
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		return sb.reverse().toString();
	}

	public static void main(String[] args) {
		System.out.println(new RemoveKDigits().removeKdigits("1432219", 3));

		System.out.println(new RemoveKDigits().removeKdigits("10200", 1));
	}
}
