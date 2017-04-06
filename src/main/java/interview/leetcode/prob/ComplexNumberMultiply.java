package interview.leetcode.prob;

/**
 * Given two strings representing two complex numbers.
 * 
 * You need to return a string representing their multiplication. Note i2 = -1
 * according to the definition.
 * 
 * Example 1: Input: "1+1i", "1+1i" Output: "0+2i" Explanation: (1 + i) * (1 +
 * i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 * Example 2: Input: "1+-1i", "1+-1i" Output: "0+-2i" Explanation: (1 - i) * (1
 * - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 * Note:
 * 
 * The input strings will not have extra blank. The input strings will be given
 * in the form of a+bi, where the integer a and b will both belong to the range
 * of [-100, 100]. And the output should be also in this form.
 * 
 * @author jojo Mar 27, 20179:13:53 PM
 */
public class ComplexNumberMultiply {
    public String complexNumberMultiply(String a, String b) {
        // formula ;
        // (a1+b1i) * (a2+b2i) = (a1 * b1) - (a2 * b2) + ((a1 * b2) + (b1 * a2))
        // + i

        String[] arr1 = a.split("\\+");
        int a1 = Integer.valueOf(arr1[0]), a2 = Integer.valueOf(arr1[1].replace("i", ""));

        String[] arr2 = b.split("\\+");
        int b1 = Integer.valueOf(arr2[0]), b2 = Integer.valueOf(arr2[1].replace("i", ""));

        return ((a1 * b1) - (a2 * b2)) + "+" + ((a1 * b2) + (b1 * a2)) + "i";
    }
}
