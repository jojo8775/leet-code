package interview.leetcode.warmup;

public class SlidingWindow
{
	public int[] maxSlidingWindow(int[] nums, int k)
	{
		if (nums.length < 2)
		{
			return nums;
		}

		int maxEntry = Integer.MIN_VALUE;
		int[] maxArr = new int[nums.length - k + 1];

		for (int i = 0; i < k; i++)
		{
			maxEntry = Math.max(maxEntry, nums[i]);
		}

		maxArr[0] = maxEntry;

		for (int i = 1; i <= nums.length - k; i++)
		{
			if (nums[i + k - 1] >= maxEntry)
			{
				maxEntry = nums[i + k - 1];
			} else if (nums[i - 1] == maxEntry)
			{
				maxEntry = Integer.MIN_VALUE;

				for (int j = i; j < i + k; j++)
				{
					maxEntry = Math.max(maxEntry, nums[j]);
				}
			}

			maxArr[i] = maxEntry;
		}

		return maxArr;
	}
	
	public static void main(String[] args){
		int[] nums = {1,-1};
		int[] r = new SlidingWindow().maxSlidingWindow(nums, 1);
	}
}
