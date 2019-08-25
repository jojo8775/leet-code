package interview.leetcode.prob;

/**
 * 
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Note:

The input string length won't exceed 1000.
 * @author jojo
 * Aug 25, 2019 4:03:57 AM
 */
public class PalindromicSubstring {
    public int countSubstrings(String s) {
        int count = 0;
        for(int i=0; i<s.length(); i++){
            count += findCount(s, i, i); // odd length
            count += findCount(s, i, i + 1); // even length
        }
        
        return count;
    }
    
    private int findCount(String str, int left, int right){
        int count = 0;
        
        while(left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)){
            count++;
            left--;
            right++;
        }
        
        return count;
    }
}
