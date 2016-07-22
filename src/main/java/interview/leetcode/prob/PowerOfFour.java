package interview.leetcode.prob;

/**
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true. Given num = 5, return false.

Follow up: Could you solve it without loops/recursion?
 * @author jojo
 *
 */
public class PowerOfFour {
    public boolean isPowerOfFour(int num) {
        if(num != 1 && num < 4){
            return false;
        }
    
        while(num > 4){
            if(num%4 != 0){
                return false;
            }
            
            num = num/4;
            
            if(num%4 != 0){
                return false;
            }
        }
        
        return true;
    }
}
