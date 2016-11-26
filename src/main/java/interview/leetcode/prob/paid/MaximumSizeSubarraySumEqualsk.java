package interview.leetcode.prob.paid;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums and a target value k, find the maximum length of a
 * subarray that sums to k. If there isn't one, return 0 instead.
 * 
 * Note: The sum of the entire nums array is guaranteed to fit within the 32-bit
 * signed integer range.
 * 
 * Example 1: Given nums = [1, -1, 5, -2, 3], k = 3, return 4. (because the
 * subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * 
 * Example 2: Given nums = [-2, -1, 2, 1], k = 1, return 2. (because the
 * subarray [-1, 2] sums to 1 and is the longest)
 * 
 * Follow Up: Can you do it in O(n) time?
 * 
 * @author jojo
 *
 */
public class MaximumSizeSubarraySumEqualsk {
	public static void main(String[] args) {
		int result = new MaximumSizeSubarraySumEqualsk().maxSubArrayLen(new int[] { -1, 1 }, 0);
		System.out.println(result);
	}

    public int maxSubArrayLen(int[] nums, int k) {
        
        // used to store sum from 0 to i. Key will be the sum and value will be the current index
        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        
        // adding a default start possition
        hashMap.put(0, -1);
        
        
        int maxSubArrayLength = 0, sum = 0;
        for(int i=0; i<nums.length; i++){
            sum += nums[i];
            // since we want longest array we want to keep minimun left index
            if(!hashMap.containsKey(sum)){
                hashMap.put(sum, i);
            }
            
            // checking the left index
            if(hashMap.containsKey(sum-k)){
                maxSubArrayLength = Math.max(maxSubArrayLength, i - hashMap.get(sum - k));
            }
        }
        
        return maxSubArrayLength;
    }
}
