package interview.leetcode.practice;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }

        int len = nums.length - 1, idx1 = len, idx2 = len;

        // finding first num[i-1] < num[i] from left to right
        while (idx1 > 0) {
            if (nums[idx1 - 1] < nums[idx1]) {
                idx1--;
                break;
            }

            idx1--;
        }

        System.out.println("idx1 = " + idx1);

        // finding first num[idx1] < num[idx2] from left to right
        while (idx2 > idx1) {
            if (nums[idx2] > nums[idx1]) {
                break;
            }

            idx2--;
        }

        // already the max number
        if (idx1 == 0 && idx2 == 0) {
            reverse(nums, 0);
            return;
        }

        // swap idx1 and idx2
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;

        // to make sure the updated number is just next highest number
        reverse(nums, idx1 + 1);
    }

    private void reverse(int[] arr, int idx) {
        int len = arr.length - 1;
        while (idx < len) {
            int temp = arr[idx];
            arr[idx++] = arr[len];
            arr[len--] = temp;
        }
    }
}
