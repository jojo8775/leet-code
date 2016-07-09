package interview.leetcode.prob;

/**
 * Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.


 * @author jojo
 *
 */
public class BasicCalculator {
	public int calculate(String s) {
		if (s.isEmpty()) {
			return 0;
		}

		int[] pos = new int[1];
		return eval("(" + s + ")", pos);
	}

    private int eval(String s, int[] pos){
        int idx = pos[0], num = 0, val = 0, oper = 1;
        
        while(idx < s.length()){
            char c = s.charAt(idx ++);
            
            switch(c){
                case '+' :
                    val = val + num * oper;
                    num = 0;
                    oper = 1;
                    break;
                case '-' :
                    val = val + num * oper;
                    num = 0;
                    oper = -1;
                    break;
                case ' ' :
                    //do nothing
                    break;
                case '(' :
                    //call recurcively to calculate brackets 
                    pos[0] = idx;
                    val = val + eval(s, pos) * oper;
                    idx = pos[0];
                    break;
                case ')' :
                    pos[0] = idx;
                    val = val + num * oper;
                    return val;
                default :
                    num = num * 10 + (c - '0');
            }
        }
        
        return val;
    }
	
	public static void main(String[] args){
		//int r = new BasicCalculator().calculate("(1+(4+5+2)-3)+(6+8)");
		int r = new BasicCalculator().calculate("3 + 4 + (6+8)");
		System.out.println(r);
	}
}
