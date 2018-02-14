package interview.leetcode.prob;

/**
 * Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.

(Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

Example 1:
Input: N = 10
Output: 9
Example 2:
Input: N = 1234
Output: 1234
Example 3:
Input: N = 332
Output: 299
Note: N is an integer in the range [0, 10^9].


 * @author jojo
 *Feb 14, 20181:57:49 PM
 */
public class MonotoneIncreasingDigits {
    public int monotoneIncreasingDigits(int N) {
        char[] cArr = String.valueOf(N).toCharArray();
        
        int mark = cArr.length - 1;
        for(int i=cArr.length - 1; i>0; i--){
            if(cArr[i - 1] > cArr[i]){
                mark = i-1;
                cArr[i-1]--;
            }
        }
        
        for(int i=mark + 1; i< cArr.length; i++){
            cArr[i] = '9';
        }
        
        return Integer.valueOf(String.valueOf(cArr));
    }
}
