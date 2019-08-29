package interview.leetcode.prob;

/**
 * You have d dice, and each die has f faces numbered 1, 2, ..., f.

Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.

 

Example 1:

Input: d = 1, f = 6, target = 3
Output: 1
Explanation: 
You throw one die with 6 faces.  There is only one way to get a sum of 3.
Example 2:

Input: d = 2, f = 6, target = 7
Output: 6
Explanation: 
You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
Example 3:

Input: d = 2, f = 5, target = 10
Output: 1
Explanation: 
You throw two dice, each with 5 faces.  There is only one way to get a sum of 10: 5+5.
Example 4:

Input: d = 1, f = 2, target = 3
Output: 0
Explanation: 
You throw one die with 2 faces.  There is no way to get a sum of 3.
Example 5:

Input: d = 30, f = 30, target = 500
Output: 222616187
Explanation: 
The answer must be returned modulo 10^9 + 7.
 

Constraints:

1 <= d, f <= 30
1 <= target <= 1000
 * @author jojo
 * Aug 28, 2019 11:14:01 PM
 */
public class NumberOfDiceRollsWithTargetSum {
	public int numRollsToTarget(int d, int f, int target) {
		int maxSum = f * d; // max sum can be obtained from the dices 
		if (maxSum < target || target < d) {
			return 0;
		}

		int mod = (int) (Math.pow(10, 9) + 7);
		long[] dp = new long[maxSum + 1];

		for(int i=1; i<=f; i++) {
			dp[i] = 1; // there is only one way to represent a face of a dice
		}
		
		for(int i=2; i<=d; i++) {
			for(int j=target; j>=1; j--) {
				dp[j] = 0; // initial value
				
				for(int k=1; k<=f && k < j; k++) {
					dp[j] += dp[j-k]; // taking advantage of DP
				}
				
				dp[j] %= mod; // since the result needs to be modulated 
			}
			
			// making the sum which are too small for the number of dices. e.g 1 cannot be represented by two dices  
			for(int j = i-1; j>0; j--) {
				dp[j] = 0;
			}
		}
		
		return (int) dp[target];
	}
}
