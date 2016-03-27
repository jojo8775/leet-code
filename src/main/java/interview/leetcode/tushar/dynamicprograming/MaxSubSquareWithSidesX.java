package interview.leetcode.tushar.dynamicprograming;

public class MaxSubSquareWithSidesX
{
	public static void main(String[] args)
	{
		int[][] arr = new int[5][];

		arr[0] = createArr(0, 0, 0, 0, 1);
		arr[1] = createArr(1, 0, 1, 1, 1);
		arr[2] = createArr(1, 0, 1, 0, 1);
		arr[3] = createArr(1, 1, 1, 1, 1);
		arr[4] = createArr(1, 0, 1, 1, 1);

		System.out.println(findMaxSubSquareSize(arr));
	}

	private static int[] createArr(int... i)
	{
		return i;
	}

	private static int findMaxSubSquareSize(int[][] matrix)
	{
		GridBlock[][] board = new GridBlock[matrix.length][matrix[0].length];

		board[0][0] = matrix[0][0] == 1 ? new GridBlock(1, 1) : new GridBlock(0, 0);

		GridBlock maxSoFar = new GridBlock(0, 0);

		for (int i = 1; i < matrix.length; i++)
		{
			if (matrix[i][0] == 1)
			{
				board[i][0] = new GridBlock(board[i - 1][0].y + 1, board[i - 1][0].x);

				if (Math.min(maxSoFar.x, maxSoFar.y) < Math.min(board[i][0].x, board[i][0].y))
				{
					maxSoFar = board[i][0];
				}
			}
			else
			{
				board[i][0] = new GridBlock(0, 0);
			}
		}

		for (int i = 1; i < matrix[0].length; i++)
		{
			if (matrix[0][i] == 1)
			{
				board[0][i] = new GridBlock(board[0][i - 1].y, board[0][i - 1].x + 1);

				if (Math.min(maxSoFar.x, maxSoFar.y) < Math.min(board[0][i].x, board[0][i].y))
				{
					maxSoFar = board[0][i];
				}
			}
			else
			{
				board[0][i] = new GridBlock(0, 0);
			}
		}

		for (int i = 1; i < matrix.length; i++)
		{
			for (int j = 1; j < matrix[0].length; j++)
			{
				if (matrix[i][j] == 1)
				{
					board[i][j] = new GridBlock(board[i - 1][j].y + 1, board[i][j - 1].x + 1);

					if (Math.min(maxSoFar.x, maxSoFar.y) < Math.min(board[i][j].x, board[i][j].y))
					{
						maxSoFar = board[i][j];
					}
				}
				else
				{
					board[i][j] = new GridBlock(0, 0);
				}
			}
		}

		print(board);
		
		return Math.min(maxSoFar.x, maxSoFar.y);
	}

	private static void print(GridBlock[][] board)
	{
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[0].length; j++){
				System.out.print("(" + board[i][j].y + "," + board[i][j].x + ") ");
			}
			
			System.out.println();
		}
	}

	private static class GridBlock
	{
		public final int x;
		public final int y;

		public GridBlock(int y, int x)
		{
			this.x = x;
			this.y = y;
		}
	}
}
