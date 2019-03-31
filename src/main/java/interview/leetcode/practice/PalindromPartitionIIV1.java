package interview.leetcode.practice;

import java.util.HashMap;
import java.util.Map;

public class PalindromPartitionIIV1 {
	private Map<String, Boolean> cache = new HashMap<>();
    public int minCut(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        
        if(isPalindrome(s, 0, len - 1)){
            return 0;
        }
        
        System.out.println("this should have returned 0 by now");
        
        for(int l=1; l<len; l++){
            
            for(int i=0; i < len - l; i++){
                dp[i][i+l] = Integer.MAX_VALUE;
                if(isPalindrome(s, i, i+l)){
                    dp[i][i+l] = 0;
                }
                else {
                    for(int j=i+1; j<=i+l; j++){
                        dp[i][i+l] = Math.min(dp[i][i+l], 1 + dp[i][j-1] + dp[j][i+l]);
                    }
                }
            }
        }
        
        return dp[0][len - 1];
    }
    
    private boolean isPalindrome(String s, int beg, int end){
        String key = s.substring(beg, end + 1);
        if(cache.containsKey(key)){
            // System.out.println("cache hit : " + key);
            return cache.get(key);
        }
        
        while(beg < end){
            if(s.charAt(beg++) != s.charAt(end--)){
                cache.put(key, false);
                return false;
            }
        }
        
        cache.put(key, true);
        return true;
    }
    
    public int findMin(String s) {
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
    
    public static void main(String[] args) {
//    	System.out.println(new PalindromePartitionII().minCut("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    	System.out.println(new PalindromPartitionIIV1().minCut("aaaaaa"));
    }
}
