package interview.leetcode.prob;

/**
 * 
 * Given two positive integers num1 and num2, find the positive integer x such that:

x has the same number of set bits as num2, and
The value x XOR num1 is minimal.
Note that XOR is the bitwise XOR operation.

Return the integer x. The test cases are generated such that x is uniquely determined.

The number of set bits of an integer is the number of 1's in its binary representation.

 

Example 1:

Input: num1 = 3, num2 = 5
Output: 3
Explanation:
The binary representations of num1 and num2 are 0011 and 0101, respectively.
The integer 3 has the same number of set bits as num2, and the value 3 XOR 3 = 0 is minimal.
Example 2:

Input: num1 = 1, num2 = 12
Output: 3
Explanation:
The binary representations of num1 and num2 are 0001 and 1100, respectively.
The integer 3 has the same number of set bits as num2, and the value 3 XOR 1 = 2 is minimal.
 

Constraints:

1 <= num1, num2 <= 109
Seen this question in a real interview before?
1/5
Yes
No
Accepted
23.6K
Submissions
52.9K
Acceptance Rate
44.6%

 * Jan 6, 2025 - 12:12:34 AM
 * Jojo 
 */
public class MinimizeXOR {
	public int minimizeXor(int num1, int num2) {
        int bitCount2 = Integer.bitCount(num2);
        int bitCount1 = Integer.bitCount(num1);

        int result = num1;
        
        if(bitCount2 > bitCount1){
            int diff = bitCount2 - bitCount1;

            // setting the 0 -> 1 from left to right, because we need more 1.
            for(int i=0; i<32 && diff > 0; i++){
                if(((result >> i) & 1) == 0){
                    result |= (1 << i);
                    diff--;
                }
            }
        }
        else if(bitCount2 < bitCount1){
            int diff = bitCount1 - bitCount2;

            // setting the 1 -> 0 from left to right so that the result ^ num1 becomes min.
            for(int i=0; i<32 && diff > 0; i++){
                if(((result >> i) & 1) == 1){
                    result ^= (1 << i);
                    diff--;
                }
            }
        }

        return result;
    }
}
