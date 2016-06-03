package interview.leetcode.prob;

/**
 * Given an unsorted array of integers, find the length of longest increasing
 * subsequence.
 * 
 * For example, Given [10, 9, 2, 5, 3, 7, 101, 18], The longest increasing
 * subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may
 * be more than one LIS combination, it is only necessary for you to return the
 * length.
 * 
 * Your algorithm should run in O(n2) complexity.
 * 
 * Follow up: Could you improve it to O(n log n) time complexity?
 * 
 * @author jojo
 *
 */
public class LongestIncreasingSubsequence
{
    public int lengthOfLIS(int[] nums) {
        if(nums.length < 2){
            return nums.length;
        }
        
        int[] grid = new int[nums.length];
        grid[0] = 1;
        
        int max = 1;
        
        for(int i=1; i<nums.length; i++){
            grid[i] = 1;
            for(int j=0; j<i; j++){
                if(nums[j] < nums[i] && grid[i] < grid[j] + 1){
                    grid[i] = grid[j] + 1;
                    max = Math.max(grid[i], max);
                }
            }
        }
        
        return max;
    }
}
