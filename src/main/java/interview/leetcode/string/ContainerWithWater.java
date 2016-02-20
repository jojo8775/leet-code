package interview.leetcode.string;

public class ContainerWithWater
{
	/**
	 * Given n non-negative integers a1, a2, ..., an, where each represents a
	 * point at coordinate (i, ai). n vertical lines are drawn such that the two
	 * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which
	 * together with x-axis forms a container, such that the container contains
	 * the most water.
	 * 
	 * Note: You may not slant the container.
	 */

	public int maxArea(int[] height)
	{
		if (height.length < 2)
		{
			return 0;
		}

		int maxArea = 0;
		int left = 0, right = height.length - 1, leftMax = 0, rightMax = 0;

		while (left < right)
		{
			if (rightMax > height[right])
			{
				right--;
				continue;
			}

			if (leftMax > height[left])
			{
				left++;
				continue;
			}

			rightMax = height[right];
			leftMax = height[left];

			if (leftMax > rightMax)
			{
				maxArea = Math.max(maxArea, rightMax * (right - left));
				right--;
			}
			else
			{
				maxArea = Math.max(maxArea, leftMax * (right - left));
				left++;
			}
		}

		return maxArea;
	}
}
