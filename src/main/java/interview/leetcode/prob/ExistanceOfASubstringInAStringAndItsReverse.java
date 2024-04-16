package interview.leetcode.prob;

/**
 * Given a string s, find any substring of length 2 which is also present in the reverse of s.

Return true if such a substring exists, and false otherwise.

 

Example 1:

Input: s = "leetcode"

Output: true

Explanation: Substring "ee" is of length 2 which is also present in reverse(s) == "edocteel".

Example 2:

Input: s = "abcba"

Output: true

Explanation: All of the substrings of length 2 "ab", "bc", "cb", "ba" are also present in reverse(s) == "abcba".

Example 3:

Input: s = "abcd"

Output: false

Explanation: There is no substring of length 2 in s, which is also present in the reverse of s.

 

Constraints:

1 <= s.length <= 100
s consists only of lowercase English letters.
Accepted
26,517
Submissions
41,184 
 * 
 * 
 * Mar 17, 2024 - 10:18:43 AM
 * Jojo 
 */
public class ExistanceOfASubstringInAStringAndItsReverse {
	// same as find palindrome in a string.
    public boolean isSubstringPresent(String s) {
        for(int i=0; i<s.length(); i++){
            if(palindromLength(s, i, i) > 1 || palindromLength(s, i-1, i) >= 1){
                return true;
            }
        }
        
        return false;
    }
    
    private int palindromLength(String s, int i, int j){
        int count = 0;
        
        while(i >= 0 && j < s.length()){
            if(s.charAt(i) != s.charAt(j)){
                break;
            }
            
            count++;
            i--;
            j++;
        }
        
        return count;
    }
}
