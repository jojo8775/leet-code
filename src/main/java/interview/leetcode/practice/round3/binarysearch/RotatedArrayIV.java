package interview.leetcode.practice.round3.binarysearch;

public class RotatedArrayIV {
    public boolean search(int[] nums, int target) {
        int beg = 0, end = nums.length - 1;

        while (beg <= end) {
            int mid = beg + (end - beg) / 2;

            if (nums[mid] == target) {
                return true;
            }

            if (nums[end] == nums[mid]) {
                end--;
            } else {
                if (nums[mid] < nums[end]) {
                    if (target > nums[mid] && target <= nums[end]) {
                        beg = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                } else {
                    if (target >= nums[beg] && target < nums[mid]) {
                        end = mid - 1;
                    } else {
                        beg = mid + 1;
                    }
                }
            }
        }

        return false;
    }
}
