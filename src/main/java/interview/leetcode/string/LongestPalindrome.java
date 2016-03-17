package interview.leetcode.string;

public class LongestPalindrome
{
	public static void main(String[] args){
//		System.out.println(new LongestPalindrome().longestPalindrome("amadam"));
//		System.out.println(new LongestPalindrome().longestPalindrome_Enhanced("abcmadam"));
		System.out.println(new LongestPalindrome().longestPalindrome_Enhanced("aaceaaa"));
	}
	
	/**
	 * Given a string S, find the longest palindromic substring in S. You may
	 * assume that the maximum length of S is 1000, and there exists one unique
	 * longest palindromic substring.
	 * 
	 * Subscribe to see which companies asked this question
	 */
	public String longestPalindrome(String s)
	{
		if (s.length() < 2)
		{
			return s;
		}

		String result = "";

		for (int i = 0; i < s.length() - 1; i++)
		{
			result = checkForLongestPalindrom(i, i, s, result);
			result = checkForLongestPalindrom(i, i + 1, s, result);
		}

		return result;
	}

	private String checkForLongestPalindrom(int left, int right, String s, String result)
	{
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right))
		{
			left--;
			right++;
		}

		if (result.length() < (right - left))
		{
			result = s.substring(left + 1, right);
		}

		return result;
	}
	
	//TODO: need to understand this --- its a crazy logic 
	public String longestPalindrome_Enhanced(String s) {
	    char[] ca = s.toCharArray();
	    int rs = 0, re = 0;
	    int max = 0;
	    for(int i = 0; i < ca.length; i++) {
	    	System.out.println("s = " + rs + " e = " + re + " max = " + max);
	        if(isPalindrome(ca, i - max - 1, i)) {
	            rs = i - max - 1; re = i;
	            System.out.println("First");
	            max += 2;
	        } else if(isPalindrome(ca, i - max, i)) {
	            rs = i - max; re = i;
	            System.out.println("Second");
	            max += 1;
	        }
	    }
	    return s.substring(rs, re + 1);
	}

	private boolean isPalindrome(char[] ca, int s, int e) {
	    if(s < 0) return false;

	    while(s < e) {
	        if(ca[s++] != ca[e--]) return false;
	    }
	    return true;
	}
}
