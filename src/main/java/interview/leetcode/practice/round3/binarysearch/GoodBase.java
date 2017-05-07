package interview.leetcode.practice.round3.binarysearch;

public class GoodBase {
    public long findGoodBase(long num) {
        int numOfBits = 64;
        for (int i = numOfBits; i > 1; i--) {
            long val = 1 << i;
            if (val < num) {
                long k = findK(num, i);

                if (k != -1) {
                    return k;
                }
            }
        }

        return num - 1;
    }

    private long findK(long num, long bits) {
        long beg = 1, end = (int) (Math.pow(num, 1.0 / bits) + 1);

        while (beg < end) {
            long mid = beg + (end - beg) / 2;
            long cur = 1, sum = 0;

            for (int i = 0; i <= bits; i++) {
                sum += cur;
                cur *= mid;
            }

            if (sum == num) {
                return mid;
            }

            if (sum < num) {
                beg = mid + 1;
            } else {
                end = mid;
            }
        }

        return -1;
    }
    
    public static void main(String[] args){
        System.out.println(new GoodBase().findGoodBase(13));
    }
}
