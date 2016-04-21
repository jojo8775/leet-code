package interview.leetcode.concepts;

/**
 * Runtime complexity = O(m + n) Space complexity = O(n) where m = text length
 * and n = pattern length.
 * 
 * @author jojo
 *
 */
public class KMPSearchAlgorithm
{
	public static void main(String[] args)
	{
//		System.out.println(doesPatternExist("abxabcabcaby", "abcaby"));
		
		indexPattern("acacabacacabacacac");
	}

	private static boolean doesPatternExist(String text, String pattern)
	{
		int[] patternArr = indexPattern(pattern);
		int textIndex = 0, patternIndex = textIndex, beg = 0, end = beg;
		boolean patternFound = false;

		while (textIndex < text.length() && patternIndex < pattern.length())
		{
			if (text.charAt(textIndex) == pattern.charAt(patternIndex))
			{
				end = textIndex;
				textIndex++;
				patternIndex++;
			}
			else if (patternIndex == 0)
			{
				textIndex++;
				beg = textIndex;
				end = textIndex;
			}
			else
			{
				patternIndex = patternArr[patternIndex - 1];
				beg = textIndex - patternIndex;
			}
		}

		if (patternIndex == patternArr.length)
		{
			patternFound = true;
			System.out.println("beg: " + beg);
			System.out.println("end: " + end);
		}

		return patternFound;
	}

	private static int[] indexPattern(String pattern)
	{
		int[] indexArr = new int[pattern.length()];

		int j = 0, i = 1;
		while (i < pattern.length())
		{
			if (pattern.charAt(i) == pattern.charAt(j))
			{
				indexArr[i++] = j + 1;
				j++;
			}
			else if (j == 0)
			{
				indexArr[i] = 0;
				i++;
			}
			else
			{
				j = indexArr[j - 1];
			}
		}

		return indexArr;
	}
}
