package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * You are given an array of integers nums. Perform the following steps:

Find any two adjacent numbers in nums that are non-coprime.
If no such numbers are found, stop the process.
Otherwise, delete the two numbers and replace them with their LCM (Least Common Multiple).
Repeat this process as long as you keep finding two adjacent non-coprime numbers.
Return the final modified array. It can be shown that replacing adjacent non-coprime numbers in any arbitrary order will lead to the same result.

The test cases are generated such that the values in the final array are less than or equal to 108.

Two values x and y are non-coprime if GCD(x, y) > 1 where GCD(x, y) is the Greatest Common Divisor of x and y.

 

Example 1:

Input: nums = [6,4,3,2,7,6,2]
Output: [12,7,6]
Explanation: 
- (6, 4) are non-coprime with LCM(6, 4) = 12. Now, nums = [12,3,2,7,6,2].
- (12, 3) are non-coprime with LCM(12, 3) = 12. Now, nums = [12,2,7,6,2].
- (12, 2) are non-coprime with LCM(12, 2) = 12. Now, nums = [12,7,6,2].
- (6, 2) are non-coprime with LCM(6, 2) = 6. Now, nums = [12,7,6].
There are no more adjacent non-coprime numbers in nums.
Thus, the final modified array is [12,7,6].
Note that there are other ways to obtain the same resultant array.
Example 2:

Input: nums = [2,2,1,1,3,3,3]
Output: [2,1,1,3]
Explanation: 
- (3, 3) are non-coprime with LCM(3, 3) = 3. Now, nums = [2,2,1,1,3,3].
- (3, 3) are non-coprime with LCM(3, 3) = 3. Now, nums = [2,2,1,1,3].
- (2, 2) are non-coprime with LCM(2, 2) = 2. Now, nums = [2,1,1,3].
There are no more adjacent non-coprime numbers in nums.
Thus, the final modified array is [2,1,1,3].
Note that there are other ways to obtain the same resultant array.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
The test cases are generated such that the values in the final array are less than or equal to 108.
 

Seen this question in a real interview before?
1/5
Yes
No
Accepted
35,580/71.8K
Acceptance Rate
49.6%
 * chiranjeebnandy
 * Sep 15, 2025  2025  10:56:53 PM
 */
public class ReplaceNonCoprimeNumbersInArray {
	public List<Integer> replaceNonCoprimes(int[] nums) {
        LinkedList<Integer> deQueue = new LinkedList<Integer>();
        for (int i=0; i<nums.length; i++) {
            int cur = nums[i];

            boolean isCoprimePair = false;
            while (!isCoprimePair && !deQueue.isEmpty()) {
                int next = deQueue.isEmpty() ? 1 : deQueue.peekLast();

                int gcd = gcd(cur, next);
                if (gcd == 1){
                    isCoprimePair = true;
                }
                else{
                    // getting the LCM since these two adjacent pair are not co-prime
                    cur *= deQueue.pollLast() / gcd;
                }
            }

            deQueue.offerLast(cur);
        }
        return deQueue;
    }

    private int gcd(int a, int b){
        while(b != 0){
            int rem = a % b;
            a = b;
            b = rem;
        }

        return a;
    }

    private int lcm(int a, int b){
        return a * b / gcd(a,b);
    }
}
