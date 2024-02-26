package interview.leetcode.practice;

public class atoi_1 {
    public int myAtoi(String str) {
        str = str.trim();
        if(str.length() == 0){
            return 0;
        }
    
        long result = 0;
        int sign = 1, idx = 0, len = str.length();
    
        if(idx < len && str.charAt(idx) == '-' || str.charAt(idx) == '+'){
            sign = str.charAt(idx ++) == '-' ? -1 : 1;
        }
        
        while(idx < len){
            char ch = str.charAt(idx++);
            if(ch < '0' || ch > '9'){
                break;
            }
            
            result *= 10;
            result += (int)(ch - '0');
            if(result * sign < Integer.MIN_VALUE || result * sign > Integer.MAX_VALUE){
                break;
            }
        }
    
        result *= sign;
    
        if(result < Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }
    
        if(result > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
    
        return (int) result;
    }
    
    public static void main(String[] args){
        System.out.println(new atoi_1().myAtoi("123"));
        System.out.println(new atoi_1().myAtoi("    010"));
    }
}
