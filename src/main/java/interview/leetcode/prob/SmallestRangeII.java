package interview.leetcode.prob;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * You are given an integer array nums and an integer k.

For each index i where 0 <= i < nums.length, change nums[i] to be either nums[i] + k or nums[i] - k.

The score of nums is the difference between the maximum and minimum elements in nums.

Return the minimum score of nums after changing the values at each index.

 

Example 1:

Input: nums = [1], k = 0
Output: 0
Explanation: The score is max(nums) - min(nums) = 1 - 1 = 0.
Example 2:

Input: nums = [0,10], k = 2
Output: 6
Explanation: Change nums to be [2, 8]. The score is max(nums) - min(nums) = 8 - 2 = 6.
Example 3:

Input: nums = [1,3,6], k = 3
Output: 3
Explanation: Change nums to be [4, 6, 3]. The score is max(nums) - min(nums) = 6 - 3 = 3.
 

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 104
0 <= k <= 104
Seen this question in a real interview before?
1/5
Yes
No
Accepted
58.7K
Submissions
159.7K
Acceptance Rate
36.7%
 * 
 * Jan 6, 2025 - 1:04:07 AM
 * Jojo 
 */
public class SmallestRangeII {
	public int smallestRangeII_1(int[] nums, int k) {
        Arrays.sort(nums);

        int first = nums[0], last = nums[nums.length - 1];
        int result = last - first;

        for(int i=0; i<nums.length - 1; i++){
            int a = nums[i], b = nums[i + 1];

            int max = Math.max(a + k, last - k);
            int min = Math.min(b - k, first + k);

            result = Math.min(result, max - min);
        }

        return result;
    }

    public int smallestRangeII(int[] nums, int k) 
    {
        int len = nums.length;
        Arrays.sort(nums);
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++)
            result = Math.min(result, getResult(nums, k, i ,len));
        return result;
    }
    
    private int getResult(int[] nums, int k, int i, int len) 
    {
        if (i == 0)
            return nums[len - 1] - nums[0];
        int max = Math.max(nums[i - 1] + k, nums[len - 1] - k);
        int min = Math.min(nums[0] + k, nums[i] - k);
        return max - min;
    }


    public int smallestRangeII_TLE(int[] nums, int k) {
        return dfs(nums, 0, new TreeMap<Integer, Integer>(), k);
    }

    private int dfs(int[] nums, int idx, TreeMap<Integer, Integer> tMap, int k){
        if(idx == nums.length){
            return tMap.lastKey() - tMap.firstKey();
        }

        int key = nums[idx] + k;
        tMap.put(key, tMap.getOrDefault(key, 0) + 1);
        int v1 = dfs(nums, idx + 1, tMap, k);

        tMap.put(key, tMap.get(key) - 1);
        if(tMap.get(key) == 0){
            tMap.remove(key);
        }

        key = nums[idx] - k;
        tMap.put(key, tMap.getOrDefault(key, 0) + 1);
        int v2 = dfs(nums, idx + 1, tMap, k);

        tMap.put(key, tMap.get(key) - 1);
        if(tMap.get(key) == 0){
            tMap.remove(key);
        }

        return Math.min(v1, v2);
    }
}
