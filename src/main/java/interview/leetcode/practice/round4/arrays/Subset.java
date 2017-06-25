package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subset {
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<Integer>());

        int prevLen = 0;
        for (int i = 0; i < nums.length; i++) {
            int len = result.size();
            for (int j = (i > 0 && nums[i] == nums[i - 1]) ? prevLen : 0; j < len; j++) {
                List<Integer> list = new ArrayList<>(result.get(j));
                list.add(nums[i]);
                result.add(list);
            }

            prevLen = result.size();
        }

        return result;
    }
}
