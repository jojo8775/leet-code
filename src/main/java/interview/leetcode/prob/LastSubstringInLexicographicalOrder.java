package interview.leetcode.prob;

/**
 * Given a string s, return the last substring of s in lexicographical order.

 

Example 1:

Input: s = "abab"
Output: "bab"
Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
Example 2:

Input: s = "leetcode"
Output: "tcode"
 

Constraints:

1 <= s.length <= 4 * 105
s contains only lowercase English letters.
Accepted
22,071
Submissions
60,938
 * @author jojo
 * Jan 31, 2021  1:45:17 AM
 */
public class LastSubstringInLexicographicalOrder {
	public String lastSubstring(String s) {
        int i=0, j=1, offset = 0, len = s.length();
        
        while((i + offset < len) && (j + offset < len)){
            char c = s.charAt(i + offset), d = s.charAt(j + offset); // checking substring 
            
            // validates if the compared subsrting is the smallest lexicographically. 
            if(c == d){
                offset ++;
            }
            else{
                if(c < d){
                    i = i + offset + 1; // if the current char is smaller then move the head of the first substring 
                }
                else{
                    j = j + offset + 1; // if the current char is greater then move the head of the second substring
                }
                
                if(i == j){
                    j++; // avoiding same index;
                }
                
                offset = 0; // resetting since one of the substring's head index is updated. 
            }
        }
        
        return s.substring(i);
    }
	
	
	public static void main(String[] args) {
		String result = new LastSubstringInLexicographicalOrder().lastSubstring("cacacb");
		System.out.println(result);
	}
}
