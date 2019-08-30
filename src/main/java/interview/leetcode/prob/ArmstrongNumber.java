package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * The k-digit number N is an Armstrong number if and only if the k-th power of each digit sums to N.

Given a positive integer N, return true if and only if it is an Armstrong number.

 

Example 1:

Input: 153
Output: true
Explanation: 
153 is a 3-digit number, and 153 = 1^3 + 5^3 + 3^3.
Example 2:

Input: 123
Output: false
Explanation: 
123 is a 3-digit number, and 123 != 1^3 + 2^3 + 3^3 = 36.
 

Note:

1 <= N <= 10^8
Accepted
3,692
Submissions
4,700
 * @author jojo
 * Aug 29, 2019 11:47:24 PM
 */
public class ArmstrongNumber {
	public boolean isArmstrong(int N) {
        int ref = N;
        List<Integer> digits = new ArrayList<>();
        
        while(N != 0){
            digits.add(N%10);
            N /= 10;
        }
        
        int len = digits.size();
        
        int sum = 0;
        
        for(int d : digits){
            sum += Math.pow(d, len);
        }
        
        return ref == sum;
    }
}
