package interview.leetcode.prob;

/**
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose any two stones and smash them together. Suppose the stones have weights x and y with x <= y. The result of this smash is:

If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
At the end of the game, there is at most one stone left.

Return the smallest possible weight of the left stone. If there are no stones left, return 0.

 

Example 1:

Input: stones = [2,7,4,1,8,1]
Output: 1
Explanation:
We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
we can combine 1 and 1 to get 0, so the array converts to [1], then that's the optimal value.
Example 2:

Input: stones = [31,26,33,21,40]
Output: 5
 

Constraints:

1 <= stones.length <= 30
1 <= stones[i] <= 100
Accepted
78,673
Submissions
144,122
 * @author jojo
 * Nov. 5, 2023 12:13:05 a.m.
 */
public class LastStoneWeightII {
	public int lastStoneWeightII(int[] stones) {
        // return topDown(stones);
        return bottomUp(stones);
    }
    
    private int bottomUp(int[] stones){
        int sum = 0;
        for(int s : stones){
            sum += s;
        }
        
        int target = sum/ 2;
        
        // states 
        boolean[][] dp = new boolean[stones.length + 1][target + 1];
        
        // base case: 0 sum can aways be reached.
        for(int i=0; i<stones.length; i++){
            dp[i][0] = true;
        }
        
        int s2 = 0;
        for(int i=1; i<=stones.length; i++){
            for(int j=1; j <= target; j++){
                boolean excludingCurrentStone = dp[i-1][j];
                boolean includingCurrentStone = j >= stones[i-1] && dp[i-1][j - stones[i-1]];
                
                if(excludingCurrentStone || includingCurrentStone){
                    dp[i][j] = true;
                    
                    s2 = Math.max(s2, j);
                }
            }
        }
        
        // this is because  we need to split the array as close to half as possible. 
        // S1 and S2 are the two parts.
        // sum = s1 + s2; 
        // s1 = sum - s2;
        // diff = s1 - s2; 
        // diff = sum - s2 - s2 = sum - (2 * s2)
        return sum - (2 * s2);
    }
    
    private int topDown(int[] stones){
        int sum = 0;
        for(int s : stones){
            sum += s;
        }
        
        int target = sum / 2;
        Integer[][] memo = new Integer[stones.length][target + 1];
        
        int s2 = dp(stones, 0, 0, target, memo);
        
        // this is because  we need to split the array as close to half as possible. 
        // S1 and S2 are the two parts.
        // sum = s1 + s2; 
        // s1 = sum - s2;
        // diff = s1 - s2; 
        // diff = sum - s2 - s2 = sum - (2 * s2)
        return sum - (2 * s2);
    }
    
    private int dp(int[] stones, int idx, int runningSum, int target, Integer[][] memo){
        if(idx == stones.length){
            return 0;
        }
        
        if(memo[idx][runningSum] != null){
            return memo[idx][runningSum];
        }
        
        int result = 0;
        if(runningSum + stones[idx] <= target){
            int including = stones[idx] + dp(stones, idx + 1, runningSum + stones[idx], target, memo);
            int excluding = dp(stones, idx + 1, runningSum, target, memo);
            
            result = Math.max(including, excluding);
        }
        else{
            // the current stone value + running sum exceeds the target
            result = dp(stones, idx + 1, runningSum, target, memo);
        }
        
        return memo[idx][runningSum] = result;
    }
}
