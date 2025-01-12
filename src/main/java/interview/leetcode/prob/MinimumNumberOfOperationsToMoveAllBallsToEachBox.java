package interview.leetcode.prob;

/**
 * You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.

In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.

Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.

Each answer[i] is calculated considering the initial state of the boxes.

 

Example 1:

Input: boxes = "110"
Output: [1,1,3]
Explanation: The answer for each box is as follows:
1) First box: you will have to move one ball from the second box to the first box in one operation.
2) Second box: you will have to move one ball from the first box to the second box in one operation.
3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.
Example 2:

Input: boxes = "001011"
Output: [11,8,5,4,3,4]
 

Constraints:

n == boxes.length
1 <= n <= 2000
boxes[i] is either '0' or '1'.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
152.6K
Submissions
175.6K
Acceptance Rate
86.9%
 * 
 * Jan 5, 2025 - 9:21:14 PM
 * Jojo 
 */
public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {
	public int[] minOperations(String boxes) {
        int len = boxes.length();

        int moves = 0, ballCount = 0;
        int[] leftToRight = new int[len];

        for(int i=0; i<len; i++){
            moves += ballCount;
            leftToRight[i] = moves;

            ballCount += boxes.charAt(i) == '1' ? 1 : 0;
        }

        moves = 0;
        ballCount = 0;

        int[] rightToLeft = new int[len];
        for(int i=len-1; i>=0; i--){
            moves += ballCount;
            rightToLeft[i] = moves;

            ballCount += boxes.charAt(i) == '1' ? 1 : 0;
        }

        int[] result = new int[len];

        for(int i=0; i<len; i++){
            result[i] = leftToRight[i] + rightToLeft[i];
        }

        return result;
    }
}