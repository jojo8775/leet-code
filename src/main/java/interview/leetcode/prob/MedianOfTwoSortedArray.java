package interview.leetcode.prob;

public class MedianOfTwoSortedArray {
    // using divide and conquer idea, each time find the mid of both arrays

    double findMedianSortedArrays(int nums1[], int nums2[]) {
        /* A[0, 1, 2, ..., n-1, n] */
        /* A[0, 1, 2, ..., m-1, m] */
        int m = nums1.length;
        int n = nums2.length;
        int middle = (m + n + 1) / 2;
        double median1 = (double) FindKth(nums1, 0, m - 1, nums2, 0, n - 1, middle);

        if ((m + n) % 2 == 0) {
            int middle1 = middle + 1;
            double median2 = (double) FindKth(nums1, 0, m - 1, nums2, 0, n - 1, middle1);
            median1 = (median1 + median2) / 2;
        }

        return median1;
    }

    // find the kth element int the two sorted arrays
    // let us say: A[aMid] <= B[bMid], x: mid len of a, y: mid len of b, then
    // wen can know
    //
    // (1) there will be at least (x + 1 + y) elements before bMid
    // (2) there will be at least (m - x - 1 + n - y) = m + n - (x + y +1)
    // elements after aMid
    // therefore
    // if k <= x + y + 1, find the kth element in a and b, but unconsidering
    // bMid and its suffix
    // if k > x + y + 1, find the k - (x + 1) th element in a and b, but
    // unconsidering aMid and its prefix
    int FindKth(int nums1[], int beg1, int end1, int nums2[], int beg2, int end2, int k) {
        if (beg1 > end1)
            return nums2[beg2 + k - 1];
        if (beg2 > end2)
            return nums1[beg1 + k - 1];

        int aMid = (beg1 + end1) / 2;
        int bMid = (beg2 + end2) / 2;

        if (nums1[aMid] <= nums2[bMid]) {
            if (k <= (aMid - beg1) + (bMid - beg2) + 1)
                return FindKth(nums1, beg1, end1, nums2, beg2, bMid - 1, k);
            else
                return FindKth(nums1, aMid + 1, end1, nums2, beg2, end2, k - (aMid - beg1) - 1);
        } else { // A[aMid] > B[bMid]
            if (k <= (aMid - beg1) + (bMid - beg2) + 1)
                return FindKth(nums1, beg1, aMid - 1, nums2, beg2, end2, k);
            else
                return FindKth(nums1, beg1, end1, nums2, bMid + 1, end2, k - (bMid - beg2) - 1);
        }
    }

    // public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    // int m = nums1.length;
    // int n = nums2.length;
    //
    // if ((m + n) % 2 != 0) // odd
    // return (double) findKth(nums1, nums2, (m + n) / 2, 0, m - 1, 0, n - 1);
    // else { // even
    // return (findKth(nums1, nums2, (m + n) / 2, 0, m - 1, 0, n - 1)
    // + findKth(nums1, nums2, (m + n) / 2 - 1, 0, m - 1, 0, n - 1)) * 0.5;
    // }
    // }
    //
    // private int findKth(int A[], int B[], int k, int aStart, int aEnd, int
    // bStart, int bEnd) {
    //
    // int aLen = aEnd - aStart + 1;
    // int bLen = bEnd - bStart + 1;
    //
    // // Handle special cases
    // if (aLen == 0)
    // return B[bStart + k];
    // if (bLen == 0)
    // return A[aStart + k];
    // if (k == 0)
    // return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
    //
    // // int aMid = aLen * k / (aLen + bLen); // a's middle count
    // // int bMid = k - aMid - 1; // b's middle count
    //
    // int aMid = aStart + ((aEnd - aStart) / 2); // a's middle count
    // int bMid = bStart + ((bEnd - bStart) / 2); // b's middle count
    //
    //// // make aMid and bMid to be array index
    //// aMid = aMid + aStart;
    //// bMid = bMid + bStart;
    //
    // if (A[aMid] > B[bMid]) {
    // k = k - (bMid - bStart);
    // aEnd = aMid;
    // bStart = bMid;
    // if(k>0){
    // k -= 1;
    // bStart += 1;
    // }
    // } else {
    // k = k - (aMid - aStart);
    // bEnd = bMid;
    // aStart = aMid;
    //
    // if(k > 0){
    // k -= 1;
    // aStart += 1;
    // }
    // }
    //
    // return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
    // }

    public static void main(String[] args) {
        // System.out.println(new
        // MedianOfTwoSortedArray().findMedianSortedArrays(new int[] { 1, 3 },
        // new int[] { 2 }));
        //
        // System.out.println(new
        // MedianOfTwoSortedArray().findMedianSortedArrays(new int[] { 1, 3 },
        // new int[] { 9 }));

        // System.out.println(new
        // MedianOfTwoSortedArray().findMedianSortedArrays(new int[] { 1, 2 },
        // new int[] { 3, 4 }));
        System.out.println(new MedianOfTwoSortedArray().findMedianSortedArrays(new int[] { 4 }, new int[] { 1, 2, 3 }));
    }
}
