package interview.leetcode.practice.round2;

public class SearchInRotatedArray {
    public int search(int[] nums, int target) {
        int beg = 0, end = nums.length - 1;

        while (beg <= end) {
            int mid = beg + (end - beg) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] > nums[beg]) {
                if (target >= nums[beg] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    beg = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    beg = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new SearchInRotatedArray().search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 5));
    }
}
