package interview.leetcode.prob.paid;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array
 * if you can flip at most one 0.
 * 
 * Example 1: Input: [1,0,1,1,0] Output: 4 Explanation: Flip the first zero will
 * get the the maximum number of consecutive 1s. After flipping, the maximum
 * number of consecutive 1s is 4. Note:
 * 
 * The input array will only contain 0 and 1. The length of input array is a
 * positive integer and will not exceed 10,000 Follow up: What if the input
 * numbers come in one by one as an infinite stream? In other words, you can't
 * store all numbers coming from the stream as it's too large to hold in memory.
 * Could you solve it efficiently?
 * 
 * @author jojo Mar 27, 201712:54:19 AM
 */
public class MaxConsecutiveOneII {
    public int findMaxConsecutiveOnes(int[] nums) {
        int beg = 0, end = 0, zeroCount = 0, len = nums.length, max = 0;

        while (end < len) {
            if (nums[end++] == 0) {
                zeroCount++;
            }
            while (zeroCount > 1) {
                if (nums[beg++] == 0) {
                    zeroCount--;
                }
            }

            max = Math.max(max, end - beg);
        }

        return Math.max(max, end - beg);
    }
}
