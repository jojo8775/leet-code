package interview.leetcode.practice.round3.array;

public class MedianOfSortedArray {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;

        double val = findKthElement(nums1, 0, len1 - 1, nums2, 0, len2 - 1, (len1 + len2) / 2);

        if ((len1 + len2) % 2 == 0) {
            val += findKthElement(nums1, 0, len1 - 1, nums2, 0, len2 - 1, ((len1 + len2) / 2) - 1);
            val /= 2.0;
        }

        return val;
    }

    private double findKthElement(int[] a, int a1, int a2, int[] b, int b1, int b2, int k) {
        if (a1 > a2) {
            return b[b1 + k];
        }

        if (b1 > b2) {
            return a[a1 + k];
        }

        int mid1 = a1 + (a2 - a1) / 2;
        int mid2 = b1 + (b2 - b1) / 2;

        if (a[mid1] <= b[mid2]) {
            if ((mid1 - a1) + (mid2 - b1) >= k) {
                return findKthElement(a, a1, a2, b, b1, mid2 - 1, k);
            } else {
                return findKthElement(a, mid1 + 1, a2, b, b1, b2, k - (mid1 - a1 + 1));
            }
        } else {
            if ((mid1 - a1) + (mid2 - b1) >= k) {
                return findKthElement(a, a1, mid1 - 1, b, b1, b2, k);
            } else {
                return findKthElement(a, a1, a2, b, mid2 + 1, b2, k - (mid2 - b1 + 1));
            }
        }
    }
}
