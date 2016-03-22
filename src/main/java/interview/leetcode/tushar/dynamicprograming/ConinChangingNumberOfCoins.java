package interview.leetcode.tushar.dynamicprograming;

public class ConinChangingNumberOfCoins
{
	public static void main(String[] args)
	{
		int[] coins = { 7, 2, 3, 6 };
		findCoins(coins, 13);
	}

	private static void findCoins(int[] coins, int sum)
	{

		int arr[] = new int[sum + 1];
		int arrRef[] = new int[arr.length];

		// initialize arr with max value
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = Integer.MAX_VALUE - 1;
			arrRef[i] = -1;
		}

		arr[0] = 0;

		for (int i = 0; i < coins.length; i++)
		{
			for (int j = 1; j < arr.length; j++)
			{
				if (j < coins[i])
				{
					continue;
				}
				else
				{
					int result = Math.min(arr[j], 1 + arr[j - coins[i]]);
					if (result != arr[j])
					{
						arr[j] = result;
						arrRef[j] = i;
					}
				}
			}
		}

		int index = arrRef.length - 1;
		while (arrRef[index] != -1)
		{
			System.out.print(coins[arrRef[index]] + ", ");
			index -= coins[arrRef[index]];
		}
	}
}
