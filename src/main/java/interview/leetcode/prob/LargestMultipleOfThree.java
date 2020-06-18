package interview.leetcode.prob;

/**
 * Given an integer array of digits, return the largest multiple of three that can be formed by concatenating some of the given digits in any order.

Since the answer may not fit in an integer data type, return the answer as a string.

If there is no answer return an empty string.

 

Example 1:

Input: digits = [8,1,9]
Output: "981"
Example 2:

Input: digits = [8,6,7,1,0]
Output: "8760"
Example 3:

Input: digits = [1]
Output: ""
Example 4:

Input: digits = [0,0,0,0,0,0]
Output: "0"
 

Constraints:

1 <= digits.length <= 10^4
0 <= digits[i] <= 9
The returning answer must not contain unnecessary leading zeros.
Accepted
6,661
Submissions
20,082
 * @author jojo
 * Jun 14, 2020  11:33:42 PM
 */
public class LargestMultipleOfThree {
    // very good explaination is given in the link below.
    // https://leetcode.com/problems/largest-multiple-of-three/discuss/517628/Python-Basic-Math 
    public String largestMultipleOfThree(int[] digits) {
        int sum = 0;
        int[] arr = new int[10];
        for(int d : digits){
            sum += d;
            arr[d]++;
        }
        
        if(sum == 0){
            return "0";
        }
        
        return solve(arr, sum, sum%3);
    }
    
    private String solve(int[] count, int sum, int remainder){
        // no remainder so the input is the max result.
        if(remainder == 0){
            return buildStr(count);
        }
        
        int mod = remainder;
        while(sum % 3 != 0){
            // if remainder is 1 then try to remove one of 1,4,7 entries from the input to make the modulus = 0
            // if remainder is 2 then try to remove one of 2,5,8 entries from the input to make the modulus = 0
            if(count[mod]-- > 0){
                sum -= mod;
            }
            else if(count[mod] <= 0){
                mod += 3;
            }
            
            // if the remainder is 1 and there is no entries of 1,4,7 then need to remove two of 2,5,8 entries from the input to make sum%3=0 
            // if the remainder is 2 and there is no entries of 2,5,8 then need to remove two of 1,4,7 entries from the input to make sum%3=0 
            if(mod > 9){
                mod = (mod + mod) % 3;
            }
        }
        
        return buildStr(count);
    }
    
    private String buildStr(int[] count){
        StringBuilder sb = new StringBuilder();
        
        for(int i=9; i>=0; i--){
            while(count[i]-- > 0){
                sb.append(String.valueOf(i));
            }
        }
        
        return sb.toString();
    }
}
