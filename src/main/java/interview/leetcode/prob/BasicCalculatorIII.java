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
		int[] idx = { 0 };
		return calculate("(" + s + ")", idx);
	}

	private int calculate(String s1, int[] idx) {
		int num = 0, len = s1.length();
		char sign = '+';
		Stack<Integer> stack = new Stack<>();

		int i = idx[0];
		while (i < len) {
			char ch = s1.charAt(i++);

            // checking if the char represents a digit 
			if (ch >= '0' && ch <= '9') {
				num *= 10;
				num += (int) (ch - '0');
			} 
            // skip if the cur char is " "
            else if (ch == ' ') {
				continue;
			} 
            // if there is a '(' time to compute the content first by calling it recursively.
            else if (ch == '(') {
				idx[0] = i;
				num = calculate(s1, idx);
				i = idx[0];
			} 
            // if end of ')' then compute the stack and return 
            else if (ch == ')') {
				if(!stack.isEmpty()) {
                    // e.g if string is (5 + 5 * 2) then we need to compute 5 * 2 before pushing 'num' to stack
					compute(sign, stack, num);
				}
				else {
                    // e.g if string is (5) then there is nothing to compute  before pushing 'num' to stack
					stack.push(num);
				}
				
				idx[0] = i;
				return addAll(stack);
			} else {
				compute(sign, stack, num);
				num = 0;
				sign = ch;
			}
		}

		stack.push(num);
		idx[0] = len;
		return addAll(stack);
	}

	private void compute(char prevSign, Stack<Integer> stack, int num) {
		switch (prevSign) {
		case '+':
			stack.push(num);
			break;
		case '-':
			num *= -1;
			stack.push(num);
			break;
		case '*':
			num *= stack.pop();
			stack.push(num);
			break;
		case '/':
			num = stack.pop() / num;
			stack.push(num);
			break;
		}
	}

	private int addAll(Stack<Integer> stack) {
		int val = 0;
		while (!stack.isEmpty()) {
			val += stack.pop();
		}

		return val;
	}
	
	// old code---------------------
	
    public int calculate_old(String s) {
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
