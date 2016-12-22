package interview.leetcode.prob;

/**
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
 * @author jojo
 *
 */
public class LongestPalindrom {
    public int longestPalindrome(String s) {
        int[] arr = new int[52];
        for(char ch : s.toCharArray()){
            if(ch >= 'A' && ch <= 'Z'){
                arr[26 + (ch - 'A')]++;
            }
            else{
                arr[ch - 'a']++;
            }
        }
        
        int count = 0;
        boolean oddFound = false;
        
        for(int val : arr){
            if(val%2==0){
                count += val;
            }
            else{
                oddFound = true;
                count += (val-1); 
            }
        }
        
        return oddFound ? count + 1 : count;
    }
}
