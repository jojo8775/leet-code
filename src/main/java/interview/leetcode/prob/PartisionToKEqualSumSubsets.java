package interview.leetcode.prob;

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
