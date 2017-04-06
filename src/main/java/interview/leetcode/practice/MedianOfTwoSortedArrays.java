package interview.leetcode.practice;

public class MedianOfTwoSortedArrays {
    public double findMedian(int[] arr1, int[] arr2) {
        int len1 = arr1.length, len2 = arr2.length;
        int v1 = findKthElement(arr1, 0, len1 - 1, arr2, 0, len2 - 1, (len1 + len2) / 2);
        if ((len1 + len2) % 2 == 0) {
            int v2 = findKthElement(arr1, 0, len1 - 1, arr2, 0, len2 - 1, (len1 + len2) / 2 + 1);
            return (v1 + v2) / 2.0;
        }

        return v1 * 1.0;
    }

    private int findKthElement(int arr1[], int aL, int aR, int[] arr2, int bL, int bR, int k) {
        if (aL > aR) {
            return arr2[bL + k - 1];
        }

        if (bL > bR) {
            return arr1[aL + k - 1];
        }

        int mid1 = aL + (aR - aL) / 2;
        int mid2 = bL + (bR - bL) / 2;

        if (arr1[mid1] <= arr2[mid2]) {
            if (k <= (mid1 - aL) + (mid2 - bL) + 1) {
                return findKthElement(arr1, aL, aR, arr2, bL, mid2 - 1, k);
            } else {
                return findKthElement(arr1, mid1 + 1, aR, arr2, bL, bR, k - (mid1 - aL + 1));
            }
        } else {
            if (k <= (mid1 - aL) + (mid2 - bL) + 1) {
                return findKthElement(arr1, aL, mid1 - 1, arr2, bL, bR, k);
            } else {
                return findKthElement(arr1, aL, aR, arr2, mid2 + 1, bR, k - (mid2 - bL + 1));
            }
        }
    }
}
