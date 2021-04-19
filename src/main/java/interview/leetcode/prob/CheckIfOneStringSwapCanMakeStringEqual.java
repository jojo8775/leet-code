package interview.leetcode.prob;

/**
 * You are given two strings s1 and s2 of equal length. A string swap is an operation where you choose two indices in a string (not necessarily different) and swap the characters at these indices.

Return true if it is possible to make both strings equal by performing at most one string swap on exactly one of the strings. Otherwise, return false.

 

Example 1:

Input: s1 = "bank", s2 = "kanb"
Output: true
Explanation: For example, swap the first character with the last character of s2 to make "bank".
Example 2:

Input: s1 = "attack", s2 = "defend"
Output: false
Explanation: It is impossible to make them equal with one string swap.
Example 3:

Input: s1 = "kelb", s2 = "kelb"
Output: true
Explanation: The two strings are already equal, so no string swap operation is required.
Example 4:

Input: s1 = "abcd", s2 = "dcba"
Output: false
 

Constraints:

1 <= s1.length, s2.length <= 100
s1.length == s2.length
s1 and s2 consist of only lowercase English letters.
Accepted
18,183
Submissions
30,409
 * @author jojo
 * Apr 18, 2021  2:51:59 PM
 */
public class CheckIfOneStringSwapCanMakeStringEqual {
    public boolean areAlmostEqual(String s1, String s2) {
        
        int[] arr = new int[26];
        
        for(char ch : s1.toCharArray()){
            arr[ch - 'a']++;
        }
        
        for(char ch : s2.toCharArray()){
            if(--arr[ch - 'a'] < 0){
                return false;
            }
        }
        
        int count = 0;
        for(int i=0; i<s1.length() && count < 3; i++){
            if(s1.charAt(i) != s2.charAt(i)){
                count++;
            }
        }
        
        return count < 3;
    }
}
