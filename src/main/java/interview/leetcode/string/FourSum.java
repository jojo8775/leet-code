package interview.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum
{
	public static void main(String[] args)
	{
		FourSum fs = new FourSum();
		int[] S = { 1, 0, -1, 0, -2, 2 };

		List<List<Integer>> result = fs.fourSum(S, 0);
		for (List<Integer> resultItem : result)
		{
			for (int i : resultItem)
			{
				System.out.print(i + ", ");
			}
			
			System.out.println();
		}
	}

	/**
	 * Given an array S of n integers, are there elements a, b, c, and d in S
	 * such that a + b + c + d = target? Find all unique quadruplets in the
	 * array which gives the sum of target.
	 * 
	 * Note: Elements in a quadruplet (a,b,c,d) must be in non-descending order.
	 * (ie, a ≤ b ≤ c ≤ d) The solution set must not contain duplicate
	 * quadruplets. For example, given array S = {1 0 -1 0 -2 2}, and target =
	 * 0.
	 * 
	 * A solution set is: (-1, 0, 0, 1) (-2, -1, 1, 2) (-2, 0, 0, 2)
	 */
	public List<List<Integer>> fourSum(int[] nums, int target)
	{
        Arrays.sort(nums);

		List<List<Integer>> result = new ArrayList<List<Integer>>();

		for (int i = 0; i < nums.length - 3; i++)
		{
			// skip duplicate numbers
			if (i > 0 && nums[i - 1] == nums[i])
			{
				continue;
			}

			for (int j = i + 1; j < nums.length - 2; j++)
			{
				// skip duplicate numbers
				if (j > i + 1 && nums[j - 1] == nums[j])
				{
					continue;
				}

				int start = j + 1;
				int end = nums.length - 1;

				while (start < end)
				{
					if (target == nums[i] + nums[j] + nums[start] + nums[end])
					{
						result.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));

                        // skip duplicate numbers
						do{
						    start++;
						}
						while (start < end && nums[start] == nums[start - 1]);
						
						do{
						    end--;
						}
						while (end > start && nums[end] == nums[end + 1]);
					}else if (target > nums[i] + nums[j] + nums[start] + nums[end])
					{
						start++;
					}
					else
					{
						end--;
					}
				}
			}
		}
		
		return result;
	}
}
