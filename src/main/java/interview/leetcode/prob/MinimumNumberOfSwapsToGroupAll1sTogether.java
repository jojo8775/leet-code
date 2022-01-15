package interview.leetcode.prob;

import java.util.Arrays;

/**
 * A swap is defined as taking two distinct positions in an array and swapping the values in them.

A circular array is defined as an array where we consider the first element and the last element to be adjacent.

Given a binary circular array nums, return the minimum number of swaps required to group all 1's present in the array together at any location.

 

Example 1:

Input: nums = [0,1,0,1,1,0,0]
Output: 1
Explanation: Here are a few of the ways to group all the 1's together:
[0,0,1,1,1,0,0] using 1 swap.
[0,1,1,1,0,0,0] using 1 swap.
[1,1,0,0,0,0,1] using 2 swaps (using the circular property of the array).
There is no way to group all 1's together with 0 swaps.
Thus, the minimum number of swaps required is 1.
Example 2:

Input: nums = [0,1,1,1,0,0,1,1,0]
Output: 2
Explanation: Here are a few of the ways to group all the 1's together:
[1,1,1,0,0,0,0,1,1] using 2 swaps (using the circular property of the array).
[1,1,1,1,1,0,0,0,0] using 2 swaps.
There is no way to group all 1's together with 0 or 1 swaps.
Thus, the minimum number of swaps required is 2.
Example 3:

Input: nums = [1,1,0,0,1]
Output: 0
Explanation: All the 1's are already grouped together due to the circular property of the array.
Thus, the minimum number of swaps required is 0.
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
Accepted
6,340
Submissions
15,337
 * @author jojo
 * Jan 9, 2022 10:46:06 PM
 */
public class MinimumNumberOfSwapsToGroupAll1sTogether {
    public int minSwaps(int[] nums) {
        int ones = Arrays.stream(nums).sum(), len = nums.length, swaps = nums.length;
        
        if(ones == len){
            return 0;
        }
        
        for (int i = 0, j = 0, sum = 0; i < len; i++) {
            while (j - i < ones){
                sum += nums[j++ % len]; // since this is a circular array
            }
            
            // think of it as sliding window, in a span of 1s if there is a whole (0s) we need to swap them 
            swaps = Math.min(swaps, ones - sum);
            
            // preserving the previous sum and leveraing on the sliding window concept.
            sum -= nums[i];
        }
        
        return swaps;
    }
}
