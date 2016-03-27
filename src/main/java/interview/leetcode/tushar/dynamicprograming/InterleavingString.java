package interview.leetcode.tushar.dynamicprograming;

public class InterleavingString
{
	public static void main(String[] args){
		System.out.println(isValid("aab", "axy", "aaxaby"));
	}

	private static boolean isValid(String s1, String s2, String r)
	{

		boolean[][] arr = new boolean[s1.length() + 1][s2.length() + 1];

		arr[0][0] = true;

		// filling the 0th row
		for (int i = 0; i < s1.length(); i++)
		{
			if (s1.charAt(i) == r.charAt(i))
			{
				arr[0][i + 1] = true;
			}
		}

		// filling the 0th column
		for (int i = 0; i < s2.length(); i++)
		{
			if (s2.charAt(i) == r.charAt(i))
			{
				arr[i + 1][0] = true;
			}
		}

		for(int i=1; i<=s2.length(); i++){
			
			for(int j=1; j<=s1.length(); j++){
				if(r.charAt(i+j-1) == s2.charAt(i-1)){
					arr[i][j] = arr[i-1][j];
				}
			    if(r.charAt(i+j-1) == s1.charAt(j-1)){
					arr[i][j] = arr[i][j-1];
				}
			}
		}
		
		print(arr);
		
		return arr[s1.length()][s2.length()];
	}

	private static void print(boolean[][] arr)
	{
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[0].length; j++){
				System.out.print(arr[i][j] + ", ");
			}
			System.out.println();
		}
	}
}
