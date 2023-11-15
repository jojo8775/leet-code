package interview.leetcode.prob;

/**
 * You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.

In the ith operation (1-indexed), you will:

Choose two elements, x and y.
Receive a score of i * gcd(x, y).
Remove x and y from nums.
Return the maximum score you can receive after performing n operations.

The function gcd(x, y) is the greatest common divisor of x and y.

 

Example 1:

Input: nums = [1,2]
Output: 1
Explanation: The optimal choice of operations is:
(1 * gcd(1, 2)) = 1
Example 2:

Input: nums = [3,4,6,8]
Output: 11
Explanation: The optimal choice of operations is:
(1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
Example 3:

Input: nums = [1,2,3,4,5,6]
Output: 14
Explanation: The optimal choice of operations is:
(1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
 

Constraints:

1 <= n <= 7
nums.length == 2 * n
1 <= nums[i] <= 106
Accepted
57,105
Submissions
98,003
 * @author jojo
 * Nov. 4, 2023 3:00:01 p.m.
 */
public class MaximumScoreAfterNOperation {
	public int maxScore(int[] nums) {
        // return topDown(nums);
        return bottomUp(nums);
    }
    
    private int bottomUp(int[] nums){
        int len = 1 << nums.length; // if num.length == 4, mask will be 1000 but only 999 states will be needed.
        int fullState =  len - 1;
        
        // states
        int[] dp = new int[len];
        
        for(int curState = len-1; curState >= 0; curState--){
            
            // base case. When the final state is reached there is nothing more to compute 
            if(curState == fullState){
                continue;
            }
            
            int numOfBits = Integer.bitCount(curState);
            // bits represents pair picked. Each operation picks two elements from the array, so odd bits are invalid states.
            if(numOfBits % 2 != 0){
                continue;
            }
            
            int operation = numOfBits / 2;
            
            for(int i=0; i<nums.length; i++){
                
                if(((curState >> i) & 1) == 1){
                    continue;
                }
                
                for(int j=i+1; j<nums.length; j++){
                    if(((curState >> j) & 1) == 1){
                        continue;
                    }   
                    
                    // operation + 1 because the current state will need to pick two remaining numbers. 
                    // picking numbers results in one operation. 
                    int curScore = (operation + 1) * gcd(nums[i], nums[j]);
                    // since the next state will always be more than the current state we are 
                    // iterating from last state to the initial state. 
                    // this way when we calculate the initial state we will have later states precomputed and 
                    // the final result will be in dp[0]
                    int nextState = curState | (1 << i) | (1 << j);
                    int remainingScore = dp[nextState];
                    
                    dp[curState] = Math.max(dp[curState], curScore + remainingScore);
                }
            }
        }
        
        // since we are coming from n states to initial state. 
        return dp[0];
    }
    
    private int topDown(int[] nums){
        int len = nums.length;
        
        int mask = 1 << len;
        
        // states
        Integer[] memo = new Integer[mask];
        
        return dp(nums, 0, 0, memo);
    }
    
    private int dp(int[] nums, int mask, int operations, Integer[] memo){
        // base case
        if(operations * 2 >= nums.length){
            return 0;
        }
                
        if(memo[mask] != null){
            return memo[mask];
        }
        
        int result = 0;
        
        for(int i=0; i<nums.length; i++){
            if(((mask >> i) & 1) == 1){
                continue;
            }
            
            for(int j=i+1; j<nums.length; j++){
                if(((mask >> j) & 1) == 1){
                    continue;
                }
                
                int newMask = mask | (1 << i) | (1 << j);
                // relation 
                int ans = ((operations + 1) * gcd(nums[i], nums[j])) + dp(nums, newMask, operations + 1, memo);
                
                result = Math.max(result, ans);
            }
        }
        
        memo[mask] = result;
        
        return result;
    }
    
    private int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        
        return gcd(b, a%b);
    }
}
