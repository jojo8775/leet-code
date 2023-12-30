package interview.leetcode.prob;

/**
 * Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.

 

Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 
Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 
Example 3:

Input: s = "abc"
Output: 1
 

Constraints:

3 <= s.length <= 5 x 10^4
s only consists of a, b or c characters.
Accepted
78,669
Submissions
120,328
 * @author jojo
 * Dec 29, 2023 4:59:05 PM
 */
public class NumberOfSubstringContainingAllThreeCharacter {
	public int numberOfSubstrings(String s) {
        int[] count = {0,0,0};
        
        int result = 0;
        
        for(int i=0,j=0; i<s.length(); i++){
            char ch = s.charAt(i);
            
            count[ch - 'a']++;
            
            while(count[0] > 0 && count[1] > 0 && count[2] > 0){
                count[s.charAt(j++) - 'a']--;
            }
            
            result += j;
        }
        
        return result;
    }
}
