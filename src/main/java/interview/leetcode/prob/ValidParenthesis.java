package interview.leetcode.prob;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid
 * but "(]" and "([)]" are not.
 * 
 * @author jojo
 *
 */
public class ValidParenthesis {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '{') {
                stack.push('}');
            } else if (ch == '[') {
                stack.push(']');
            } else {
                if (stack.isEmpty() || stack.peek() != ch) {
                    return false;
                }

                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
