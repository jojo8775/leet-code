package interview.leetcode.prob;

/**
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.

 * @author jojo
 * Aug 24, 2019 12:21:48 PM
 */
public class ShortedUnsortedContinuousSubArray {
	public int findUnsortedSubarray(int[] nums) {
        // end and beg is assigned accordingly so that for a perfectly sorted input end - beg + 1 becomes 0
        int end = -2, beg = -1, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;        
        
        // this can be done in one loop as well but this way it is more readable.
        for(int i=0; i<nums.length; i++){
            max = Math.max(max, nums[i]);
            if(nums[i] < max){
                end = i;
            }
        }
        
        for(int i=nums.length - 1; i>=0; i--){
            min = Math.min(min, nums[i]);
            if(nums[i] > min){
                beg = i;
            }
        }
        
        return end - beg + 1;
    }
}
