package interview.leetcode.prob;

/**
 * Given an integer n, you must transform it into 0 using the following operations any number of times:

Change the rightmost (0th) bit in the binary representation of n.
Change the ith bit in the binary representation of n if the (i-1)th bit is set to 1 and the (i-2)th through 0th bits are set to 0.
Return the minimum number of operations to transform n into 0.

 

Example 1:

Input: n = 3
Output: 2
Explanation: The binary representation of 3 is "11".
"11" -> "01" with the 2nd operation since the 0th bit is 1.
"01" -> "00" with the 1st operation.
Example 2:

Input: n = 6
Output: 4
Explanation: The binary representation of 6 is "110".
"110" -> "010" with the 2nd operation since the 1st bit is 1 and 0th through 0th bits are 0.
"010" -> "011" with the 1st operation.
"011" -> "001" with the 2nd operation since the 0th bit is 1.
"001" -> "000" with the 1st operation.
 

Constraints:

0 <= n <= 109
Accepted
19,798
Submissions
29,535
 * @author jojo
 * Nov. 29, 2023 8:50:31 p.m.
 */
public class MinimumOneBitOperationToMakeIntegerZero {
	public int minimumOneBitOperations(int n) {
        if (n <= 1){
            // if n = 0, no changes needed
            // if n = 1, only one change is needed.
            return n;  
        } 
        
        int count = 0;
        
        // finding the most significant bit. in 1100, the left most '1' is the most significant bit
        while ((1<<count) <= n){
            count++;  
        } 
        
        // based on the problem if n = 6 [binary representaion: 100]
        // it needs the following seven steps to make it 0 
        // step 1:  101  (change type 1 : changing 0th bit)
        // step 2:  111  (change type 2 : changing 1st bit)
        // step 3:  110  (change type 1 : changing 0th bit)
        // step 4:  010  (change type 2 : changing 2nd bit)
        // step 5:  011  (change type 1 : changing 0th bit)
        // step 6:  001  (change type 2 : changing 1st bit)
        // step 7:  000  (change type 1 : changing 0th bit)
        //
        // now if n was 7 [binary representaion: 110] 
        // we will need less than 7 steps. Precisely number of steps needed to bring 10 to 0
        // this is being achieved by calling this method recusively using minimumOneBitOperations(n-(1<<(count-1))
        return ((1<<count)-1) - minimumOneBitOperations(n-(1<<(count-1)));
    }
}
