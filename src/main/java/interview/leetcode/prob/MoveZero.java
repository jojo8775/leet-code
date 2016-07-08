package interview.leetcode.prob;

/**
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 * 
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums
 * should be [1, 3, 12, 0, 0].
 * 
 * @author jojo
 *
 */
public class MoveZero
{
	public void moveZeroes(int[] nums)
	{
		int idx = 0;
		for (int i = 0; i < nums.length; i++)
		{
			if (nums[i] != 0)
			{
				nums[idx++] = nums[i];
			}
		}

		while (idx < nums.length)
		{
			nums[idx++] = 0;
		}
	}
}
