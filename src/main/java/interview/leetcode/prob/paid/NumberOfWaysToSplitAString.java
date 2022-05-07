package interview.leetcode.prob.paid;

/**
 * Given a binary string s, you can split s into 3 non-empty strings s1, s2, and s3 where s1 + s2 + s3 = s.

Return the number of ways s can be split such that the number of ones is the same in s1, s2, and s3. Since the answer may be too large, return it modulo 109 + 7.

 

Example 1:

Input: s = "10101"
Output: 4
Explanation: There are four ways to split s in 3 parts where each part contain the same number of letters '1'.
"1|010|1"
"1|01|01"
"10|10|1"
"10|1|01"
Example 2:

Input: s = "1001"
Output: 0
Example 3:

Input: s = "0000"
Output: 3
Explanation: There are three ways to split s in 3 parts.
"0|0|00"
"0|00|0"
"00|0|0"
 

Constraints:

3 <= s.length <= 105
s[i] is either '0' or '1'.
Accepted
19,385
Submissions
60,460
 * @author jojo
 * May 4, 2022 10:28:40 PM
 */
public class NumberOfWaysToSplitAString {
	public int numWays(String s) {
        int mod = (int) (1e9 + 7);
        long n = s.length();
        
        // too short not possible to split into 3 pieces
        if(n < 3){
            return 0;
        }
        
        long oneCount = 0;
        for(int i=0; i<n; i++){
            if(s.charAt(i) == '1'){
                oneCount++;
            }
        }
        
        // not possible to split equally among three 
        if(oneCount%3 != 0){
            return 0;
        }
        
        // no zero... this is the max which can be formed using 0s.... visualize it using 0000
        if(oneCount == 0){
            return (int) ((((n-1) * (n-2)) / 2) % mod);
        }
        
        long avgOnes = oneCount / 3;
        long firstZeroCount = 0, secondZeroCount = 0, prefixCount = 0;
        
        for(int i=0; i<n; i++){
            char ch = s.charAt(i);
            
            if(ch == '1'){
                prefixCount++;
            }
            else{
                // counting number of 0s between s1 and s2
                if(prefixCount == avgOnes){
                    firstZeroCount++;
                }
                // counting number of 0s between s2 and s3
                else if(prefixCount == 2 * avgOnes){
                    secondZeroCount++;
                }
            }
        }
        
        return (int) ((++firstZeroCount * ++ secondZeroCount) % mod);
    }
}
