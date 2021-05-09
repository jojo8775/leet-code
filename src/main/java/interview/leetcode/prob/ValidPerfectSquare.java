package interview.leetcode.prob;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.
 * @author jojo
 *
 */
public class ValidPerfectSquare
{
	public boolean isPerfectSquare(int num) {
        long low = 1, high = num;
        while (low <= high) {
        	// this is basically making /2 
            long mid = (low + high) >> 1;
            
            if (mid * mid == num) {
                return true;
            }
            else if (mid * mid < num) {
                low = mid + 1;
            } 
            else {
                high =  mid - 1;
            }
        }
        
        return false;
    }
}
