package interview.leetcode.prob.paid;

/**
 * Given an integer n, find the closest integer (not including itself), which is
 * a palindrome.
 * 
 * The 'closest' is defined as absolute difference minimized between two
 * integers.
 * 
 * Example 1: Input: "123" Output: "121" Note: The input n is a positive integer
 * represented by string, whose length will not exceed 18. If there is a tie,
 * return the smaller one as answer.
 * 
 * @author jojo
 *
 */
public class FindTheClosestPalindrom {
    public String nearestPalindromic(String n) {
        // if greater than 2 and all 9 then need to convert is to + 2;
        if (n.length() >= 2 && allNine(n)) {
            StringBuilder sb = new StringBuilder("1");
            for (int i = 0; i < n.length() - 1; i++) {
                sb.append("0");
            }

            return sb.append("1").toString();
        }

        boolean isOdd = n.length() % 2 != 0;
        String left = n.substring(0, (n.length() + 1) / 2);

        long minDiff = Long.MAX_VALUE;
        String result = "";

        // should either add 1 or minus 1 if the existing number is a palindrom
        // else do 0
        for (int change : new int[] { -1, 0, 1 }) {
            String assumedPalindrom = getPalindrom(String.valueOf(Long.valueOf(left) + change), isOdd);

            // if the input is 10 then left will have 1 and for (0) change
            // palindrom will be '0'so need to manually edit it to '9'
            // if input is 999123 then left + 1 will make it 1000 and palindrom
            // will make it 1000-0001 which is much larger than next palindrom
            if (n.length() >= 2 && (n.length() != assumedPalindrom.length() || Long.valueOf(assumedPalindrom) == 0)) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n.length() - 1; i++) {
                    sb.append("9");
                }

                assumedPalindrom = sb.toString();
            }

            long diff = Math.abs(Long.valueOf(n) - Long.valueOf(assumedPalindrom));
            if (diff != 0 && minDiff > diff) {
                minDiff = diff;
                result = assumedPalindrom;
            }
        }

        return result;
    }

    private boolean allNine(String str) {
        for (char ch : str.toCharArray()) {
            if (ch != '9') {
                return false;
            }
        }

        return true;
    }

    private String getPalindrom(String left, boolean isOdd) {
        if (isOdd) {
            return left.substring(0, left.length() - 1) + new StringBuilder(left).reverse().toString();
        }

        return left + new StringBuilder(left).reverse().toString();
    }
}
