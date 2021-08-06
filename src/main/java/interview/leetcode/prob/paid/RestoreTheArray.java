package interview.leetcode.prob.paid;

/**
 * A program was supposed to print an array of integers. The program forgot to print whitespaces and the array is printed as a string of digits s and all we know is that all integers in the array were in the range [1, k] and there are no leading zeros in the array.

Given the string s and the integer k, return the number of the possible arrays that can be printed as s using the mentioned program. Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: s = "1000", k = 10000
Output: 1
Explanation: The only possible array is [1000]
Example 2:

Input: s = "1000", k = 10
Output: 0
Explanation: There cannot be an array that was printed this way and has all integer >= 1 and <= 10.
Example 3:

Input: s = "1317", k = 2000
Output: 8
Explanation: Possible arrays are [1317],[131,7],[13,17],[1,317],[13,1,7],[1,31,7],[1,3,17],[1,3,1,7]
Example 4:

Input: s = "2020", k = 30
Output: 1
Explanation: The only possible array is [20,20]. [2020] is invalid because 2020 > 30. [2,020] is ivalid because 020 contains leading zeros.
Example 5:

Input: s = "1234567890", k = 90
Output: 34
 

Constraints:

1 <= s.length <= 105
s consists of only digits and does not contain leading zeros.
1 <= k <= 109
Accepted
10,081
Submissions
27,214
 * @author jojo
 * Aug 4, 2021  11:26:27 PM
 */
public class RestoreTheArray {
    public int numberOfArrays(String s, int k) {
        long mod = (long) (1e9 + 7);
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        
        
        for(int i=s.length()-1; i>=0 ; i--){
            if(s.charAt(i) == '0'){
                continue;
            }
            
            long n = 0;
            for(int j=0; i + j < s.length(); j++){
                n *= 10;
                n += (long)(s.charAt(i + j) - '0');
                
                if(n > k){
                    break;
                }
                
                dp[i] = (int)((dp[i] + dp[i + j + 1]) % mod);
            }
        }
        
        return dp[0];
	}
}
