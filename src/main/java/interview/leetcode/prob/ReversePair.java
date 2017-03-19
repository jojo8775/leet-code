package interview.leetcode.prob;

/**
 * Given an array nums, we call (i, j) an important reverse pair if i < j and
 * nums[i] > 2*nums[j].
 * 
 * You need to return the number of important reverse pairs in the given array.
 * 
 * Example1:
 * 
 * Input: [1,3,2,3,1] Output: 2 Example2:
 * 
 * Input: [2,4,3,5,1] Output: 3 Note: The length of the given array will not
 * exceed 50,000. All the numbers in the input array are in the range of 32-bit
 * integer.
 * 
 * @author jojo
 *
 */
public class ReversePair {
    public int reversePairs(int[] nums) {
        // think in terms of divide and concure
        return mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
    }

    // this divides the array into smaller sub array
    private int mergeSort(int[] nums, int beg, int end, int[] helper) {
        // if beg is >= end then there cannot be any result so returning 0
        if (beg >= end) {
            return 0;
        }

        int mid = beg + (end - beg) / 2;
        // recursive call to break it to smaller sub array
        int count = mergeSort(nums, beg, mid, helper) + mergeSort(nums, mid + 1, end, helper);

        // count number of times nums[i] > 2 * nums[j] condition is satisfied
        for (int i = beg, j = mid + 1; i <= mid; i++) {
            // dividing instead of multiply to avoid overflow
            while (j <= end && nums[i] / 2.0 > nums[j]) {
                j++;
            }

            // this will make sure the count is cumilative
            count += j - (mid + 1);
        }

        // sorting each sub array
        sort(nums, beg, mid, end, helper);
        return count;
    }

    private void sort(int[] nums, int beg, int mid, int end, int[] helper) {
        // p1 is the start of left sub array and p2 is the start for right
        // subarray
        int p1 = beg, p2 = mid + 1, idx = beg;

        // using the helper array as placeholder
        for (int i = beg; i <= end; i++) {
            helper[i] = nums[i];
        }

        while (p1 <= mid || p2 <= end) {
            if (p1 > mid || p2 <= end && helper[p2] <= helper[p1]) {
                nums[idx++] = helper[p2++];
            } else {
                nums[idx++] = helper[p1++];
            }
        }
    }
}
