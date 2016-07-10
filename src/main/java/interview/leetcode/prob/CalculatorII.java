package interview.leetcode.prob;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
 * @author jojo
 *
 */
public class CalculatorII {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
		int num = 0, idx = 0;
		char sign = '+';
		
		while(idx < s.length()){
			char c = s.charAt(idx);
			
			//checking if it is a number
			if(Character.isDigit(c)){
				num = num * 10 + (c - '0');
			}
			
			//if c is an operand or the last element
			if((!Character.isDigit(c) && c != ' ') || (idx == s.length() - 1)){
				if(sign == '+'){
					stack.push(num);
				}
				
				else if(sign == '-'){
					stack.push(-num);
				}
				// * and / has precedence over + and - operation
				else if(sign == '*'){
					stack.push(stack.pop()*num);
				}
				
				else if(sign == '/'){
					stack.push(stack.pop()/num);
				}
				
				sign = s.charAt(idx);
				num = 0;
			}
			
			idx++;
		}
		
		int result = 0;
		for(int i : stack){
			result += i;
		}
		
		return result;
    }
    
    public static void main(String[] args){
//    	int result = new CalculatorII().calculate("2*3+4");
    	int result = new CalculatorII().calculate("3+5 / 2");
    	System.out.println(result);
    }
}
