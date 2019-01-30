package interview.leetcode.practice;

public class RegularExpressionMathing {
	public boolean isMatch(String s, String p) {
		boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
		
		for(int i=1; i<dp.length; i++) {
			if(p.charAt(i-1) == '*') {
				dp[i][0] = dp[i-2][0]; 
			}
		}
		
		for(int i=1; i<dp.length; i++) {
			char c1 = p.charAt(i);
			for(int j=1; j<dp[i].length; j++) {
				if(c1 == '*') {
					// not contributing any occurrence.
					dp[i][j] = dp[i-2][j];
					
					if(p.charAt(i-2) == s.charAt(j-1) || p.charAt(i - 2) == '.') {
						dp[i][j] = dp[i][j-1] || dp[i-1][j] || dp[i][j];
					}
				}
				else if(c1 == s.charAt(j-1) || c1 == '.')
				{
					dp[i][j] = dp[i-1][j-1];
				}
			}
		}
		
		return dp[p.length()][s.length()];
	}
	
	public static void main(String[] args) {
		System.out.println(new RegularExpressionMatch().isMatch("hilhsejhdfvbuzwutysedsjhd", ".*"));
		System.out.println(new RegularExpressionMatch().isMatch("hilhsejhdfvbuzwutysedsjhd", "aa"));
		System.out.println(new RegularExpressionMatch().isMatch("hilhsejhdfvbuzwutysedsjhd", "sjh"));
		System.out.println(new RegularExpressionMatch().isMatch("mississippi", "mis*is*p*."));
		System.out.println(new RegularExpressionMatch().isMatch("mississippi", "mis*is*.p*."));
	}
}
