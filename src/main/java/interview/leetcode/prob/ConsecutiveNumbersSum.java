package interview.leetcode.prob;

/**
 * Given an integer n, return the number of ways you can write n as the sum of consecutive positive integers.

 

Example 1:

Input: n = 5
Output: 2
Explanation: 5 = 2 + 3
Example 2:

Input: n = 9
Output: 3
Explanation: 9 = 4 + 5 = 2 + 3 + 4
Example 3:

Input: n = 15
Output: 4
Explanation: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 

Constraints:

1 <= n <= 109
 
Seen this question in a real interview before?
1/6
Yes
No
Accepted
103,031/240.9K
Acceptance Rate
42.8%

 * 
 * chiranjeebnandy
 * Jul 11, 2026  2026  2:47:24 PM
 */
public class ConsecutiveNumbersSum {
	public int consecutiveNumbersSum_1(int N) {
        int count = 0;

        // x > 0 --> N/k - (k + 1)/2 > 0
        int upper_limit = (int)(Math.sqrt(2 * N + 0.25) - 0.5);
        for (int k = 1; k <= upper_limit; ++k) {
            // x should be an integer
            if ((N - k * (k + 1) / 2) % k == 0)
                count++;
        }
        return count;
    }


    public int consecutiveNumbersSum(int n) {
        int count = 0;

        // think of a staircase problem. consecutive number from x, is x + 1, x + 2, x + 3 ... so on.. 
        // the problem is to find the x. now think of a number 15 and let we want to try a seq of 3 numbers 
        // x + 1, x + 2, x + 3 == 15. so 3x = 15 - (1 + 2 + 3) = 15 - 6 = 9
        // 3x = 9
        // x = 9 / 3 == 3

        // 3 + 1, 3 + 2, 3 + 3 = 15 
        // 4 + 5 + 6 = 15

        // represents the sequence length
        int seqLen = 1;

        while(n > 0){
            n -= seqLen;

            if(n % seqLen == 0){
                count++;
            }

            seqLen++;
        }

        return count;
    }
}
