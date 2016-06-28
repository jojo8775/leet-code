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
//		int[] nums = {1,-1};
		int[] nums = { 648, 614, 490, 138, 657, 544, 745, 582, 738, 229, 775, 665, 876, 448, 4, 81, 807, 578, 712, 951,
				867, 328, 308, 440, 542, 178, 637, 446, 882, 760, 354, 523, 935, 277, 158, 698, 536, 165, 892, 327, 574,
				516, 36, 705, 900, 482, 558, 937, 207, 368 };
		int[] r = new SlidingWindow().maxSlidingWindow(nums, 9);
		print(r);
	}
	
	private static void print(int[] a){
		for(int i : a){
			System.out.print(i + ", ");
		}
	}
}
