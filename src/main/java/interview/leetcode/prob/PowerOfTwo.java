package interview.leetcode.prob;

/**
 * Given an integer n, return true if it is a power of two. Otherwise, return false.

An integer n is a power of two, if there exists an integer x such that n == 2x.

 

Example 1:

Input: n = 1
Output: true
Explanation: 20 = 1
Example 2:

Input: n = 16
Output: true
Explanation: 24 = 16
Example 3:

Input: n = 3
Output: false
 

Constraints:

-231 <= n <= 231 - 1
 

Follow up: Could you solve it without loops/recursion?
 * 
 * 
 * Given an integer, write a function to determine if it is a power of two.
 * @author jojo
 *
 */
public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0){
            return false;
        }
        
        return (n&(n-1)) == 0;
    }
    
    public boolean isPowerOfTwo_BitWise(int n){
        if(n == 0){
            return false;
        }
        
        long x = (long) n;
        
        // finding the 2s compliment. in 2's complement all the bits are flipped except the rightmost 1 bit
        // eg. 11010 -> 2s compliment -> 00110 
        long x2s = x & -x;
        
        return x == x2s;
    }
    
    public boolean isPowerOfTwo_Simulation(int n){
        if(n <= 1){
            return n == 1;
        }
        
        while(n > 1){
            if(n % 2 != 0){
                return false;
            }
            
            n = n / 2;
        }
        
        return true;
    }
}
