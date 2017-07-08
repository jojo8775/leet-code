package interview.leetcode.prob;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
 * @author jojo
 *
 */
public class WiggleSortII {
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }

        int med = findKthLargest(nums, (len + 1) / 2, 0, len - 1);
        int left = 1; // (2) elements larger than the 'median' are put into the
                   // first odd slots
        int right = (len - 1) / 2 * 2; // (1) elements smaller than the 'median' are
                                   // put into the last even slots
        int pivot = right;

        for (int k = 0; k < len; k++) {
            if (nums[pivot] > med) {
                swap(nums, pivot, left);
                left += 2;
            } else if (nums[pivot] < med) {
                swap(nums, pivot, right);
                right = right - 2;
                pivot = pivot - 2;
                if (pivot < 0)
                    pivot = len / 2 * 2 - 1;
            } else {
                pivot = pivot - 2;
                if (pivot < 0)
                    pivot = len / 2 * 2 - 1;
            }
        }
    }

    void swap(int[] nums, int i, int j) {
        if (i < 0 || i >= nums.length || j < 0 || j >= nums.length)
            return;
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public int findKthLargest(int[] nums, int index, int beg, int end) {

        int pivot = findRandom(beg, end);
        swap(nums, pivot, end);
        pivot = end;

        int left = beg, right = beg;
        while (right < end) {
            if (nums[right] < nums[pivot]) {
                swap(nums, left++, right++);
            } else {
                right++;
            }
        }

        swap(nums, pivot, left);

        if (left == index) {
            return nums[left];
        } else if (left < index) {
            return findKthLargest(nums, index, left + 1, end);
        }

        return findKthLargest(nums, index, beg, left - 1);
    }

    private int findRandom(int beg, int end) {
        return beg + (int) (Math.random() * (end - beg + 1));
    }
}
