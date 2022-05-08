package interview.leetcode.practice.round4.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

 

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2
 

Constraints:

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
Accepted
771,866
Submissions
1,744,777
 * @author jojo
 * May 7, 2022 11:47:17 PM
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
    	Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0, 1);
        for (int i = 0; i<nums.length; i++) {
            sum+=nums[i];
            if (map.containsKey(sum-k)) {
                count+=map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new SubarraySumEqualsK().subarraySum(new int[] { 1, 1, 1 }, 2));
    }
}
