package interview.leetcode.prob;

/**
 * Given an integer, return its base 7 string representation.

Example 1:
Input: 100
Output: "202"
Example 2:
Input: -7
Output: "-10"
Note: The input will be in range of [-1e7, 1e7].

Subscribe to see which companies asked this question.
 * @author jojo
 *Mar 19, 20172:34:07 PM
 */
public class Base7 {
    public String convertToBase7(int num) {
        if(num == 0){
            return "0";
        }
        
        String sign = num < 0 ? "-" : "";
        num = Math.abs(num);
        
        StringBuilder sb = new StringBuilder();
        while(num != 0){
            sb.append(num % 7);
            num /= 7;
        }
        
        return sb.append(sign).reverse().toString();
    }
}
