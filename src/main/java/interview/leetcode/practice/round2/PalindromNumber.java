package interview.leetcode.practice.round2;

public class PalindromNumber {
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        
        int temp = x, result = 0;
        while(temp > 0){
            result *= 10;
            result += temp%10;
            temp /= 10;
        }
        
        return result == x;
    }
}
