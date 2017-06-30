package interview.leetcode.practice.round3.recursion;

public class Microsoft {

    public int findLongestSubsequence(String s1, String s2){
       int[][] dp = new int[s1.length() + 1][s2.length() + 1];
       int max = 0;
       
       for(int i=1; i<=s1.length(); i++){
           for(int j=1; j<s2.length(); j++){
               if(s1.charAt(i-1) == s2.charAt(j-1)){
                   dp[i][j] = 1 + dp[i-1][j-1];
               }
               else{
                   dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
               }
               
               max = Math.max(max, dp[i][j]);
           }
       }
       
       return max;
    }
    
    /**
     * find largest common substring of the two given string
     */
    public String findLongestSubstring(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        int max = 0;
        String result = "";

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }

                if (max < dp[i][j]) {
                    max = dp[i][j];
                    result = s1.substring(i - dp[i][j], i);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Microsoft().findLongestSubstring("aaabb", "aaabb"));
            
        System.out.println(new Microsoft().findLongestSubsequence("dnhvisdfiuvneirdnuvie", "dnhvisdfineirdnuvie"));
    }
}
