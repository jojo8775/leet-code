package interview.leetcode.prob.paid;

import java.util.List;
import java.util.Stack;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

Example 2:
Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)

Show Company Tags
Show Tags
Show Similar Problems

 * @author jojo
 *
 */
public class NestedListWeightSum {
	public int depthSum(List<NestedInteger> nestedList) {
		return findSum(nestedList, 1);
	}

	private int findSum(List<NestedInteger> nestedList, int depth) {
		int sum = 0;
		for (NestedInteger nestedInteger : nestedList) {
			if (nestedInteger.isInteger()) {
				sum += (depth * nestedInteger.getInteger());
			} else {
				sum += findSum(nestedInteger.getList(), depth + 1);
			}
		}

		return sum;
	}
	
	public int iterative_depthSum(List<NestedInteger> nestedList) {
        Stack<Pair> stack = new Stack<Pair>();
        stack.push(new Pair(nestedList, 1));
        int sum = 0;
        while (!stack.isEmpty()) {
            Pair top = stack.pop();
            for (NestedInteger nestedInteger : top.nestedList) {
                if (nestedInteger.isInteger()) {
                    sum += (top.depth * nestedInteger.getInteger());
                } else {
                    stack.push(new Pair(nestedInteger.getList(), top.depth + 1));
                }
            }
        }

        return sum;
    }

    private static class Pair {
        List<NestedInteger> nestedList;
        int depth;

        public Pair(List<NestedInteger> nestedList, int depth) {
            this.nestedList = nestedList;
            this.depth = depth;
        }
    }

	/**
	 * This is the interface that allows for creating nested lists. You should
	 * not implement it, or speculate about its implementation
	 */
	public interface NestedInteger {

		// @return true if this NestedInteger holds a single integer, rather
		// than a nested list.
		public boolean isInteger();

		// @return the single integer that this NestedInteger holds, if it holds
		// a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger();

		// @return the nested list that this NestedInteger holds, if it holds a
		// nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}
}
