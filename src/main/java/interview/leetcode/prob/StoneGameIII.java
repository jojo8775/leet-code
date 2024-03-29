package interview.leetcode.prob;

/**
 * Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.

Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2, or 3 stones from the first remaining stones in the row.

The score of each player is the sum of the values of the stones taken. The score of each player is 0 initially.

The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.

Assume Alice and Bob play optimally.

Return "Alice" if Alice will win, "Bob" if Bob will win, or "Tie" if they will end the game with the same score.

 

Example 1:

Input: stoneValue = [1,2,3,7]
Output: "Bob"
Explanation: Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.
Example 2:

Input: stoneValue = [1,2,3,-9]
Output: "Alice"
Explanation: Alice must choose all the three piles at the first move to win and leave Bob with negative score.
If Alice chooses one pile her score will be 1 and the next move Bob's score becomes 5. In the next move, Alice will take the pile with value = -9 and lose.
If Alice chooses two piles her score will be 3 and the next move Bob's score becomes 3. In the next move, Alice will take the pile with value = -9 and also lose.
Remember that both play optimally so here Alice will choose the scenario that makes her win.
Example 3:

Input: stoneValue = [1,2,3,6]
Output: "Tie"
Explanation: Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.
 

Constraints:

1 <= stoneValue.length <= 5 * 104
-1000 <= stoneValue[i] <= 1000
Accepted
83,796
Submissions
129,850
 * @author jojo
 * Nov. 3, 2023 10:49:51 p.m.
 */
public class StoneGameIII {
	public String stoneGameIII(int[] stoneValue) {
        // return bottomUp(stoneValue);
        return topDown(stoneValue);
    }
    
    private String bottomUp(int[] stoneValue){
        int len = stoneValue.length;
    
        // states: here is state represent max score difference between players
        int[] dp = new int[len + 1];
        
        for(int i=len-1; i>=0; i--){
            // relation: taking one stone, dp[i+1] because the next  players score needs to be substracted
            dp[i] = stoneValue[i] - dp[i+1];
            
            // relation: taking two stones
            if(i + 1 < len){
                dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i+1] - dp[i+2]);
            }
            
            // relation: taking three stones
            if(i + 2 < len){
                dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i+1] + stoneValue[i+2] - dp[i+3]);
            }
        }
        
        // dp[0] represents the max difference between players with all the stones left to pick. 
        // so the first stone tells who can win
        if(dp[0] > 0){
            return "Alice";
        }
        else if(dp[0] < 0){
            return "Bob";
        }
        else{
            return "Tie";
        }
    }
    
    private String topDown(int[] stoneValue){
        
        // states 
        Integer[] memo = new Integer[stoneValue.length];
        
        int val = dp(stoneValue, 0, memo);
        
        if(val > 0){
            return "Alice";
        }
        else if(val < 0){
            return "Bob";
        }
        else{
            return "Tie";
        }
    }
    
    private int dp(int[] stoneValue, int idx, Integer[] memo){
        // base case: no stones left to pick
        if(idx == stoneValue.length){
            return 0;
        }
        
        if(memo[idx] != null){
            return memo[idx];
        }
        
        // relation: taking one stone
        int val = stoneValue[idx] - dp(stoneValue, idx + 1, memo);
        
        // relation: taking two stones
        if(idx + 1 < stoneValue.length){
            val = Math.max(val, stoneValue[idx] + stoneValue[idx + 1] - dp(stoneValue, idx + 2, memo));
        }
        
        // relation: taking three stones
        if(idx + 2 < stoneValue.length){
            val = Math.max(val, stoneValue[idx] + stoneValue[idx + 1] + stoneValue[idx + 2] - dp(stoneValue, idx + 3, memo));
        }
        
        memo[idx] = val;
        
        return val;
    }
}
