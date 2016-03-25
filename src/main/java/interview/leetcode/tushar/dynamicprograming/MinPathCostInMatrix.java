package interview.leetcode.tushar.dynamicprograming;

public class MinPathCostInMatrix
{
	public static void main(String[] args)
	{
		int[][] arr = new int[3][];
		arr[0] = createArr(1,3,5,8);
		arr[1] = createArr(4,2,1,7);
		arr[2] = createArr(4,3,2,3);
		
		findMinimumCost(arr);
	}

	private static int[] createArr(int... args)
	{
		return args;
	}

	private static void findMinimumCost(int[][] arr)
	{
		int[][] grid = new int[arr.length][arr[0].length];
		grid[0][0] = arr[0][0];

		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[0].length; j++)
			{
				if (i == 0 && j == 0)
				{
					continue;
				}

				if (i == 0)
				{
					grid[i][j] = grid[i][j - 1] + arr[i][j];
				}
				else if (j == 0)
				{
					grid[i][j] = grid[i - 1][j] + arr[i][j];
				}
				else
				{
					grid[i][j] = Math.max(grid[i][j - 1], grid[i - 1][j]) + arr[i][j];
				}
			}
		}

		System.out.println(grid[grid.length - 1][grid[0].length - 1]);
	}
}
