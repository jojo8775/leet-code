package interview.leetcode.practice;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    public boolean findDuplicate(int[] nums, int k) {
        if (k < 1) {
            return false;
        }

        Set<Integer> set = new HashSet<Integer>();

        int beg = 0, end = Math.min(k, nums.length);
        while (beg < end) {
            if (!set.add(nums[beg])) {
                return true;
            }

            beg++;
        }

        end = nums.length;
        while (beg < end) {
            set.remove(nums[beg - 1]);
            if (!set.add(nums[beg])) {
                return true;
            }

            beg++;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new ContainsDuplicate().findDuplicate(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 4, 2 }, 23));
    }
}
