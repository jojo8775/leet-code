package interview.leetcode.string;

public class TrappingRainWater
{
	/**
	 * Given n non-negative integers representing an elevation map where the
	 * width of each bar is 1, compute how much water it is able to trap after
	 * raining.
	 * 
	 * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
	 */
	public int trap(int[] height)
	{
		if (height.length <= 1)
		{
			return 0;
		}

		// For each tower to have water on it, there has to be a taller building
		// on left or right.

		// To find the max left tower on left for a given building
		int[] left = new int[height.length];
		left[0] = height[0];
		for (int i = 1; i < height.length; i++)
		{
			left[i] = Math.max(left[i - 1], height[i]);
		}

		// To find the max right tower on left for a given building
		int[] right = new int[height.length];
		right[right.length - 1] = height[height.length - 1];
		for (int i = height.length - 2; i >= 0; i--)
		{
			right[i] = Math.max(height[i], right[i + 1]);
		}

		int waterCount = 0;

		for (int i = 0; i < height.length; i++)
		{
			waterCount += Math.max(Math.min(left[i], right[i]) - height[i], 0);
		}

		return waterCount;
	}
}
