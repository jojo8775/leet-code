
package interview.leetcode.string;

public class MinimumWindow
{
	public static void main(String[] args)
	{
		MinimumWindow minimumWindow = new MinimumWindow();

		System.out.println(minimumWindow.findMinimumWindow("ADOBECODEBANC", "ABC"));
//		System.out.println(minimumWindow.findMinimumWindow_Enhanced("ADOBECODEBANC", "ABC"));
		System.out.println(minimumWindow.findMinimumWindow_Enhanced("A", "B"));
	}

	/**
	 * Given a string S and a string T, find the minimum window in S which will
	 * contain all the characters in T in complexity O(n).
	 * 
	 * For example, S = "ADOBECODEBANC" T = "ABC" Minimum window is "BANC".
	 */
	public String findMinimumWindow(String s, String t)
	{
		if (s.isEmpty() || t.isEmpty() || s.length() < t.length())
		{
			return "";
		}

		int[] indexArr = new int[128];

		// indexing t string
		for (char c : t.toCharArray())
		{
			indexArr[c]++;
		}

		int beg = 0, end = 0, count = t.length();
		String result = "";
		boolean moveStart = false;

		while (end < s.length())
		{
			if (!moveStart)
			{
				if (indexArr[s.charAt(end)] > 0)
				{
					count--;
				}

				indexArr[s.charAt(end)]--;
			}

			if (count == 0)
			{
				if (result.isEmpty() || result.length() > (end - beg))
				{
					result = s.substring(beg, end + 1);
				}

				// found the optimum result
				if (result.length() == t.length())
				{
					return result;
				}
			}

			if (indexArr[s.charAt(beg)] < 0 && count == 0)
			{
				indexArr[s.charAt(beg)]++;
				if(indexArr[s.charAt(beg)] > 0){
					count ++;
				}
				moveStart = true;
				beg++;
			}
			else
			{
				moveStart = false;
				end++;
			}
		}

		return result;
	}
	
	public String findMinimumWindow_Enhanced(String s, String t){
		int[] indexArr = new int[128];
		
		//indexing the tokens
		for(char c : t.toCharArray()){
			indexArr[c] ++;
		}
		
		int end = 0, beg = 0, minLength = Integer.MAX_VALUE, tokenCount = t.length();
		String result = "";
		
		while(end < s.length()){
			//move end
			if(indexArr[s.charAt(end)] > 0){
				tokenCount --;
			}
				
			indexArr[s.charAt(end)]--;
			
			//move start
			while(tokenCount == 0){
				indexArr[s.charAt(beg)]++;
				if(indexArr[s.charAt(beg)] > 0){
					tokenCount ++;
					if(minLength > (end - beg)){
						result = s.substring(beg,end+1);
						minLength = end - beg;
					}
				}
				
				if(++beg > end){
					break;
				}
			}
			
			end++;
		}
		
		return result;
	}
}
