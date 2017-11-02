package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        Integer num1 = null, num2 = null;
        int count1 = 0, count2 = 0;
        int len = nums.length, idx = 0;
        while (idx < len) {
            if (num1 == null || num1 == nums[idx]) {
                num1 = nums[idx];
                count1++;
            } else if (num2 == null || num2 == nums[idx]) {
                num2 = nums[idx];
                count2++;
            } else {
                if (--count1 <= 0) {
                    count1 = 0;
                    num1 = null;
                }

                if (--count2 <= 0) {
                    count2 = 0;
                    num2 = null;
                }

                if (num1 == null) {
                    num1 = num2;
                    count1 = count2;

                    num2 = null;
                    count2 = 0;
                }
            }

            idx++;
        }

        count1 = 0;
        count2 = 0;
        for (int n : nums) {
            if (num1 != null && num1 == n) {
                count1++;
            } else if (num2 != null && num2 == n) {
                count2++;
            }
        }

        // System.out.println("Count 1: " + count1 + " Num1 : " + num1);
        // System.out.println("Count 2: " + count2 + " Num2 : " + num2);

        int cap = len / 3;
        List<Integer> result = new ArrayList<>();
        if (count1 > cap) {
            result.add(num1);
        }
        if (count2 > cap) {
            result.add(num2);
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = new MajorityElementII().majorityElement(new int[] { 1, 2, 2, 3, 2, 1, 1, 3 });
        for (int n : result) {
            System.out.println(n);
        }
    }
}
