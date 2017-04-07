package interview.leetcode.practice;

public class ReverseNumber {
    public int reverseNumber(int num) {
        int sign = num < 0 ? -1 : 1;
        num = Math.abs(num);

        int result = 0;
        while (num > 0) {
            result *= 10;
            result += num % 10;
            num /= 10;
        }

        return result * sign;
    }
}
