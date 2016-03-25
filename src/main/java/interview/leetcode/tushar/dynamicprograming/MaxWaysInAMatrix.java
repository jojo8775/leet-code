package interview.leetcode.tushar.dynamicprograming;

public class MaxWaysInAMatrix
{
	public static void main(String[] args)
	{
		findNumberOfways(3);
		findNumberOfwaysWithObstruction(3, 0, 2);
	}

	private static void findNumberOfways(int n)
	{
		int[][] matrix = new int[n][n];

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (i == 0)
				{
					matrix[i][j] = 1;
				}
				else if (j == 0)
				{
					matrix[i][j] = 1;
				}
				else
				{
					matrix[i][j] = matrix[i][j - 1] + matrix[i - 1][j];
				}
			}
		}

		System.out.println(matrix[n - 1][n - 1]);
	}

	private static void findNumberOfwaysWithObstruction(int n, int x, int y)
	{
		int[][] matrix = new int[n][n];
		matrix[0][0] = 1;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if(i==0 && j==0){
					continue;
				}
				
				if (i == y && j == x)
				{
					matrix[i][j] = 0;
				}
				else if (i == 0)
				{
					matrix[i][j] = matrix[i][j-1];
				}
				else if (j == 0)
				{
					matrix[i][j] = matrix[i-1][j];
				}
				else
				{
					matrix[i][j] = matrix[i][j - 1] + matrix[i - 1][j];
				}
			}
		}

		System.out.println(matrix[n - 1][n - 1]);
	}
}
