package interview.leetcode.prob;

import java.util.Arrays;

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
}
