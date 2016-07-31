package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?


 * @author jojo
 *
 */
public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        //dynamic programming bottom up approach
        
        Arrays.sort(nums);
        
        int[] dp = new int[target + 1];
        //since there is only one way to represent 0
        dp[0] = 1;
        
        for(int i=1; i<=target; i++){
            
            //for each coin
            for(int j=0; j<nums.length; j++){
                if(i - nums[j] >=0 ){
                    dp[i] += dp[i-nums[j]];
                }
                else{
                    break;
                }
            }
        }
        
        return dp[target];
    }
}
