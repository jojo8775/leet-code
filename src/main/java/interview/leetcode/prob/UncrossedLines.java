package interview.leetcode.prob;

/**
 * We write the integers of A and B (in the order they are given) on two separate horizontal lines.

Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:

A[i] == B[j];
The line we draw does not intersect any other connecting (non-horizontal) line.
Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.

Return the maximum number of connecting lines we can draw in this way.

 

Example 1:


Input: A = [1,4,2], B = [1,2,4]
Output: 2
Explanation: We can draw 2 uncrossed lines as in the diagram.
We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.
Example 2:

Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
Output: 3
Example 3:

Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
Output: 2
 

Note:

1 <= A.length <= 500
1 <= B.length <= 500
1 <= A[i], B[i] <= 2000
Accepted
7,293
Submissions
14,066
 * @author jojo
 * Sep 1, 2019 2:12:46 AM
 */
public class UncrossedLines {
	public int maxUncrossedLines(int[] A, int[] B) {
     // return topDown(A, B);
     return bottomUp(A, B);
 }
	
	private int bottomUp(int[] A, int[] B){
        // represents states. Since each element in A has B times possibilities 
        int[][] dp = new int[A.length + 1][B.length + 1];
        
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[i].length; j++){
                if(A[i-1] == B[j-1]){
                    // Relation: if the first entry of A and B are same we connect them 
                    // since there is no chance of overlap
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    // Relation: if the first entry of A and B are different then we Skip first entry of A or B and take max
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[A.length][B.length];
    }
    
    private int topDown(int[] A, int[] B){
        // represents states. Since each element in A has B times possibilities 
        Integer[][] memo = new Integer[A.length][B.length];

        return dp(A, A.length - 1, B, B.length - 1, memo);
    }

    private int dp(int[] A, int ae, int[] B, int be, Integer[][] memo){
        // base case.
        if(ae < 0 || be < 0){
            return 0;
        }

        // memo lookup for repeatative problems 
        if(memo[ae][be] != null){
            return memo[ae][be];
        }

        int result = 0;

        // Relation: if the first entry of A and B are same we connect them 
        // since there is no chance of overlap
        if(A[ae] == B[be]){
            result = 1 + dp(A, ae - 1, B, be - 1, memo);
        }
        else{
            // Relation: if the first entry of A and B are different then we Skip first entry of A or B and take max
            int skippingA = dp(A, ae - 1, B, be, memo);
            int skippingB = dp(A, ae, B, be - 1, memo);
            result = Math.max(skippingA, skippingB);
        }

        memo[ae][be] = result;
        return result;
    }
}
