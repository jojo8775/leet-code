package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

public class PalindromPartitioningII {
	public int minCut_2(String s) {
    	int len = s.length();
    	boolean[][] dp = new boolean[len][len]; // stores if a given substring is palindrom or not.
    	int[] minSplit = new int[len]; // store the min split for each new char addition.
    	
    	for(int i=0; i<len; i++) {
    		int min = i; // max split for a new char addition can be the position count;
    		for(int j=0; j<=i; j++) {
    			// if i and j same and if the in-between chars are also palindrome  
    			if(s.charAt(j) == s.charAt(i) && (j+1 > i-1 || dp[j+1][i-1])) {
    				dp[j][i] = true;
    				min = j == 0 ? 0 : Math.min(min, minSplit[j-1] + 1);
    			}
    		}
    	}
    	
    	return minSplit[len - 1];
    }
	
	private Set<String> cache = new HashSet<String>();
	public int minCut(String s) {
		// using bottom up approach
		int[][] grid = new int[s.length()][s.length()];
		
		for (int k = 1; k < s.length(); k++) {
			for (int i = 0; i < s.length() - k; i++) {
				grid[i][i + k] = Integer.MAX_VALUE;
				for (int j = i + 1; j <= i + k; j++) {
					int val = 0;
					if (!isPalindrom(s, i, i + k)) {
						val = grid[i][j - 1] + grid[j][i + k] + 1;
					}

					grid[i][i + k] = Math.min(grid[i][i + k], val);
				}
			}
		}

		return grid[0][s.length() - 1];
	}

	private boolean isPalindrom(String str, int beg, int end) {
		int i = beg, j = end;
		
		if(cache.contains(str.substring(beg, end + 1))){
			return true;
		}
		while (i < j) {
			if (str.charAt(i++) != str.charAt(j--)) {
				return false;
			}
		}
		
		cache.add(str.substring(beg, end + 1));
		return true;
	}

	public static void main(String[] args) {
		// System.out.println(new PalindromPartitioningII().minCut("aabczza"));
		// System.out.println(new
		// PalindromPartitioningII().minCut("aabsdvvaaaasa"));
		System.out.println(new PalindromPartitioningII().minCut(
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}
}
