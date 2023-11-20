package interview.leetcode.prob;

/**
 * You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:

Type-1: Remove the character at the start of the string s and append it to the end of the string.
Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
Return the minimum number of type-2 operations you need to perform such that s becomes alternating.

The string is called alternating if no two adjacent characters are equal.

For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 

Example 1:

Input: s = "111000"
Output: 2
Explanation: Use the first operation two times to make s = "100011".
Then, use the second operation on the third and sixth elements to make s = "101010".
Example 2:

Input: s = "010"
Output: 0
Explanation: The string is already alternating.
Example 3:

Input: s = "1110"
Output: 1
Explanation: Use the second operation on the second element to make s = "1010".
 

Constraints:

1 <= s.length <= 105
s[i] is either '0' or '1'.
Accepted
22,981
Submissions
58,092
 * @author jojo
 * Nov 18, 2023 3:55:25 PM
 */
public class MinimumNumberOfFlipsToMakeBinaryTheStringAlternating {
	public int minFlips(String s) {
        String s1 = s + s; // this is done to simulate max times of operation 1
        
        int n = s.length(), len = s1.length();
        
        StringBuilder sb2 = new StringBuilder(), sb3 = new StringBuilder();
        
        for(int i=0; i<len; i++){
            if(i % 2 == 0){
                sb2.append("0");
                sb3.append("1");
            }
            else{
                sb2.append("1");
                sb3.append("0");
            }
        }
        
        // if s = 101 then
        // s1 = 101101  (s + s)
        // s2 = 101010
        // s3 = 010101
        // the goal is to find the number of missmatch from current string to end string 
        // take the min
        String s2 = sb2.toString(), s3 = sb3.toString();
        
        int flip1 = 0, flip2 = 0, result = Integer.MAX_VALUE;
        
        for(int i=0; i<len; i++){
            // count the mismatch from the given string and goal
            if(s2.charAt(i) != s1.charAt(i)){
                flip1++;
            }
            
            if(s3.charAt(i) != s1.charAt(i)){
                flip2++;
            }
            
            if(i >= n){
                // if the window size is more than n time to shrink
                if(s2.charAt(i-n) != s1.charAt(i-n)){
                    flip1--;
                }   
            
                if(s3.charAt(i-n) != s1.charAt(i-n)){
                    flip2--;
                }   
            }
            
            //System.out.println("f1:" + flip1 + "  f2:" + flip2);
            
            if(i >= n-1){
                result = Math.min(result, Math.min(flip1, flip2));
            }
        }
        
        return result;
    }
}
