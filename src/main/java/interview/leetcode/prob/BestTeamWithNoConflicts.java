package interview.leetcode.prob;

import java.util.Arrays;

/**
 * You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest overall score. The score of the team is the sum of scores of all the players in the team.

However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a strictly higher score than an older player. A conflict does not occur between players of the same age.

Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player, respectively, return the highest overall score of all possible basketball teams.

 

Example 1:

Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
Output: 34
Explanation: You can choose all the players.
Example 2:

Input: scores = [4,5,6,5], ages = [2,1,2,1]
Output: 16
Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
Example 3:

Input: scores = [1,2,3,5], ages = [8,9,10,1]
Output: 6
Explanation: It is best to choose the first 3 players. 
 

Constraints:

1 <= scores.length, ages.length <= 1000
scores.length == ages.length
1 <= scores[i] <= 106
1 <= ages[i] <= 1000
Accepted
77,892
Submissions
154,061
 * @author jojo
 * Nov. 3, 2023 9:46:01 p.m.
 */
public class BestTeamWithNoConflicts {
	public int bestTeamScore(int[] scores, int[] ages) {
        // return topDown(scores, ages);
        return bottomUp(scores, ages);
    }
    
    private int bottomUp(int[] scores, int[] ages){
        int len = ages.length;
        int[][] agePair = new int[len][2];
        
        for(int i=0; i<len; i++){
            agePair[i] = new int[]{ages[i], scores[i]};
        }
        
        Arrays.sort(agePair, ((a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
        
        // states
        int[] dp = new int[len];
        
        int result = 0;
        
        for(int i=0; i<len; i++){
            
            // basecase
            dp[i] = agePair[i][1];
            
            for(int j=0; j<i; j++){
                // relation
                if(agePair[j][1] <= agePair[i][1]){
                    dp[i] = Math.max(dp[i], dp[j] + agePair[i][1]);
                }
            }
            
            // result
            result = Math.max(result, dp[i]);
        }
        
        return result;
    }
    
    private int topDown(int[] scores, int[] ages){
        int len = ages.length;
        int[][] agePair = new int[len][2];
        
        for(int i=0; i<len; i++){
            agePair[i] = new int[]{ages[i], scores[i]};
        }
        
        Arrays.sort(agePair, ((a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
        
        // states
        Integer[][] memo = new Integer[len][len];
        
        return dp(agePair, -1, 0, memo);
    }
    
    private int dp(int[][] agePair, int prev, int idx, Integer[][] memo){
        // base case
        if(idx == agePair.length){
            return 0;
        }
        
        if(memo[prev + 1][idx] != null){
            return memo[prev + 1][idx];
        }
        
        // relation: if the number is a non-conflict 
        if(prev == -1 || agePair[idx][1] >= agePair[prev][1]){
            int including = agePair[idx][1] + dp(agePair, idx, idx + 1, memo);
            int excluding = dp(agePair, prev, idx + 1, memo);
            
            return memo[prev + 1][idx] = Math.max(including, excluding);
        }
        // relation: if the number is a conflict then skip
        else{
            return memo[prev + 1][idx] = dp(agePair, prev, idx + 1, memo);
        }
    }
}
