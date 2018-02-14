package interview.leetcode.datastructure.heap;

/**
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.


 * @author jojo
 *Feb 14, 20181:06:57 PM
 */
public class NumberOf1Bit {
 // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int num = 0;
        
        while(n != 0){
            num += (n & 1);
            n = n >>> 1; // this is for unsigned numbers
        }
        
        return num;
    }
}
