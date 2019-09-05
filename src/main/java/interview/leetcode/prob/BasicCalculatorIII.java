package interview.leetcode.prob;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 

Note: Do not use the eval built-in library function.


 * @author jojo
 * Sep 5, 2019 1:07:26 AM
 */
public class BasicCalculatorIII {
    public int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();  // stores the numbers
        Stack<Character> operandStack = new Stack<>(); // stores the operations 
        
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            
            // skip spaces
            if(ch == ' '){
                continue;
            }
            
            // stores the number 
            if(Character.isDigit(ch)){
                int num = ch - '0';
                while(i < s.length() - 1 && Character.isDigit(s.charAt(i + 1))){
                    num *= 10;
                    num += (s.charAt(i + 1) - '0');
                    i++;
                }
                
                numStack.push(num);
            }
            
            // start of sub expression
            else if(ch == '('){
                operandStack.push(ch);
            }
            
            // end of sub expression, so need to calculate it and store it as one number.
            else if(ch == ')'){
                while(operandStack.peek() != '('){
                    numStack.push(calculate(operandStack.pop(), numStack.pop(), numStack.pop())); // since stack the args are b, and a
                }
                
                operandStack.pop();
            }
            
            // just a operand
            else if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
            	// need to honor the precedence
                while(!operandStack.isEmpty() && precedence(ch, operandStack.peek())){
                    numStack.push(calculate(operandStack.pop(), numStack.pop(), numStack.pop()));
                }
                
                // if the equation starts with '-'
                if(numStack.isEmpty()) {
                	numStack.push(0);
                }
                
                // if the sub expression starts with '-'
                else {
                	int idx = i - 1;
                	while(idx > 0 && s.charAt(idx) == ' ') {
                		idx--;
                	}
                	
                	if(s.charAt(idx) == '(') {
                		numStack.push(0);
                	}
                }
                
                operandStack.push(ch);
            }
        }
        
        // compute the existing stack.
        while(!operandStack.isEmpty()){
            numStack.push(calculate(operandStack.pop(), numStack.pop(), numStack.pop()));
        }
        
        return numStack.pop();
    }
    
    private int calculate(char op, int b, int a){
        switch(op){
            case '+' : return a + b;
            case '-' : return a - b;
            case '*' : return a * b;
            case '/' : return a / b;
        }
        
        return 0;
    }
    
    private boolean precedence(char op1, char op2){
        if((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')){
            return false;
        }
        
        if(op2 == '(' || op2 == ')'){
            return false;
        }
        
        return true;
    }
}
