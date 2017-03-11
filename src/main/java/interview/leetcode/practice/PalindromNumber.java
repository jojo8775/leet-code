package interview.leetcode.practice;

public class PalindromNumber {
    public boolean isPalindrom(int num) {
        if (num < 0) {
            return false;
        }

        int original = num, reverse = 0;

        while (num > 0) {
            reverse *= 10;
            reverse += num % 10;
            num /= 10;
        }

        return reverse == original;
    }
}
