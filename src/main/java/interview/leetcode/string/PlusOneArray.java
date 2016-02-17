package interview.leetcode.string;

public class PlusOneArray
{
	public static void main(String[] args)
	{
		PlusOneArray prob = new PlusOneArray();

		int[] arr = { 9, 8, 9, 9 };
		int[] result = prob.plusOne(arr);

		for (int i : result)
		{
			System.out.print(i + ", ");
		}
	}

	/**
	 * Given a non-negative number represented as an array of digits, plus one
	 * to the number.
	 * 
	 * The digits are stored such that the most significant digit is at the head
	 * of the list.
	 */
	public int[] plusOne(int[] digits)
	{
		int temp = 1;
		for (int i = digits.length - 1; i >= 0; i--)
		{
			temp += digits[i];
			digits[i] = temp % 10;
			temp /= 10;
		}

		if (temp == 0)
		{
			return digits;
		}

		int[] result = new int[digits.length + 1];
		result[0] = 1;
		for (int i = 1; i < result.length; i++)
		{
			result[i] = digits[i - 1];
		}

		return result;
	}

	public int[] plusOne_Enhanced(int[] digits)
	{
		if (digits == null || digits.length == 0)
		{
			return digits;
		}

		for (int i = digits.length - 1; i >= 0; i--)
		{
			digits[i]++;
			if (digits[i] != 10)
			{
				return digits;
			}

			digits[i] = 0;
		}

		int[] result = new int[digits.length + 1];
		result[0] = 1;
		for (int i = 1; i < result.length; i++)
		{
			result[i] = digits[i - 1];
		}

		return result;
	}
}
