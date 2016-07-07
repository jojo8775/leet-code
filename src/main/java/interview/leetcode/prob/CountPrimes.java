package interview.leetcode.prob;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 * 
 * @author jojo
 *
 */
public class CountPrimes
{
	public int countPrimes(int n)
	{
		if (n < 3)
		{
			return 0;
		}

		// https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
		int[] arr = new int[n];

		for (int i = 2; i < n; i++)
		{
			if (arr[i] != -1)
			{
				for (int j = i + i; j < n; j = j + i)
				{
					arr[j] = -1;
				}
			}
		}

		int count = 0;
		for (int i = 2; i < n; i++)
		{
			if (arr[i] == 0)
			{
				count++;
			}
		}

		System.out.println(count);
		return count;
	}

	public static void main(String[] args)
	{
		new CountPrimes().countPrimes(7);
	}
}
