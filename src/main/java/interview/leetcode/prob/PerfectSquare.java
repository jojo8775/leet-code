package interview.leetcode.prob;

/**
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 * 
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13,
 * return 2 because 13 = 4 + 9.
 * 
 * @author jojo
 *
 */
public class PerfectSquare
{
	public int findSquare(int n)
	{
		int[] arr = new int[n + 1];

		for (int i = 1; i <= n; i++)
		{
			arr[i] = Integer.MAX_VALUE;
			for (int j = 1; j * j <= i; j++)
			{
				arr[i] = Math.min(arr[i], arr[i - j * j] + 1);
			}
		}

		System.out.println(arr[n]);
		return arr[n];
	}

	public static void main(String[] args)
	{
		new PerfectSquare().findSquare(6);
	}
}
