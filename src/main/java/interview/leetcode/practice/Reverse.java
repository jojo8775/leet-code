package interview.leetcode.practice;

public class Reverse {
    public boolean palindromNumber(int num){
        int reverse = 0, temp = num;
        while(temp > 0){
            reverse *= 10;
            reverse += (temp % 10);
            temp /= 10;
        }
        
        return num == reverse;
    }
}
