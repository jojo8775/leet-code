package interview.leetcode.prob;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 * 
 * @author jojo
 *
 */
public class ClimbingStairs
{
	public int climbStairs(int n)
	{
		if (n < 2)
		{
			return n;
		}

		int prev = 1, cur = 2;

		for (int i = 2; i < n; i++)
		{
			int temp = cur + prev;
			prev = cur;
			cur = temp;
		}

		return cur;
	}
}
