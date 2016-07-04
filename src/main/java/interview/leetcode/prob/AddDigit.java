package interview.leetcode.prob;

/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?

Show Hint 

 * @author jojo
 *
 */
public class AddDigit
{
    public int addDigits(int num) {
        while(num > 9){
            int result = 0;
            while(num != 0){
                result += (num%10);
                num /= 10;
            }
            
            num = result;
        }
        
        return num;
    }
    
    //O(n) solution : https://en.wikipedia.org/wiki/Digital_root
    public int addDigit_ConstantTime(int num){
    	return num - 9*((num-1)/9);
    }
    
}
