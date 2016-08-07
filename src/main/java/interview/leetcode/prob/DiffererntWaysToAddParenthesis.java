package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]
 * @author jojo
 *
 */
public class DiffererntWaysToAddParenthesis {
	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> result = new ArrayList<Integer>();
		if (input == null || input.isEmpty()) {
			return result;
		}

		List<Entry> entries = divideAndConqure(input);
		print(entries);
		
		for(Entry e : entries){
			result.add(e.val);
		}
		
		return result;
	}
	
	private void print(List<Entry> entries){
		for(Entry e : entries){
			System.out.println(e.disp + " : " + e.val);
		}
	}

	private List<Entry> divideAndConqure(String input) {
		List<Entry> nums = new ArrayList<Entry>();
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);

			if (!isOperand(ch)) {
				continue;
			}

			List<Entry> left = divideAndConqure(input.substring(0, i));
			List<Entry> right = divideAndConqure(input.substring(i + 1));

			for (Entry iLeft : left) {
				for (Entry iRight : right) {
					String s = "( " + iLeft.disp + " " +  ch + " " + iRight.disp + " )";
					nums.add(new Entry(calculate(iLeft.val, iRight.val, ch), s));
				}
			}
		}

		if (nums.isEmpty()) {
			nums.add(new Entry(Integer.parseInt(input), input));
		}

		return nums;
	}

	private boolean isOperand(char ch) {
		return ch == '*' || ch == '-' || ch == '+';
	}

	private int calculate(int a, int b, char op) {
		int val = 0;
		if (op == '*') {
			val = a * b;
		} else if (op == '-') {
			val = a - b;
		} else {
			val = a + b;
		}

		return val;
	}
	
	private static class Entry{
		int val;
		String disp;
		
		public Entry(int val, String disp){
			this.val = val;
			this.disp = disp;
		}
	}

	public static void main(String[] args) {
		new DiffererntWaysToAddParenthesis().diffWaysToCompute("2*3-4*5");
	}
}
