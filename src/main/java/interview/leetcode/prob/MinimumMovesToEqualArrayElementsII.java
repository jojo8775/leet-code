package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 * @author jojo
 *
 */
public class MinimumMovesToEqualArrayElementsII {
    public int minMoves2(int[] nums) {
        // sorting is required as need to find the median
        Arrays.sort(nums);
        
        int len = nums.length;
        int mid = (len - 1)/2;
        int median = nums[mid];

        if(len % 2 == 0){
            median =  (median + nums[mid + 1])/2;
        }
        
        int moves = 0;
        
        // abs is required as moves can be + / - 
        for(int n : nums){
            moves += Math.abs(n - median);
        }
        
        return moves;
    }
}
