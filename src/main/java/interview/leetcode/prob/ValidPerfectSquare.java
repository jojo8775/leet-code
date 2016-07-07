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
        if(num == 0){
            return false;
        }
        if(num == 1){
            return true;
        }
        
        int beg=1,end=num,mid=0;
        
        while(beg <= end){
        	//need to use beg for efficiency
            mid = (end-beg)/2 + beg;
            if(mid * mid > num || mid*mid <= 0){
                end = mid - 1;
            }
            else if(mid * mid < num){
                beg = mid + 1;
            }
            else{
                return true;
            }
        }
        
        return false;
    }
}
