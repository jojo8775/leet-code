package interview.leetcode.prob;

/**
 * We define the Perfect Number is a positive integer that is equal to the sum
 * of all its positive divisors except itself.
 * 
 * Now, given an integer n, write a function that returns true when it is a
 * perfect number and false when it is not. Example: Input: 28 Output: True
 * Explanation: 28 = 1 + 2 + 4 + 7 + 14 Note: The input number n will not exceed
 * 100,000,000. (1e8)
 * 
 * @author jojo Mar 27, 20178:42:09 PM
 */
public class PerfectNumber {
    public boolean checkPerfectNumber(int num) {
        if (num <= 2) {
            return false;
        }

        int sum = 1;

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                sum += num / i;
            }
        }

        return sum == num;
    }
}
