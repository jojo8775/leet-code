package interview.leetcode.prob;

/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

Example:

Input: 38
Output: 2 
Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2. 
             Since 2 has only one digit, return it.
Follow up:
Could you do it without any loop/recursion in O(1) runtime?
 * @author jojo
 * May 22, 2019 8:44:03 PM
 */
public class AddDigits {
	public int addDigits_adv(int num) {
        if(num == 0){
            return 0;
        }
        else if(num % 9 == 0){
            return 9;
        }
        else{
            return num % 9;
        }
    }
	
	public int addDigits(int num) {
        int sum = num;
        while(num > 9){
            sum = 0;
            while(num != 0){
                sum += (num % 10);
                num /= 10;
            }
            
            num = sum;
        }
        
        return sum;
    }
}
