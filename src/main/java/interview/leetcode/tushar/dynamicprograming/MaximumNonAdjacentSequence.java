package interview.leetcode.tushar.dynamicprograming;

public class MaximumNonAdjacentSequence
{
	public static void main(String[] args)
	{
		// int[] nums = { 4, 1, 1, 4, 2, 1 };
		int[] nums = { 4, 15, 6 ,9,3};
		System.out.println(findMax(nums));
	}

	private static int findMax(int[] nums)
	{
		int[] arr = new int[nums.length];

		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = nums[i];
			for (int j = 0; j < i; j++)
			{
				if (i - j != 1)
				{
					if (arr[j] + nums[i] > arr[i])
					{
						arr[i] = arr[j] + nums[i];
					}
				}
				else if (arr[j] > arr[i])
				{
					arr[i] = arr[j];
				}
			}
		}
		

		return arr[arr.length - 1];
	}
}
