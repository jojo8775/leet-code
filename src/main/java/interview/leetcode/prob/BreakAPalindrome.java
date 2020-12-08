package interview.leetcode.prob;

/**
 * Given a palindromic string palindrome, replace exactly one character by any lowercase English letter so that the string becomes the lexicographically smallest possible string that isn't a palindrome.

After doing so, return the final string.  If there is no way to do so, return the empty string.

 

Example 1:

Input: palindrome = "abccba"
Output: "aaccba"
Example 2:

Input: palindrome = "a"
Output: ""
 

Constraints:

1 <= palindrome.length <= 1000
palindrome consists of only lowercase English letters.

 * @author jojo
 * Nov 17, 2020  6:27:41 PM
 */
public class BreakAPalindrome {
	public String breakPalindrome(String palindrome) {
        int len = palindrome.length();
        
        if(len < 2){
            return "";
        }
        
        for(int i=0; i<len/2; i++){
            if(palindrome.charAt(i) != 'a'){
                return palindrome.substring(0, i) + "a" + palindrome.substring(i + 1);
            }
        }
        
        return palindrome.substring(0, len - 1) + "b";
    }
}
