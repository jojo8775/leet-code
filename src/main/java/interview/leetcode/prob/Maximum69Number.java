package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a positive integer num consisting only of digits 6 and 9.

Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).

 

Example 1:

Input: num = 9669
Output: 9969
Explanation: 
Changing the first digit results in 6669.
Changing the second digit results in 9969.
Changing the third digit results in 9699.
Changing the fourth digit results in 9666.
The maximum number is 9969.
Example 2:

Input: num = 9996
Output: 9999
Explanation: Changing the last digit 6 to 9 results in the maximum number.
Example 3:

Input: num = 9999
Output: 9999
Explanation: It is better not to apply any change.
 

Constraints:

1 <= num <= 104
num consists of only 6 and 9 digits.
Accepted
138,527
Submissions
173,376
 * @author jojo
 * Nov 6, 2022 9:24:11 PM
 */
public class Maximum69Number {
    public int maximum69Number (int num) {
        List<Integer> list = new ArrayList<>();
        
        while(num > 0){
            list.add(num % 10);
            num /= 10;
        }
        
        for(int i=list.size() - 1; i>=0; i--){
            if(list.get(i) != 9){
                list.set(i, 9);
                break;
            }
        }
        
        int result = 0;
        for(int i=list.size() - 1; i>=0; i--){
            result *= 10;
            result += list.get(i);
        }
        
        return result;
    }
}
