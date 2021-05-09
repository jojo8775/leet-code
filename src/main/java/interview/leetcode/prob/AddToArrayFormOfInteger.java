package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The array-form of an integer num is an array representing its digits in left to right order.

For example, for num = 1321, the array form is [1,3,2,1].
Given num, the array-form of an integer, and an integer k, return the array-form of the integer num + k.

 

Example 1:

Input: num = [1,2,0,0], k = 34
Output: [1,2,3,4]
Explanation: 1200 + 34 = 1234
Example 2:

Input: num = [2,7,4], k = 181
Output: [4,5,5]
Explanation: 274 + 181 = 455
Example 3:

Input: num = [2,1,5], k = 806
Output: [1,0,2,1]
Explanation: 215 + 806 = 1021
Example 4:

Input: num = [9,9,9,9,9,9,9,9,9,9], k = 1
Output: [1,0,0,0,0,0,0,0,0,0,0]
Explanation: 9999999999 + 1 = 10000000000
 

Constraints:

1 <= num.length <= 104
0 <= num[i] <= 9
num does not contain any leading zeros except for the zero itself.
1 <= k <= 104
Accepted
69,340
Submissions
154,224
 * @author jojo
 * May 2, 2021  11:01:20 PM
 */
public class AddToArrayFormOfInteger {
	public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> result = new ArrayList<>();
        
        int carry = 0, j = num.length - 1;
        while(k > 0){
            int val = k%10;
            val += carry;
            
            if(j>=0){
                val += num[j--];
            }
            
            carry = val/10;
            val %= 10;
            result.add(val);
            
            k /=10;
        }
        
        while(j>=0){
            int val = num[j--] + carry;
            carry = val/10;
            val %= 10;
            result.add(val);
        }
        
        if(carry > 0){
            result.add(carry);
        }
        
        Collections.reverse(result);
        return result;
    }
}
