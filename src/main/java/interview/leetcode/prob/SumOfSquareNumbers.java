package interview.leetcode.prob;

/**
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.

 

Example 1:

Input: c = 5
Output: true
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:

Input: c = 3
Output: false
 

Constraints:

0 <= c <= 231 - 1
Accepted
239,736
Submissions
686,078

 * Jun 16, 2024 - 11:03:03 PM
 * Jojo 
 */
public class SumOfSquareNumbers {
	public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            // a*a + b*b = c
            // b*b = c - a*a;
            int b = c - (int)(a * a);
            
            // using binary search to find the square root of b
            if (binary_search(0, b, b)){
                return true;
            }
        }
        return false;
    }
    
    private boolean binary_search(long s, long e, int n) {
        while(s <= e){
            long mid = s + (e - s)/2;
            
            if(mid * mid == n){
                return true;
            }
            
            if(mid * mid > n){
                e = mid - 1;
            }
            else{
                s = mid + 1;
            }
        }
        
        return false;
    }
}
