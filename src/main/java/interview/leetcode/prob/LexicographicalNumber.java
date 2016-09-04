package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given an integer n, return 1 - n in lexicographical order.
 * 
 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 * 
 * Please optimize your algorithm to use less time and space. The input size may
 * be as large as 5,000,000.
 * 
 * 
 * @author jojo
 *
 */
public class LexicographicalNumber {
	public List<Integer> lexicalOrder(int n) {
		List<Integer> result = new ArrayList<Integer>();

		Stack<int[]> stack = new Stack<int[]>();
		stack.push(new int[] { 1, 0 });
		result.add(1);

		while (!stack.isEmpty()) {
			int[] top = stack.peek();

			// try to add a digit
			if (top[1] == 0) {
				top[1] = 1;
				int val2 = top[0] * 10;
				if (val2 <= n) {
					stack.push(new int[] { val2, 0 });
					result.add(val2);
				}
			}
			// else increment
			else {
				stack.pop();
				int val2 = top[0] + 1;
				if (val2 <= n && val2 % 10 != 0) {
					stack.push(new int[] { val2, 0 });
					result.add(val2);
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		List<Integer> result = new LexicographicalNumber().lexicalOrder(102);
		for (int n : result) {
			System.out.println(n);
		}
	}
}
