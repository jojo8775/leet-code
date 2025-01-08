package interview.leetcode.prob;

import java.util.Stack;

/**
 * You are given an array of positive integers nums.

Return the number of 
subarrays
 of nums, where the first and the last elements of the subarray are equal to the largest element in the subarray.

 

Example 1:

Input: nums = [1,4,3,3,2]

Output: 6

Explanation:

There are 6 subarrays which have the first and the last elements equal to the largest element of the subarray:

subarray [1,4,3,3,2], with its largest element 1. The first element is 1 and the last element is also 1.
subarray [1,4,3,3,2], with its largest element 4. The first element is 4 and the last element is also 4.
subarray [1,4,3,3,2], with its largest element 3. The first element is 3 and the last element is also 3.
subarray [1,4,3,3,2], with its largest element 3. The first element is 3 and the last element is also 3.
subarray [1,4,3,3,2], with its largest element 2. The first element is 2 and the last element is also 2.
subarray [1,4,3,3,2], with its largest element 3. The first element is 3 and the last element is also 3.
Hence, we return 6.

Example 2:

Input: nums = [3,3,3]

Output: 6

Explanation:

There are 6 subarrays which have the first and the last elements equal to the largest element of the subarray:

subarray [3,3,3], with its largest element 3. The first element is 3 and the last element is also 3.
subarray [3,3,3], with its largest element 3. The first element is 3 and the last element is also 3.
subarray [3,3,3], with its largest element 3. The first element is 3 and the last element is also 3.
subarray [3,3,3], with its largest element 3. The first element is 3 and the last element is also 3.
subarray [3,3,3], with its largest element 3. The first element is 3 and the last element is also 3.
subarray [3,3,3], with its largest element 3. The first element is 3 and the last element is also 3.
Hence, we return 6.

Example 3:

Input: nums = [1]

Output: 1

Explanation:

There is a single subarray of nums which is [1], with its largest element 1. The first element is 1 and the last element is also 1.

Hence, we return 1.

 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
 * 
 * Jan 5, 2025 - 11:26:17 PM
 * Jojo 
 */
public class FindTheNumberOfSubarraysWhereBoundryElementsAreMaximum {
	public long numberOfSubarrays(int[] nums) {
        Stack<long[]> stack = new Stack<>();
        long count = 0;

        for(int i=0; i<nums.length; i++){
            while(!stack.isEmpty() && stack.peek()[0] < nums[i]){
                // droping the entry because the element staring at top[0] will never be valid
                // because the current num is more than the starting point.
                stack.pop();
            }

            if(stack.isEmpty() || stack.peek()[0] != nums[i]){
                // starting a new sub array. 
                // 0: because this is a base case and the common logic increments the count by 1
                stack.push(new long[]{nums[i], 0});
            }

            // updating the size of the current sub array.
            stack.peek()[1]++;
            count += stack.peek()[1];
        }

        return count;
    }
}
