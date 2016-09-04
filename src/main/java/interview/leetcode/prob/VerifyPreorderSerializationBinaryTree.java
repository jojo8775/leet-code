package interview.leetcode.prob;

import java.util.Stack;

public class VerifyPreorderSerializationBinaryTree {
	public boolean isValidSerialization(String preorder) {
		String[] strArr = preorder.split(",");
		Stack<int[]> stack = new Stack<int[]>();

		int idx = 0;
		stack.push(new int[] { 0, Integer.valueOf(strArr[idx++])});
		while (idx < strArr.length) {
			if (stack.isEmpty()) {
				return false;
			} else if (stack.peek()[0] == 1) {
				stack.pop();
			} else {
				stack.peek()[0] = 1;
			}

			if (!strArr[idx].equals("#")) {
				stack.push(new int[] { 0 , Integer.valueOf(strArr[idx])});
			}
			idx++;
		}

		return stack.isEmpty();
	}

	public static void main(String[] args) {
		System.out
				.println(new VerifyPreorderSerializationBinaryTree().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
	}
}
