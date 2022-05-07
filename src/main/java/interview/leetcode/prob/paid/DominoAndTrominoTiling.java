package interview.leetcode.prob.paid;

/**
 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.


Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.

In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.

 

Example 1:


Input: n = 3
Output: 5
Explanation: The five different ways are show above.
Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 1000
Accepted
46,853
Submissions
97,862
 * @author jojo
 * Apr 30, 2022 5:26:47 PM
 */
public class DominoAndTrominoTiling {
	 public int numTilings(int n) {
	        if(n <= 2){
	            return n;
	        }
	        
	        long mod = (long)(1e9 + 7);
	        
	        long[] fullCover = new long[n+1];
	        long[] partialCover = new long[n+1];
	        
	        fullCover[1] = 1;
	        fullCover[2] = 2;
	        partialCover[2] = 1;
	        
	        for(int i=3; i<=n; i++){
	            fullCover[i] = (fullCover[i-1] + fullCover[i-2] + (2 * partialCover[i-1])) % mod;
	            partialCover[i] = (fullCover[i-2] + partialCover[i-1]) % mod;
	        }
	        
	        return (int) fullCover[n];
	    }
}
