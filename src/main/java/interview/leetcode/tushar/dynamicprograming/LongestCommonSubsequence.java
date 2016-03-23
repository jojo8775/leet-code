package interview.leetcode.tushar.dynamicprograming;

public class LongestCommonSubsequence
{
	public static void main(String[] args)
	{
		char[] seq1 = { 'a', 'b', 'c', 'd', 'a', 'f' };
		char[] seq2 = { 'a', 'c', 'b', 'e', 'f' };

		findLongestCommonSubSequence(seq1, seq2);
	}

	private static void findLongestCommonSubSequence(char[] seq1, char[] seq2)
	{

		int[][] grid = new int[seq1.length + 1][seq2.length + 1];

		for (int i = 1; i <= seq1.length; i++)
		{
			for (int j = 1; j <= seq2.length; j++)
			{
				if (seq1[i - 1] == seq2[j - 1])
				{
					grid[i][j] = Math.max(grid[i][j - 1], grid[i - 1][j]) + 1;
				}
				else
				{
					grid[i][j] = Math.max(grid[i][j - 1], grid[i - 1][j]);
				}
			}
		}

		System.out.println(grid[seq1.length][seq2.length]);

		int y = seq1.length, x = seq2.length;

		while (y > 0)
		{
			if (grid[y][x] != grid[y-1][x])
			{
				System.out.print(seq1[y - 1]);
				
				while(x>0 && grid[y][x-1] == grid[y][x]){
					x--;
				}
				
				x--;
			}
			else
			{
				y--;
			}
		}
	}
}
