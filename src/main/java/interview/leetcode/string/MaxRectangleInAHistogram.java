package interview.leetcode.string;

import java.util.Stack;

public class MaxRectangleInAHistogram
{
	public static void main(String[] args)
	{
		int[] heights = { 1, 2, 3, 2, 4 };
		System.out.println(new MaxRectangleInAHistogram().findArea(heights));
	}

	/**
	 * Find the max area of a rectangle which can be formed in a given
	 * histogram.
	 * 
	 * Assumptions: each buildings are of unit width there will be a building of
	 * at least height 1
	 */
	public int findArea(int[] heights)
	{
		if (heights.length < 1)
		{
			return 0;
		}

		Stack<Integer> heightStack = new Stack<Integer>();
		int maxArea = 0, count = 0;

		while(count < heights.length)
		{
			if (heightStack.isEmpty() || heightStack.peek() < heights[count])
			{
				heightStack.push(count);
				count++;
			}
			else
			{
				int topIndex = heightStack.pop();
				int stackTop = heightStack.isEmpty() ? 0 : heightStack.peek();
				maxArea = Math.max(maxArea, heights[topIndex] * (count - stackTop - 1));
			}
		}

		while (!heightStack.isEmpty())
		{
			int topIndex = heightStack.pop();
			int stackTop = heightStack.isEmpty() ? 0 : heightStack.peek();
			maxArea = Math.max(maxArea, heights[topIndex] * (heights.length - stackTop - 1));
		}

		return maxArea;
	}
}
