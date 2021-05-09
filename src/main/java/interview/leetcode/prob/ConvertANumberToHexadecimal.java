package interview.leetcode.prob;

/**
 * 
Given an integer num, return a string representing its hexadecimal representation. For negative integers, two’s complement method is used.

All the letters in the answer string should be lowercase characters, and there should not be any leading zeros in the answer except for the zero itself.

 

Example 1:

Input: num = 26
Output: "1a"
Example 2:

Input: num = -1
Output: "ffffffff"
 

Constraints:

-231 <= num <= 231 - 1
 

Follow up: Could you solve it without using any built-in library method?

Accepted
79,768
Submissions
178,768
 * @author jojo
 * May 3, 2021  9:44:03 PM
 */
public class ConvertANumberToHexadecimal {
    public String toHex(int num) {
        if(num == 0){
            return "0";
        }
        
        char[] arr = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        
        StringBuilder sb = new StringBuilder();
        while(num != 0){
            int idx = num & 15;
            sb.append(arr[idx]);
            
            // >>> three angle bracket takes care of the negative number. 
            num = num >>> 4;
        }
        
        return sb.reverse().toString();
    }
}
