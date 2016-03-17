package interview.leetcode.string;

public class SmallestPalindrome
{
	public static void main(String[] args)
	{
		// String s = new SmallestPalindrome().shortestPalindrome("aaceaaa");
		// String s = new
		// SmallestPalindrome().shortestPalindrome("aaaaaaaaaaaB");
		// System.out.println(s);
		// new SmallestPalindrome().p("aaceaaa");

		// new SmallestPalindrome().blaa("AMADAM");
//		System.out.println(new SmallestPalindrome().ehanced("abbacd"));
		System.out.println(new SmallestPalindrome().ehanced("aacecaaa"));
	}

	public String shortestPalindrome(String s)
	{
		if (s.length() < 2)
		{
			return s;
		}

		// finding the longest pallindrom in the existing string
		int start = 0, end = 0, max = 0, refEnd = s.length() - 1;
		do
		{
			start = 0;
			end = 0;
			max = 0;
			for (int i = 0; i <= refEnd; i++)
			{
				if (isPalindrom(s, i - max - 1, i))
				{
					start = i - max - 1;
					end = i;
					max += 2;
				}
				else if (isPalindrom(s, i - max, i))
				{
					start = i - max;
					end = i;
					max += 1;
				}
			}
			refEnd = start;
		} while (start != 0);

		if (start == 0 && end == s.length() - 1)
		{
			return s;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i > end; i--)
		{
			sb.append(s.charAt(i));
		}

		return sb.append(s).toString();
	}

	private boolean isPalindrom(String s, int beg, int end)
	{
		if (beg < 0)
		{
			return false;
		}

		while (beg < end)
		{
			if (s.charAt(beg++) != s.charAt(end--))
			{
				return false;
			}
		}

		return true;
	}

	private String p(String s)
	{
		int start = 0, end = 0, max = 0, refEnd = s.length() - 1;
		start = 0;
		end = 0;
		max = 0;
		for (int i = 0; i <= refEnd; i++)
		{
			if (isPalindrom(s, i - max - 1, i))
			{
				start = i - max - 1;
				end = i;
				max += 2;
			}
			else if (isPalindrom(s, i - max, i))
			{
				start = i - max;
				end = i;
				max += 1;
			}
		}

		System.out.println(s.substring(start, end + 1));

		return s.substring(start, end + 1);
	}

	private String blaa(String s)
	{
		if (s.length() <= 1)
			return s;
		char[] c_str = s.toCharArray();
		// the next array stores the failure function of each position.
		int[] next = new int[c_str.length];
		next[0] = -1;
		int i = -1, j = 0;
		while (j < c_str.length - 1)
		{
			if (i == -1 || c_str[i] == c_str[j])
			{
				i++;
				j++;
				next[j] = i;
				if (c_str[j] == c_str[i])
					next[j] = next[i];
			}
			else
				i = next[i];
		}

		// ----------------------- debug
		System.out.println("pritnting next");
		for (int x : next)
		{
			System.out.print(x + ", ");
		}

		// ----------------------- debug

		// match the string with its reverse.
		i = c_str.length - 1;
		j = 0;
		while (i >= 0 && j < c_str.length)
		{
			if (j == -1 || c_str[i] == c_str[j])
			{
				i--;
				j++;
			}
			else
			{
				j = next[j];
			}
		}
		StringBuilder sb = new StringBuilder();
		for (i = c_str.length - 1; i >= j; i--)
			sb.append(c_str[i]);
		for (char c : c_str)
			sb.append(c);
		return sb.toString();
	}

	public String blaa1(String s)
	{
		if (s.length() < 2)
		{
			return s;
		}

		int[] arrIndex = new int[256];

		int successIndex = 0;

		for (int i = 0; i < s.length(); i++)
		{
			if (arrIndex[s.charAt(i)] == 0)
			{
				arrIndex[s.charAt(i)]++;
			}
			else
			{
				if (isPalindrom_1(s, i, successIndex))
				{
					successIndex = i;
				}
			}
		}

		if (successIndex == s.length() - 1)
		{
			return s;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i > successIndex; i--)

		{
			sb.append(s.charAt(i));
		}

		return sb.append(s).toString();
	}

	private boolean isPalindrom_1(String s, int index, int successIndex)
	{
		int i = 0, j = index;
		for (; i < j; i++, j--)
		{
			if (j == successIndex)
			{
				return true;
			}

			if (s.charAt(i) != s.charAt(j))
			{
				return false;
			}
		}

		return true;
	}

	private String ehanced(String s)
	{
		// if string is empty
		if (s.length() <= 1)
			return s;

		char[] charArr = s.toCharArray();
		// the next array stores the failure function of each position.
		int[] faliurePoint = new int[charArr.length];
		faliurePoint[0] = -1;
		int i = -1, j = 0;
		while (j < charArr.length - 1)
		{
			if (i == -1 || charArr[i] == charArr[j])
			{
				i++;
				j++;
				faliurePoint[j] = i;
				if (charArr[j] == charArr[i])
					faliurePoint[j] = faliurePoint[i];
			}
			else
				i = faliurePoint[i];
		}

		// ----------------------- debug
		System.out.println("pritnting next");
		for (int x : faliurePoint)
		{
			System.out.print(x + ", ");
		}

		// ----------------------- debug

		// match the string with its reverse.
		i = charArr.length - 1;
		j = 0;
		while (i >= 0 && j < charArr.length)
		{
			if (j == -1)
			{
				System.out.println("index  -1");
			}
			else
			{
				System.out.println("comparing " + charArr[i] + " , " + charArr[j]);
			}
			if (j == -1 || charArr[i] == charArr[j])
			{
				i--;
				j++;
			}
			else
			{
				j = faliurePoint[j];
			}
		}
		StringBuilder sb = new StringBuilder();
		for (i = charArr.length - 1; i >= j; i--)
			sb.append(charArr[i]);
		for (char c : charArr)
			sb.append(c);
		return sb.toString();
	}

	public String simplified(String s)
	{
		// if string is empty
		if (s.length() <= 1)
			return s;

		int[] indexArr = new int[s.length()];

		char ch = s.charAt(0);

		for (int i = 0; i < s.length(); i++)
		{
			if (ch == s.charAt(i))
			{
				indexArr[i] = -1;
			}
		}

		int beg = 0;
		int end = s.length() - 1;

		while (end >= 0 && beg < s.length())
		{
			if (beg == -1 || s.charAt(beg) == s.charAt(end))
			{
				beg++;
				end--;
			}
			else
			{
				beg = indexArr[beg];
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= beg; i--)
		{
			sb.append(s.charAt(i));
		}
		
		sb.append(s);
		return sb.toString();
	}

}
