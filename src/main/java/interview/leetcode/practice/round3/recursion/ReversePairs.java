package interview.leetcode.practice.round3.recursion;

public class ReversePairs {
    public int findReversePair(int[] arr) {
        return divideAndConcur(arr, 0, arr.length - 1, new int[arr.length]);
    }

    private int divideAndConcur(int[] arr, int beg, int end, int[] placeHolder) {

        if (beg > end) {
            return 0;
        }

        int mid = beg + (end - beg) / 2;
        int count = divideAndConcur(arr, beg, mid, placeHolder);
        count += divideAndConcur(arr, mid + 1, end, placeHolder);

        for (int i = beg, j = mid + 1; i <= mid; i++) {
            while (j <= end && arr[i] / 2 > arr[j]) {
                j++;
            }

            count += j - mid + 1;
        }

        sort(arr, beg, mid, end, placeHolder);
        return count;
    }

    private void sort(int[] arr, int beg, int mid, int end, int[] placeHolder) {
        for (int i = beg; i <= end; i++) {
            placeHolder[i] = arr[i];
        }

        int s1 = beg, s2 = mid + 1, idx = s1;

        while (s1 <= mid || s2 <= end) {
            if (s2 > end || (s1 <= mid && placeHolder[s1] < placeHolder[s2])) {
                arr[idx++] = placeHolder[s1++];
            } else {
                arr[idx++] = placeHolder[s2++];
            }
        }
    }
}
