package interview.leetcode.prob;

/**
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
Note:
The given number is in the range [0, 108]

 * @author jojo
 * Aug 25, 2019 6:29:06 PM
 */
public class MaximumSwap {
	public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        
        int[] buckets = new int[10];
        
        // capturing the largest index of each digits
        for(int i=0; i<digits.length; i++){
            char d = digits[i];
            buckets[d - '0'] = i;
        }
        
        for(int i=0; i<digits.length; i++){
            for(int k=9; k> digits[i] - '0'; k--){
                // if for any digit a higher digit is present at a greater index, swapping them will get us the largest number.
                if(buckets[k] > i){
                    swap(digits, buckets[k], i);
                    
                    return Integer.parseInt(String.valueOf(digits));
                }
            }
        }
        
        // no swap;
        return num;
    }
    
    private void swap(char[] digits, int i, int j){
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
    }
}
