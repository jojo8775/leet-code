package interview.leetcode.prob;

/**
 * Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:

Append the character '0' zero times.
Append the character '1' one times.
This can be performed any number of times.

A good string is a string constructed by the above process having a length between low and high (inclusive).

Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.

 

Example 1:

Input: low = 3, high = 3, zero = 1, one = 1
Output: 8
Explanation: 
One possible valid good string is "011". 
It can be constructed as follows: "" -> "0" -> "01" -> "011". 
All binary strings from "000" to "111" are good strings in this example.
Example 2:

Input: low = 2, high = 3, zero = 1, one = 2
Output: 5
Explanation: The good strings are "00", "11", "000", "110", and "011".
 

Constraints:

1 <= low <= high <= 105
1 <= zero, one <= low
Accepted
68,017
Submissions
123,038
 * @author jojo
 * Nov. 3, 2023 8:12:28 a.m.
 */
public class CountWaysToBuildGoodString {
	public int countGoodStrings(int low, int high, int zero, int one) {
        // return bottomsUp(low, high, zero, one);
        return topDown(low, high, zero, one);
    }
    
    private int bottomsUp(int low, int high, int zero, int one){
        int mod = (int)(1e9 + 7);
        
        // states 
        int[] dp = new int[high + 1];
        
        // base case
        dp[0] = 1;
        
        for(int i=1; i<=high; i++){
            // Relation :
            if(i >= zero){
                dp[i] += dp[i-zero];
            }
            
            // Relation :
            if(i >= one){
                dp[i] += dp[i-one];
            }
            
            dp[i] %= mod;
        }
        
        int count = 0;
        
        for(int i=low; i<= high; i++){
            count += dp[i];
            count %= mod;
        }
        
        return count;
    }
    
    private int topDown(int low, int high, int zero, int one){
        int result = 0;
        int mod = (int)(1e9 + 7);
        
        // states 
        Integer[] memo = new Integer[high + 1];
        
        for(int i = low; i<= high; i++){
            result += dp(i, zero, one, mod, memo);
            result %= mod;
        }
        
        return result;
    }
    
    private int dp(int end, int zero, int one, int mod, Integer[] memo){
        // base case
        // where end ran to zero basd on the 0s and 1s count 
        if(end == 0){
            return 1;
        }
        
        if(end < 0){
            return 0;
        }
        
        if(memo[end] != null){
            return memo[end];
        }
        
        int count = 0;
        // relation:
        if(end >= zero){
            count += dp(end - zero, zero, one, mod, memo);
        }
        
        // relation:
        if(end >= one){
            count += dp(end - one, zero, one, mod, memo);
        }
        
        count %= mod;
        memo[end] = count;
        
        return memo[end];
    }
}
