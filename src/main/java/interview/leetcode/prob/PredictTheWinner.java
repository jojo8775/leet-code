package interview.leetcode.prob;

/**
 * Given an array of scores that are non-negative integers. Player 1 picks one
 * of the numbers from either end of the array followed by the player 2 and then
 * player 1 and so on. Each time a player picks a number, that number will not
 * be available for the next player. This continues until all the scores have
 * been chosen. The player with the maximum score wins.
 * 
 * Given an array of scores, predict whether player 1 is the winner. You can
 * assume each player plays to maximize his score.
 * 
 * Example 1: Input: [1, 5, 2] Output: False Explanation: Initially, player 1
 * can choose between 1 and 2. If he chooses 2 (or 1), then player 2 can choose
 * from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1
 * (or 2). So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. Hence,
 * player 1 will never be the winner and you need to return False. Example 2:
 * Input: [1, 5, 233, 7] Output: True Explanation: Player 1 first chooses 1.
 * Then player 2 have to choose between 5 and 7. No matter which number player 2
 * choose, player 1 can choose 233. Finally, player 1 has more score (234) than
 * player 2 (12), so you need to return True representing player1 can win. Note:
 * 1 <= length of the array <= 20. Any scores in the given array are
 * non-negative integers and will not exceed 10,000,000. If the scores of both
 * players are equal, then player 1 is still the winner.
 * 
 * @author jojo Mar 12, 20171:58:08 AM
 */
public class PredictTheWinner {
    public boolean PredictTheWinner_1int[] nums) {
        int len = nums.length;
        // adding a cache for memorization
        // >= 0 since the result will be 0 when there is a draw
        return helper(nums, 0, len - 1, new Integer[len][len]) >= 0;
    }

    private int helper(int[] nums, int beg, int end, Integer[][] cache) {
        if (cache[beg][end] == null) {
            cache[beg][end] = beg == end ? nums[end] :
            // substracting the return of helper as points earned by oponent
            // should be less the first player
                    Math.max(nums[beg] - helper(nums, beg + 1, end, cache),
                            nums[end] - helper(nums, beg, end - 1, cache));
        }

        return cache[beg][end];
    }
    
    public boolean predictTheWinner(int[] nums) {
        return dp(nums, 0, nums.length - 1, new Integer[nums.length][nums.length]) >= 0;
    }

    private int dp(int[] nums, int leftIdx, int rightIdx, Integer[][] memo){
        if(leftIdx == rightIdx){
            return nums[leftIdx];
        }

        if(memo[leftIdx][rightIdx] != null){
            return memo[leftIdx][rightIdx];
        }

        int bytakingLeft = nums[leftIdx] - dp(nums, leftIdx + 1, rightIdx, memo);
        int byTakingRight = nums[rightIdx] - dp(nums, leftIdx, rightIdx - 1, memo);

        return memo[leftIdx][rightIdx] = Math.max(bytakingLeft, byTakingRight);
    }
}
