package interview.leetcode.practice.round3.divideandconcur;

import java.util.ArrayList;
import java.util.List;

public class ValidParenthesis {
    public List<Node> findWays(String str){
        if(str.isEmpty()){
            return new ArrayList<Node>();
        }
        
        List<Node> result = new ArrayList<>();
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            
            if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                List<Node> left = findWays(str.substring(0,i));
                List<Node> right = findWays(str.substring(i+1));
                
                for(Node ln : left){
                    for(Node rn : right){
                        String exp = "( " + ln.str + " " + ch + " " + rn.str + " )";
                        result.add(new Node(exp ,calculate(ln.val, rn.val, ch)));
                    }
                }
            }
        }
        
        if(result.isEmpty()){
            result.add(new Node(str, Integer.valueOf(str)));
        }
        
        return result;
    }

    private int calculate(int a, int b, char operator){
        if(operator == '+'){
            return a + b;
        }
        else if(operator == '-'){
            return a - b;
        }
        else if(operator == '*'){
            return a * b;
        }
        else {
            return a / b;
        }
    }

    private static class Node {
        String str; 
        int val;
        
        public Node(String str, int val){
            this.val = val;
            this.str = str;
        }
    }
    
    public static void main(String[] args){
//        List<Node> result = new ValidParenthesis().findWays("2+8+4+4*2/5");
        List<Node> result = new ValidParenthesis().findWays("2*3-4*5");
        
        for(Node n : result){
            System.out.println("exp: " + n.str + "  Val: " + n.val);
        }
    }
}
