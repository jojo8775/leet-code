package interview.leetcode.practice.round4.arrays;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            count += map.getOrDefault((sum - k), 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new SubarraySumEqualsK().subarraySum(new int[] { 1, 1, 1 }, 2));
    }
}
