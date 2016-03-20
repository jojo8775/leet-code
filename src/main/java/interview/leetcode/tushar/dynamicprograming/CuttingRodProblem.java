package interview.leetcode.tushar.dynamicprograming;

public class CuttingRodProblem
{
	public static void main(String[] args)
	{
		int[] rodPrices = { 2, 5, 7, 8 };
		findMaxProfit(rodPrices, 5);
	}

	public static void findMaxProfit(int[] prices, int length)
	{
		int[][] arr = new int[prices.length + 1][length + 1];

		for (int i = 1; i <= prices.length; i++)
		{
			for (int j = 1; j <= length; j++)
			{

				// if the current catalog rod size is greater than j skip it
				if (i > j)
				{
					arr[i][j] = arr[i - 1][j];
				}
				else
				{

					int profitWithEntry = prices[i - 1] + (j > i ? arr[i][j - i] : 0);
					int prevMaxProfit = arr[i - 1][j];

					arr[i][j] = Math.max(profitWithEntry, prevMaxProfit);
				}
			}
		}
		

		int x = prices.length, y = length;

		System.out.println("Max profit : " + arr[x][y]);
		System.out.println("Rod lengths are: ");

		while (x > 0 && y > 0)
		{
			if (arr[x][y] != arr[x - 1][y])
			{
				System.out.print(prices[x - 1] + ", ");
				y -= x;
			}
			else
			{
				x--;
			}
		}
	}
}
