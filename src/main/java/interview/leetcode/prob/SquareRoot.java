package interview.leetcode.prob;

/**
 * Implement int sqrt(int x).
 * 
 * Compute and return the square root of x.
 * 
 * @author jojo
 *
 */
public class SquareRoot
{
	public int mySqrt(int x)
	{
		if (x < 2)
		{
			return x;
		}

		int beg = 0, end = x, mid = 0;
		while (beg < end)
		{
			mid = (beg + end) / 2;
			if (x / mid >= mid)
			{
				beg = mid + 1;
			} else
			{
				end = mid;
			}
		}

		return end - 1;
	}

	public static void main(String[] args)
	{
		System.out.println(new SquareRoot().mySqrt(10));
	}
}
