package interview.leetcode.practice.round3.array;

public class ReverseWord {
    public String reverse(String str) {
        char[] cArr = str.toCharArray();
        int beg = 0, end = 0, len = cArr.length;

        reverse(cArr, beg, len - 1);

        while (beg < len) {
            beg = getNonSpaceIdx(cArr, beg);
            end = getSpaceIdx(cArr, beg);
            reverse(cArr, beg, end - 1);
            beg = end;
        }

        return String.valueOf(cArr);
    }

    private void reverse(char[] cArr, int beg, int end) {
        while (beg < end) {
            char temp = cArr[beg];
            cArr[beg++] = cArr[end];
            cArr[end--] = temp;
        }
    }

    private int getNonSpaceIdx(char[] cArr, int beg) {
        int len = cArr.length;
        while (beg < len && cArr[beg] == ' ') {
            beg++;
        }

        return beg;
    }

    private int getSpaceIdx(char[] cArr, int beg) {
        int len = cArr.length;
        while (beg < len && cArr[beg] != ' ') {
            beg++;
        }

        return beg;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseWord().reverse("the sky is blue."));
    }
}
