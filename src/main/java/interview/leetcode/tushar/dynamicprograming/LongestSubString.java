package interview.leetcode.tushar.dynamicprograming;

public class LongestSubString
{
	public static void main(String[] args){
		findLongestSubstring("abcdaf", "zbcdf");
	}
	
	private static void findLongestSubstring(String str1, String str2)
	{
		int[][] grid = new int[str1.length()][str2.length()];

		int maxCountX = 0, maxCountY = 0;
		for (int i = 0; i < str1.length(); i++)
		{
			for (int j = 0; j < str2.length(); j++)
			{
				if (str1.charAt(i) == str2.charAt(j))
				{
					if (i == 0 || j == 0)
					{
						grid[i][j] = 1;
					}
					else
					{
						grid[i][j] += 1 + grid[i - 1][j - 1];
					}

					if (grid[maxCountY][maxCountX] < grid[i][j])
					{
						maxCountY = i;
						maxCountX = j;
					}
				}
			}
		}

		System.out.println(grid[maxCountY][maxCountX]);
		
		char[] charArr = new char[grid[maxCountY][maxCountX]];
		int charCount= charArr.length - 1;
		
		while(maxCountY >= 0 && maxCountY >=0 && grid[maxCountY][maxCountX] > 0){
			charArr[charCount--] = str1.charAt(maxCountY--);
			maxCountX--;
		}
		
		System.out.println(String.valueOf(charArr));
	}
}
