package interview.leetcode.prob.paid;

/**
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit
 * integer which has exactly the same digits existing in the integer n and is
 * greater in value than n. If no such positive 32-bit integer exists, you need
 * to return -1.
 * 
 * Example 1: Input: 12 Output: 21 Example 2: Input: 21 Output: -1
 * 
 * @author jojo
 *
 */
public class NextGreaterElementIII {
    public int nextGreaterElement(int n) {
        String str1 = String.valueOf(n);
        String str2 = String.valueOf(Integer.MAX_VALUE);

        if (str1.length() > str2.length() && str1.compareTo(str2) > 0) {
            return -1;
        }

        char[] cArr = str1.toCharArray();

        int idx1 = cArr.length - 1;
        while (idx1 > 0) {
            if (cArr[idx1] > cArr[idx1 - 1]) {
                idx1--;
                break;
            }

            idx1--;
        }

        int idx2 = cArr.length - 1;
        while (idx2 > idx1) {
            if (cArr[idx2] > cArr[idx1]) {
                break;
            }

            idx2--;
        }

        if (idx1 == 0 && idx2 == 0) {
            return -1;
        }

        char temp = cArr[idx1];
        cArr[idx1] = cArr[idx2];
        cArr[idx2] = temp;

        reverse(cArr, idx1 + 1);

        long val = Long.valueOf(String.valueOf(cArr));
        if (val > Integer.MAX_VALUE) {
            return -1;
        }

        return (int) val;
    }

    private void reverse(char[] cArr, int idx) {
        int beg = idx, end = cArr.length - 1;
        while (beg < end) {
            char ch = cArr[beg];
            cArr[beg++] = cArr[end];
            cArr[end--] = ch;
        }
    }
}
