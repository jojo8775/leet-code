package interview.leetcode.tushar.dynamicprograming;

public class FibbonacciStairs
{
	public static void main(String[] args)
	{
		System.out.println(findNumberOfWays(6));
		System.out.println(findNumberOfWays_M(6));
	}

	private static int findNumberOfWays(int step)
	{

		int[] numberOfWays = new int[step + 1];
		numberOfWays[0] = 0;
		numberOfWays[1] = 1;

		for (int i = 2; i <= step; i++)
		{
			numberOfWays[i] = numberOfWays[i - 1] + numberOfWays[i - 2];
		}

		return numberOfWays[step];
	}

	// without array
	private static int findNumberOfWays_M(int step)
	{
		int stepMinusOne = 1;
		int stepMinusTwo = 0;

		for (int i = 2; i <= step; i++)
		{
			int temp = stepMinusOne + stepMinusTwo;
			stepMinusTwo = stepMinusOne;
			stepMinusOne = temp;
		}

		return stepMinusOne;
	}
}
