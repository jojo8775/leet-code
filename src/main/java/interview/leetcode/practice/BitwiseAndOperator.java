package interview.leetcode.practice;

public class BitwiseAndOperator {
    public int bitwiseAnd(int a, int b){
        int ls = 0;
        
        while(a != 0 && b != a){
            a = a >> 1;
            b = b >> 1;
            ls++;
        }
        
        if(a == 0){
            return a;
        }
        
        return a << ls;
    }
    
    public static void main (String[] args){
        System.out.println(new BitwiseAndOperator().bitwiseAnd(1, 1));
    }
}
