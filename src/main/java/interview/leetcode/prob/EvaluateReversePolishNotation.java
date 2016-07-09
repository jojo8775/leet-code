package interview.leetcode.prob;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * @author jojo
 *
 */
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
    
        for(int i=0; i<tokens.length; i++){
            if(tokens[i].equals("*") || tokens[i].equals("/") || tokens[i].equals("+") || tokens[i].equals("-")){
                stack.push(eval(stack.pop(), stack.pop(), tokens[i]));
            }
            else{
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        
        return stack.pop();
    }
    
    private int eval(int a, int b, String oper){
        if(oper.equals("*")){
            return b * a;
        }
        else if(oper.equals("/")){
            return b/a;
        }
        else if(oper.equals("+")){
            return b+a;
        }
        else{
            return b-a;
        }
    }
}
