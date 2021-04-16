package interview.leetcode.prob;

/**
 * Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both cases.

 

Example 1:

Input: s = "hello"
Output: "holle"
Example 2:

Input: s = "leetcode"
Output: "leotcede"
 

Constraints:

1 <= s.length <= 3 * 105
s consist of printable ASCII characters.
Accepted
273,094
Submissions
603,672
 * @author jojo
 * Apr 13, 2021  10:59:34 PM
 */
public class ReverseVowelOfAString {
	public String reverseVowels(String s) {
        int b = 0, e = s.length() - 1;
        
        char[] cArr = s.toCharArray();
        while(b < e){
            while(b < e && !isVowel(cArr[b])){
                b++;
            }
            
            while(b < e && !isVowel(cArr[e])){
                e--;
            }
            
            char t = cArr[b];
            cArr[b] = cArr[e];
            cArr[e] = t;
            b++;
            e--;
        }
        
        return String.valueOf(cArr);
    }
    
    private boolean isVowel(char ch){
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
