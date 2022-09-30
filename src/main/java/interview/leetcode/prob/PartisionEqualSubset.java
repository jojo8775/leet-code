package interview.leetcode.prob;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
 * @author jojo
 *
 */
public class PartisionEqualSubset {
    // reference taken from http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
    
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int n : nums){
            sum += n;
        }
        
        // if sum cannot be divided into two then it cannot be split equally
        if(sum % 2 != 0){
            return false;
        }
        
        // 0 to sum/2 in columns and empty subset to nth subset of input (nums) in rows
        boolean[][] grid = new boolean[(sum/2) + 1][nums.length + 1];
        
        // there will always be a subset with zero sum so marking the first column as true
        Arrays.fill(grid[0], true);
        
        // for each sum
        for(int i=1; i<grid.length; i++){
            // for each sub set
            for(int j=1; j<grid[0].length; j++){
                // if previous subset (j-1) contains the current sum (i) then current subset (j) should also have it
                grid[i][j] = grid[i][j-1];
                
                // if current sum (i) is greater than equal to current input entry (j)
                if(i >= nums[j-1]){
                    // then previous sum status or previous subset status should be considered
                    grid[i][j] = grid[i - nums[j-1]][j-1] || grid[i][j];
                }
            }
        }
        
        return grid[sum/2][nums.length];
    }
    
    public boolean canPartition_topdown(int[] nums) {
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
    
    public boolean canPartition_rec(int[] nums) {
        int sum = 0;
        for(int n : nums){
            sum += n;
        }
        
        if(sum%2 != 0){
            return false;
        }
        
        return canPartition(nums, 0, 0, sum/2, new HashSet<String>());
    }
    
    private boolean canPartition(int[] nums, int idx, int sofar, int target, Set<String> failedSelection){
        if(sofar == target){
            return true;
        }
        
        String key = sofar + "-" + "i";
        if(failedSelection.contains(key)){
            return false;
        }
        
        for(int i=idx; i<nums.length; i++){
            if(sofar + nums[i] <= target){
                if(canPartition(nums, i + 1, sofar + nums[i], target, failedSelection)){
                    return true;
                }
            }
        }
        
        failedSelection.add(key);
        return false;
    }
}
