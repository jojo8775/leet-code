package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        int beg = 0, end = nums.length - 1;
        Set<List<Integer>> result = new HashSet<List<Integer>>();

        while ((beg + 1) < end) {
            int sum = nums[beg] + nums[end];
            int assumedIndex = binarySearch(beg + 1, end - 1, nums, -sum);

            if (sum + nums[assumedIndex] == 0) {
                result.add(Arrays.asList(nums[beg], nums[assumedIndex], nums[end]));
                beg++;
            } else if (sum + nums[assumedIndex] > 0) {
                end--;
            } else {
                beg++;
            }
        }

        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        resultList.addAll(result);
        return resultList;
    }

    private int binarySearch(int beg, int end, int[] nums, int target) {
        while (beg < end) {
            int mid = beg + (end - beg) / 2;

            if (nums[mid] < target) {
                beg = mid + 1;
            } else {
                end = mid;
            }
        }

        return beg;
    }

    public static void main(String[] args) {
//        List<List<Integer>> result = new ThreeSum().threeSum(new int[] { -1, 0, 1, 2, -1, -4 });
        List<List<Integer>> result = new ThreeSum().threeSum(new int[] { -1, 0, 1, 0});

        for (List<Integer> ll : result) {
            for (int n : ll) {
                System.out.print(n + ",");
            }

            System.out.println();
        }
    }
}
