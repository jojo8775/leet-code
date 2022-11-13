package interview.leetcode.practice;

import java.util.Stack;

public class Prac9 {
	public static void main(String[] args) {
		var result = new Prac9().calculate("2*(5+5*2)/3+(6/2+8)");
		System.out.println(result);
	}

	public int calculate(String s) {
		int[] idx = { 0 };
		return calculate("(" + s + ")", idx);
	}

	private int calculate(String s1, int[] idx) {
		int num = 0, len = s1.length();
		char sign = '+';
		Stack<Integer> stack = new Stack<>();

		int i = idx[0];
		while (i < len) {
			char ch = s1.charAt(i++);

			if (ch >= '0' && ch <= '9') {
				num *= 10;
				num += (int) (ch - '0');
			} else if (ch == ' ') {
				continue;
			} else if (ch == '(') {
				idx[0] = i;
				num = calculate(s1, idx);
				i = idx[0];
			} else if (ch == ')') {
				if(!stack.isEmpty()) {
					compute(sign, stack, num);
				}
				else {
					stack.push(num);
				}
				
				idx[0] = i;
				return addAll(stack);
			} else {
				compute(sign, stack, num);
				num = 0;
				sign = ch;
			}
		}

		stack.push(num);
		idx[0] = len;
		return addAll(stack);
	}

	private void compute(char prevSign, Stack<Integer> stack, int num) {
		switch (prevSign) {
		case '+':
			stack.push(num);
			break;
		case '-':
			num *= -1;
			stack.push(num);
			break;
		case '*':
			num *= stack.pop();
			stack.push(num);
			break;
		case '/':
			num = stack.pop() / num;
			stack.push(num);
			break;
		}
	}

	private int addAll(Stack<Integer> stack) {
		int val = 0;
		while (!stack.isEmpty()) {
			val += stack.pop();
		}

		return val;
	}
}
