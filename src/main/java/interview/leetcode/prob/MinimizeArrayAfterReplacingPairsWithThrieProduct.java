package interview.leetcode.prob;

/**
 * Given an integer array nums and an integer k, you can perform the following operation on the array any number of times:

Select two adjacent elements of the array like x and y, such that x * y <= k, and replace both of them with a single element with value x * y (e.g. in one operation the array [1, 2, 2, 3] with k = 5 can become [1, 4, 3] or [2, 2, 3], but can't become [1, 2, 6]).
Return the minimum possible length of nums after any number of operations.

 

Example 1:

Input: nums = [2,3,3,7,3,5], k = 20
Output: 3
Explanation: We perform these operations:
1. [2,3,3,7,3,5] -> [6,3,7,3,5]
2. [6,3,7,3,5] -> [18,7,3,5]
3. [18,7,3,5] -> [18,7,15]
It can be shown that 3 is the minimum length possible to achieve with the given operation.
Example 2:

Input: nums = [3,3,3,3], k = 6
Output: 4
Explanation: We can't perform any operations since the product of every two adjacent elements is greater than 6.
Hence, the answer is 4.
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 109
1 <= k <= 109
Seen this question in a real interview before?
1/5
Yes
No
Accepted
1.3K
Submissions
3.1K
Acceptance Rate
40.8%
 * 
 * Jan 6, 2025 - 1:48:54 AM
 * Jojo 
 */
public class MinimizeArrayAfterReplacingPairsWithThrieProduct {
	public int minArrayLength(int[] nums, int k) {
        int len = nums.length + 1; // adding 1 here, otherwise I have to check if first element is 0 separately.
        long prev = 1L;

        for(int i=0; i<nums.length; i++){
            if(nums[i] == 0){
                len = 1;
                break;
            }

            if(prev * nums[i] <= k){
                prev = prev * (long)nums[i];
                len--;
            }
            else{
                // reseting the prev.
                prev = (long)nums[i];
            }
        }

        // len == nums.length + 1 when input nums.length == 1
        return len == nums.length + 1 ? len - 1 : len;
    }
}
