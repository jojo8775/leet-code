package interview.leetcode.prob;

/**
 * Given a string s, return the number of homogenous substrings of s. Since the answer may be too large, return it modulo 109 + 7.

A string is homogenous if all the characters of the string are the same.

A substring is a contiguous sequence of characters within a string.

 

Example 1:

Input: s = "abbcccaa"
Output: 13
Explanation: The homogenous substrings are listed as below:
"a"   appears 3 times.
"aa"  appears 1 time.
"b"   appears 2 times.
"bb"  appears 1 time.
"c"   appears 3 times.
"cc"  appears 2 times.
"ccc" appears 1 time.
3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.
Example 2:

Input: s = "xy"
Output: 2
Explanation: The homogenous substrings are "x" and "y".
Example 3:

Input: s = "zzzzz"
Output: 15
 

Constraints:

1 <= s.length <= 105
s consists of lowercase letters.
Accepted
47,145
Submissions
86,484
 * @author jojo
 * Nov. 8, 2023 10:10:05 p.m.
 */
public class CountNumberOfHomogenousString {
	public int countHomogenous_1(String s) {
        long mod = (long) (1e9 + 7);
        
        long count = 0, streakCount = 0;
        
        for(int i=0; i<s.length(); i++){
            if(i==0 || s.charAt(i) == s.charAt(i-1)){
                streakCount++;
            }
            else{
                streakCount = 1;
            }
            
            count += streakCount;
            count %= mod;
        }
        
        return (int)(count);
    }
        
    public int countHomogenous(String s) {
        long mod = (long) (1e9 + 7);
        
        long count = 0;
        for(int i=0, j=0; j < s.length(); j++){
            if(s.charAt(i) != s.charAt(j)){
                i = j;
            }
            
            count += (1 + (j-i));
            count %= mod;
        }
        
        return (int)(count);
    }
}
