package interview.leetcode.practice;

import java.util.Stack;

public class ScoreBoard {
	public int findScore(String[] scores) {
		Stack<Integer> stack = new Stack<>();
		int result = 0;
		
		for(String score : scores) {
			if(score.equals("Z")) {
				result -= stack.pop();
			}
			else if(score.equals("+")) {
				int n1 = stack.pop(), n2 = stack.pop();
				int n3 = n1 + n2;
				result += n3;
				stack.push(n2);
				stack.push(n1);
				stack.push(n3);
			}
			else if(score.equals("X")) {
				int n = 2 * stack.peek();
				stack.push(n);
				result += n;
			}
			else {
				int n = Integer.parseInt(score);
				stack.push(n);
				result += n;
			}
		}
		
		return result;
	}
}
