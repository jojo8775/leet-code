package interview.leetcode.prob;

import java.util.Arrays;

/**
 * 
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

 

Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 

Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
Accepted
120,744
Submissions
263,861
 * @author jojo
 * Mar 27, 2021  8:33:47 PM
 */
public class PartisionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int total = 0;
        for(int n : nums){
            total += n;
        }
        
        if(k <= 0 || total % k != 0){
            return false;
        }
        
        // sorting to assign the biggest ones first.
        Arrays.sort(nums);
        
        return dfs(nums, nums.length - 1, new int[k], total / k);
    }
    
    private boolean dfs(int[] nums, int idx, int[] partitions, int target){
        if(idx == -1){
            // for(int i=0; i<partisions.length; i++){
            //     if(partisions[i] != target){
            //         return false;
            //     }
            // }
            
            return true;
        }
        
        for(int i=0; i<partitions.length; i++){
            
            // if both the groups have the same count then the next partision will have the same result.
            if(i > 0 && partitions[i-1] == partitions[i]){
                continue;
            }
            
            // there is no point in adding if the target exceeds for a partition.
            if(partitions[i] + nums[idx] <= target){
                partitions[i] += nums[idx];
                
                if(dfs(nums, idx - 1, partitions, target)){
                    return true;
                }
                
                partitions[i] -= nums[idx];
            }
        }
        
        return false;
    }
	
	// ---------- old code.
	
    public boolean canPartitionKSubsets_old(int[] nums, int k) {
        int sum = 0;
        for(int num:nums){
            sum += num;
        }
        
        if(k <= 0 || sum%k != 0){
            return false;
        }
        
        int[] visited = new int[nums.length];
        return canPartition(nums, visited, 0, k, 0, sum/k);
    }
    
    private boolean canPartition(int[] nums, int[] visited, int idx, int k, int curSum, int target){
        // since k is 1 based
        if(k==1){
            return true;
        }
        
        if(curSum == target ){
            return canPartition(nums, visited, 0, k-1, 0, target);
        }
        
        for(int i = idx; i<nums.length; i++){
            if(visited[i] == 0){
                visited[i] = 1;
                if(canPartition(nums, visited, i+1, k, curSum + nums[i], target)){
                    return true;
                }
                
                visited[i] = 0;
            }
        }
        return false;
    }
}
