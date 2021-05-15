package interview.leetcode.prob;

/**
 * 
Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.

Formally, a parentheses string is valid if and only if:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.

 

Example 1:

Input: "())"
Output: 1
Example 2:

Input: "((("
Output: 3
Example 3:

Input: "()"
Output: 0
Example 4:

Input: "()))(("
Output: 4
 

Note:

S.length <= 1000
S only consists of '(' and ')' characters.
 
Accepted
102,040
Submissions
135,982

 * @author jojo
 * May 9, 2021  12:16:01 AM
 */
public class MinimumAddToMakeParenthesisValid {
    public int minAddToMakeValid(String S) {
        
        int count = 0, openBracketCount = 0;
        for(char ch : S.toCharArray()){
            if(ch == '('){
                openBracketCount++;
            }
            else if(openBracketCount > 0){
                openBracketCount--;
            }
            else{
                count++;
            }
        }
        
        return count + openBracketCount;
    }
}
