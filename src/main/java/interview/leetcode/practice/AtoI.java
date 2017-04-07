package interview.leetcode.practice;

public class AtoI {
    public int atoi(String str){
        int sign = 1, idx = 0, len = str.length();

        if(str.charAt(idx) == '-'){
            idx++;
            sign = -1;
        }
        
        int num = 0;
        while(idx < len){
            num *= 10;
            char ch = str.charAt(idx++);
            if(ch < '0' || ch > '9'){
                return 0;
            }
            
            num += (int) ( ch - '0');
            if(num < 0){
                return Integer.MAX_VALUE;
            }
        }
        
        return num * sign;
    }
}
