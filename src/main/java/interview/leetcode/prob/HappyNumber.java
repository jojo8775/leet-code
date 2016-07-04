package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
 * @author jojo
 *
 */
public class HappyNumber
{
    public boolean isHappy(int n) {
        Set<Integer> nums = new HashSet<Integer>();
        
        while(nums.add(n)){
            int result = 0;
            while(n != 0){
            	int mod = n%10;
                result += (mod * mod);
                n/=10;
            }
            
            if(result == 1){
                return true;
            }
            
            n = result;
        }
        
        return false;
    }
    
    public static void main(String[] args){
    	System.out.println(new HappyNumber().isHappy(19));
    }
}
