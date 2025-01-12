package interview.leetcode.prob;

import java.util.Stack;

/**
 * A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:

It is ().
It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
It can be written as (A), where A is a valid parentheses string.
You are given a parentheses string s and a string locked, both of length n. locked is a binary string consisting only of '0's and '1's. For each index i of locked,

If locked[i] is '1', you cannot change s[i].
But if locked[i] is '0', you can change s[i] to either '(' or ')'.
Return true if you can make s a valid parentheses string. Otherwise, return false.

 

Example 1:


Input: s = "))()))", locked = "010100"
Output: true
Explanation: locked[1] == '1' and locked[3] == '1', so we cannot change s[1] or s[3].
We change s[0] and s[4] to '(' while leaving s[2] and s[5] unchanged to make s valid.
Example 2:

Input: s = "()()", locked = "0000"
Output: true
Explanation: We do not need to make any changes because s is already valid.
Example 3:

Input: s = ")", locked = "0"
Output: false
Explanation: locked permits us to change s[0]. 
Changing s[0] to either '(' or ')' will not make s valid.
 

Constraints:

n == s.length == locked.length
1 <= n <= 105
s[i] is either '(' or ')'.
locked[i] is either '0' or '1'.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
50.5K
Submissions
137.3K
Acceptance Rate
36.8%
 * @author jojo
 * Jan. 11, 2025 10:57:19 p.m.
 */
public class CheckIfAParenthesesStringCanBeValid {
	public boolean canBeValid(String s, String locked) {
        int balance = 0;
        
        // left to right;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '(' || locked.charAt(i) == '0'){
                balance++;
            }
            else{
                balance--;

                if(balance < 0){
                    return false;
                }
            }
        }

        balance = 0;
        // left to right;
        for(int i=s.length() - 1; i>=0; i--){
            if(s.charAt(i) == ')' || locked.charAt(i) == '0'){
                balance++;
            }
            else{
                balance--;

                if(balance < 0){
                    return false;
                }
            }
        }

        return balance % 2 == 0;
    }


    public boolean canBeValid_1(String s, String locked) {
        Stack<Integer> obc = new Stack<>();
        Stack<Integer> lStack = new Stack<>();
        
        for(int i=0; i<s.length(); i++){
            if(locked.charAt(i) == '0'){
                lStack.push(i);
            }
            else if(s.charAt(i) == '('){
                obc.push(i);
            }
            else if(s.charAt(i) == ')'){
                if(obc.isEmpty()){
                    if(lStack.isEmpty()){
                        return false;
                    }

                    lStack.pop();
                }
                else{
                    obc.pop();
                }
            }
        }

        while(!obc.isEmpty()){
            if(lStack.isEmpty() || obc.peek() > lStack.peek()){
                return false;
            }

            int top = lStack.pop();
            if(top == obc.peek()){
                if(lStack.isEmpty()){
                    return false;
                }

                lStack.pop();
            }

            obc.pop();
        }

        return lStack.size() % 2 == 0;
    }
}
