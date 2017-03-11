package interview.leetcode.prob;

/**
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.

Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.
Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
Subscribe to see which companies asked this question.

Show Tags

 * @author jojo
 *Mar 11, 201711:38:09 AM
 */
public class NumberComplement {
    public class Solution {
        public int findComplement(int num) {
            int temp = num, xor = 0;
            while(num > 0){
                xor = xor << 1;
                xor |= 1;
                num = num >>> 1;
            }
            
            return xor ^ temp;
        }
    }
}
