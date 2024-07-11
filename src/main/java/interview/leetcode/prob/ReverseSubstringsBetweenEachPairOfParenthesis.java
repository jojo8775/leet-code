package interview.leetcode.prob;

/**
 * You are given a string s that consists of lower case English letters and brackets.

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets.

 

Example 1:

Input: s = "(abcd)"
Output: "dcba"
Example 2:

Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.
Example 3:

Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
 

Constraints:

1 <= s.length <= 2000
s only contains lower case English characters and parentheses.
It is guaranteed that all parentheses are balanced.
Accepted
84,113
Submissions
124,721
 * @author jojo
 * Jul. 10, 2024 7:42:15 p.m.
 */
public class ReverseSubstringsBetweenEachPairOfParenthesis {
	public String reverseParentheses(String s) {
        return findReverse(s, new int[]{0});
    }
    
    private String findReverse(String s, int[] idx){
        StringBuilder sb = new StringBuilder();
        
        while(idx[0] < s.length()){
            char ch = s.charAt(idx[0]++);
            
            if(ch == '('){
                sb.append(findReverse(s, idx));
            }
            else if(ch == ')'){
                return sb.reverse().toString();
            }
            else{
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }
}
