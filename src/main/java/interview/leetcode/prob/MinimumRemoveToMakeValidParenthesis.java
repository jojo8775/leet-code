package interview.leetcode.prob;

/**
 * Given a string s of '(' , ')' and lowercase English characters. 

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 

Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
Example 4:

Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"
 

Constraints:

1 <= s.length <= 10^5
s[i] is one of  '(' , ')' and lowercase English letters.
Accepted
85,302
Submissions
136,331
 * @author jojo
 * Aug 20, 2020  10:50:01 PM
 */
public class MinimumRemoveToMakeValidParenthesis {
	public String minRemoveToMakeValid_adv(String s) {
		int extraOpen = 0;

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '(') {
				extraOpen++;
			} else if (ch == ')') {
				if (extraOpen == 0) {
					continue;
				}

				extraOpen--;
			}

			sb.append(ch);
		}

		int extraClose = 0;
		for (int i = sb.length() - 1; i >= 0; i--) {
			char ch = sb.charAt(i);
			if (ch == ')') {
				extraClose++;
			} else if (ch == '(') {
				if (extraClose == 0) {
					sb.deleteCharAt(i);
				} else {
					extraClose--;
				}
			}
		}

		return sb.toString();
	}

	public String minRemoveToMakeValid(String s) {
		StringBuilder sb = new StringBuilder(s);
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			if (ch == '(') {
				stack.push(i);
			} else if (ch == ')') {
				if (stack.isEmpty() || s.charAt(stack.peek()) != '(') {
					stack.push(i);
				} else {
					stack.pop();
				}
			}
		}

		while (!stack.isEmpty()) {
			sb.deleteCharAt(stack.pop());
		}

		return sb.toString();
	}
}
