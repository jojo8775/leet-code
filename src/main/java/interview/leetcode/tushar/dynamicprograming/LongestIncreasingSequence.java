package interview.leetcode.tushar.dynamicprograming;

public class LongestIncreasingSequence
{

	public static void main(String[] args)
	{
		int[] input1 = { 3, 4, -1, 0, 6, 2, 3 };
		execute(input1);

		int[] input2 = { 2, 5, 1, 8, 3 };
		execute(input2);
	}

	private static void execute(int[] input)
	{
		int[] result = findLongestSubsequence(input);

		for (int i : result)
		{
			System.out.print(i + ", ");
		}
		System.out.println();
	}

	/**
	 * Find the longest subsequence.
	 */
	private static int[] findLongestSubsequence(int[] input)
	{
		if (input.length < 2)
		{
			return input;
		}

		int arr[] = new int[input.length];
		int arr2[] = new int[input.length];

		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = 1;
			arr2[i] = -1;
		}

		int currentIndex = 0;
		for (int i = 1; i < input.length; i++)
		{
			for (int j = 0; j < i; j++)
			{
				if (input[j] < input[i])
				{
					if(arr[j] + 1 > arr[i]){
						arr[i] = arr[j] + 1;
						arr2[i] = j;
					}
					
					if (arr[currentIndex] < arr[i])
					{
						currentIndex = i;
					}
				}
			}
		}

		int length = arr[currentIndex];
		int[] result = new int[length];

		do{
			result[--length] = input[currentIndex];
			currentIndex = arr2[currentIndex];
		}
		while(currentIndex >= 0);
		
		return result;
	}

}
