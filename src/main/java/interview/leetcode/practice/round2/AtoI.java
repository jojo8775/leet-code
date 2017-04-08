package interview.leetcode.practice.round2;

public class AtoI {
    public int myAtoi(String str) {
        str = str.replaceAll("\\s", "");

        if(str.isEmpty()){
            return 0;
        }
        
        int sign = 1, idx = 0;
        if(str.charAt(0) == '-'){
            sign = -1;
            idx = 1;
        }
        
        long result = 0;
        
        while(idx < str.length()){
            char ch = str.charAt(idx++);
            
            if(ch >= '0' && ch <= '9'){
                result *= 10;
                result += (int) (ch - '0');
                System.out.println(result);
                // make sure you break it instead of return as it we need to handle the corner case of Max and Min value
                if(result > Integer.MAX_VALUE){
                    break;
                }
            }
            else{
                break;
            }
        }
        
        result *= sign;
        if(result > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        
        if(result < Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }
        
        return (int) result;
    }
}
