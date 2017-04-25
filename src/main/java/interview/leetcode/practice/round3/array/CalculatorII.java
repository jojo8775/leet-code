package interview.leetcode.practice.round3.array;

public class CalculatorII {
    public int calculator(String str){
        int prev = 0, cur = 0, sumSofar = 0;
        char operator = '+';
        
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            
            if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                if(ch == '*' || ch == '/'){
                    sumSofar -= prev;
                }
                prev = calculate(operator, prev, cur, sumSofar);
                sumSofar += prev;
                cur = 0;
                operator = ch;
            }
            else{
                cur *= 10;
                cur += (int)(ch - '0');
            }
        }
        
        sumSofar += calculate(operator, prev, cur, sumSofar);
        return sumSofar;
    }

    private int calculate(char operator, int prev, int cur, int sumSofar){
        int result = 0;
        
        if(operator == '+'){
            result = cur;
        }
        else if(operator == '-'){
            result = cur * -1;
        }
        else if(operator == '*'){
            result = cur * prev;
        }
        else{
            result = prev/cur;            
        }
        
        return result;
    }
    
    public static void main(String[] args){
        System.out.println(new CalculatorII().calculator("-9*7+9"));
    }
}
