package interview.leetcode.prob;

/**
 * Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321


 * @author jojo
 *
 */
public class ReverseInteger {
    public int reverse(int x) {
        long l = x;
        int sign = x < 0 ? -1 : 1;
        x *= sign;
        
        long  reverse = 0;
        
        while(x != 0){
            reverse *= 10L;
            reverse += x%10L;
            x /= 10L;
        }
        
        if(reverse > Integer.MAX_VALUE || reverse < Integer.MIN_VALUE){
            return 0;
        }
        
        return (int) reverse * sign;
    }
    
    public static void main(String[] args){
    	System.out.println(new ReverseInteger().reverse(-1123));
    }
}
