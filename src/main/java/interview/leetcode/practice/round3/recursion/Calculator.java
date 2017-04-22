package interview.leetcode.practice.round3.recursion;

public class Calculator {
    public int calculate(String str){
        return calculate("(" + str + ")", new int[]{0});
    }

    private int calculate(String str, int[] idx){
        int num = 0, prev = 0;
        char operator = '+';
        for(int i=idx[0]; i<str.length(); i++){
            char ch = str.charAt(i);
            
            if(ch == '+' || ch == '-'){
                prev = calculate(operator, prev, num);
                operator = ch;
                num = 0;
            }
            else if(ch == '('){
                idx[0] = i + 1;
                num = calculate(str, idx);
                i = idx[0];
            }
            else if(ch == ')'){
                prev = calculate(operator, prev, num);
                idx[0] = i;
                return prev;
            }
            else if(ch == ' '){
                continue;
            }
            else{
                num *= 10;
                num += (int)(ch - '0');
            }
        }
        
        return calculate(operator, prev, num);
    }

    private int calculate(char operator, int num1, int num2){
        return operator == '+' ? num1 + num2 : num1 - num2;
    }

    public static void main(String[] args){
        System.out.println(new Calculator().calculate("-90+1-(7+3)+4+9+(7+9)"));
    }
}
