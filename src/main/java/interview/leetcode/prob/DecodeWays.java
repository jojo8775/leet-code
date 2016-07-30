package interview.leetcode.prob;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
 * @author jojo
 *
 */
public class DecodeWays {
	public int numDecodings(String s) {
        if(s == null || s.isEmpty() || s.charAt(0) == '0'){
            return 0;
        }
        
        int[] dp = new int[s.length()];
        dp[0] = 1;
        
        for(int i=1; i<dp.length; i++){
            dp[i] = dp[i-1];
            int val = Integer.valueOf(s.substring(i-1, i+1));
            if(val > 0 && val <= 26){
                if(s.charAt(i) == '0'){
                    if(i-1 != 0){
                        dp[i] = dp[i-2];
                    }
                }
                else if(val != (int)(s.charAt(i) - '0')){
                    if(i-1 == 0){
                        dp[i] += 1;
                    }
                    else{
                        dp[i] += dp[i-2];
                    }
                }
            }
            else if(s.charAt(i) == '0'){
                return 0;
            }
        }
        
        return dp[dp.length - 1];
    }
	
	public static void main(String[] args){
		System.out.println(new DecodeWays().numDecodings("1012"));
	}
}
