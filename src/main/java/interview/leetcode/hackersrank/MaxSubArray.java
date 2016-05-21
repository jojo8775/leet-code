package interview.leetcode.hackersrank;

import java.math.BigInteger;
import java.util.Scanner;

public class MaxSubArray
{
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		int numberOfTestCase = s.nextInt();
		for (int i = 0; i < numberOfTestCase; i++)
		{
			int arrLength = s.nextInt();
			int[] arr = new int[arrLength];

			for (int j = 0; j < arrLength; j++)
			{
				arr[j] = s.nextInt();
			}

			BigInteger nonContiguousSum = BigInteger.valueOf(arr[0]);
			for (int j = 1; j < arrLength; j++)
			{
				if (arr[j] > 0)
				{
					nonContiguousSum = nonContiguousSum.add(BigInteger.valueOf(arr[j]));
				}
				else if(nonContiguousSum.compareTo(BigInteger.valueOf(arr[j])) == -1){
					nonContiguousSum = BigInteger.valueOf(arr[j]);
				}
			}

			BigInteger currentMax = BigInteger.valueOf(arr[0]), maxValue = currentMax;
			for (int j = 1; j < arr.length; j++)
			{
				if (currentMax.compareTo(BigInteger.valueOf(0)) == -1)
				{
					currentMax = BigInteger.valueOf(arr[j]);
				} else
				{
					currentMax = currentMax.add(BigInteger.valueOf(arr[j]));
				}

				if (currentMax.compareTo(maxValue) == 1)
				{
					maxValue = currentMax;
				}
			}

			System.out.println(maxValue + " " + nonContiguousSum);
		}
	}
}
