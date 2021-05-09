package interview.leetcode.prob;

/**
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.

 

Example 1:

Input: s = "aba"
Output: true
Example 2:

Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.
Example 3:

Input: s = "abc"
Output: false
 

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
Accepted
263,263
Submissions
707,585
 * @author jojo
 * May 6, 2021  12:35:01 AM
 */
public class ValidPalindromII {
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                if(isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1)){
                    return true;
                }
                
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    private boolean isPalindrome(String s, int left, int right){
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
}
