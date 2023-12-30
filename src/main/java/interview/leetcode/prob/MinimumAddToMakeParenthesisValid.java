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
        int cbcNeeded = 0, adjNeeded = 0, len = S.length();

        for(int i=0; i<len; i++){
            char ch = S.charAt(i);

            if(ch == '('){
                cbcNeeded++;
            }
            else{
                cbcNeeded--;

                if(cbcNeeded < 0){
                    cbcNeeded = 0;
                    adjNeeded +=1;
                }
            }
        }

        return cbcNeeded + adjNeeded;
    }
	
	 public int minAddToMakeValid_2pass(String S) {
	        int obc = 0, cbc = 0;

	        for(int i=0; i<S.length(); i++){
	            if(S.charAt(i) == '('){
	                obc++;
	            }
	            else if(obc > 0){
	                obc--;
	            }
	        }

	        for(int i=S.length() - 1; i>=0; i--){
	            if(S.charAt(i) == ')'){
	                cbc++;
	            }
	            else if(cbc > 0){
	                cbc--;
	            }
	        }

	        return obc + cbc;
	    }su
}
