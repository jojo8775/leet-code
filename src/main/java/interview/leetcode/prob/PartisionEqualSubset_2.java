package interview.leetcode.prob;

/**
 * Practice.
 * @author jojo
 * Oct 29, 2023 11:54:25 AM
 */
public class PartisionEqualSubset_2 {
	public boolean canPartition(int[] nums) {
        //return canPartition_dfs(nums);
        //return canPartition_topdown(nums);
        // return canPartition_bottomUp(nums);
        return canPartition_bottomUp_adv(nums);
    }
    
    private boolean canPartition_dfs(int[] nums) {
        int len = nums.length, total = 0;
        
        for(int n : nums){
            total += n;
        }
        
        if(total % 2 != 0){
            return false;
        }
        
        total /= 2;
        
        Boolean[][] memo = new Boolean[len][total + 1];
        
        return dfs(nums, len - 1, total, 0, memo);
    }
    
    private boolean dfs(int[] nums, int idx, int total, int sofar, Boolean[][] memo){
        // System.out.println("sofar:" + sofar + "   idx:" + idx + "  total:" + total);
        
        if(sofar == total){
            return true;
        }
        
        if(idx < 0 || sofar > total){
            return false;
        }
        
        if(memo[idx][sofar] != null){
            return memo[idx][sofar];
        }
        
        boolean ans = dfs(nums, idx - 1, total, sofar + nums[idx], memo);
        if(!ans){
            ans = dfs(nums, idx - 1, total, sofar, memo);
        }
        
        
        memo[idx][sofar] = ans;
        
        // System.out.println("sofar:" + sofar + "   idx:" + idx);
        
        return ans;
    }
    
    private boolean canPartition_topdown(int[] nums) {
        int sum = 0;

        for(int n : nums){
            sum += n;
        }

        if(sum % 2 != 0){
            return false;
        }

        int subsetSum = sum/2;

        Boolean[][] memo = new Boolean[nums.length + 1][subsetSum + 1];

        return dp(nums, nums.length - 1, subsetSum, memo);
    }

    private boolean dp(int[] nums, int n, int subsetSum, Boolean[][] memo){
        if(n < 0 || subsetSum < 0){
            return false;
        }

        if(subsetSum == 0){
            return true;
        }

        if(memo[n][subsetSum] != null){
            return memo[n][subsetSum];
        }

        boolean val = dp(nums, n-1, subsetSum, memo) || dp(nums, n-1, subsetSum - nums[n], memo);

        memo[n][subsetSum] = val;
        return val;
    }
    
    private boolean canPartition_bottomUp(int[] nums){
        int len = nums.length, total = 0;        
        
        for(int n : nums){
            total += n;
        }
        
        if(total%2 != 0){
            return false;
        }
        
        int subTotal = total/2;
        
        boolean[][] dp = new boolean[len+1][subTotal + 1];
        dp[0][0] = true;
        
        for(int i=1; i<=len; i++){
            int cur = nums[i-1];
            
            for(int j=1; j<=subTotal; j++){
                if(j < cur){
                    dp[i][j] = dp[i-1][j];
                }
                else{
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-cur];
                }
            }
        }
        
        return dp[len][subTotal];
    }
    
    private boolean canPartition_bottomUp_adv(int[] nums){
        int len = nums.length, total = 0;        
        
        for(int n : nums){
            total += n;
        }
        
        if(total%2 != 0){
            return false;
        }
        
        int subTotal = total/2;
        
        boolean[] dp = new boolean[subTotal + 1];
        dp[0] = true;
        
        // this is same as the frog jump / can reach end problem
        for(int n : nums){
            for(int i=subTotal; i>=n; i--){
                if(dp[i-n]){
                    dp[i] = true;
                }
            }
        }
        
        return dp[subTotal];
    }
}
