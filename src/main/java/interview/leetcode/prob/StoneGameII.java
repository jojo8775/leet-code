package interview.leetcode.prob;

/**
 * Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones. 

Alice and Bob take turns, with Alice starting first.  Initially, M = 1.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

 

Example 1:

Input: piles = [2,7,9,4,4]
Output: 10
Explanation:  If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 piles in total. If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's larger. 
Example 2:

Input: piles = [1,2,3,4,5,100]
Output: 104
 

Constraints:

1 <= piles.length <= 100
1 <= piles[i] <= 104
Accepted
89,088
Submissions
130,549
 * @author jojo
 * Nov. 5, 2023 10:33:24 p.m.
 */
public class StoneGameII {
	public int stoneGameII(int[] piles) {
        return topdown(piles);
    }
    
    private int topdown(int[] piles){
        int len = piles.length;
        
        // this is done to make calculation faster 
        int[] preSum = new int[len];
        preSum[len-1] = piles[len-1];
        
        // taking the sum of all the elements from the end
        for(int i=len-2; i>=0; i--){
            preSum[i] = piles[i] + preSum[i+1];
        }
        
        // states:
        Integer[][] memo = new Integer[len][len];
        
        return dp(piles, 0, 1, preSum, memo);
    }
    
    private int dp(int[] piles, int idx, int M, int[] preSum, Integer[][] memo){
        // base case.
        if(idx + 2 * M >= piles.length){
            return preSum[idx];
        }
        
        if(memo[idx][M] != null){
            return memo[idx][M];
        }
        
        int result = 0;
        
        for(int x = 1; x <= (2*M); x++){
            int currentPlayerStones = preSum[idx] - preSum[idx + x];
            
            int nextPlayerStones = dp(piles, idx + x, Math.max(M, x), preSum, memo);
            
            // preSum[idx + x] is the max of next player if there is no more turn for the current player
            int nextPlayerLeftOver = preSum[idx + x] - nextPlayerStones;
            
            result = Math.max(result, currentPlayerStones + nextPlayerLeftOver);
        }
        
        return memo[idx][M] = result;
    }
}
