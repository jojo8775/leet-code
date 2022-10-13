package interview.leetcode.prob;

/**
 * Given a binary string s, return the minimum number of character swaps to make it alternating, or -1 if it is impossible.

The string is called alternating if no two adjacent characters are equal. For example, the strings "010" and "1010" are alternating, while the string "0100" is not.

Any two characters may be swapped, even if they are not adjacent.

 

Example 1:

Input: s = "111000"
Output: 1
Explanation: Swap positions 1 and 4: "111000" -> "101010"
The string is now alternating.
Example 2:

Input: s = "010"
Output: 0
Explanation: The string is already alternating, no swaps are needed.
Example 3:

Input: s = "1110"
Output: -1
 

Constraints:

1 <= s.length <= 1000
s[i] is either '0' or '1'.
Accepted
19,284
Submissions
45,999
 * @author jojo
 * Oct 13, 2022 9:16:40 AM
 */
public class MinimumNumberOfSwapsToMakeTheBinaryStringAlternating {
    public int minSwaps(String s) {
        int oneCount = 0, zeroCount = 0;
        
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '1'){
                oneCount++;
            }
            else{
                zeroCount++;
            }
        }
        
        // if the diff of 1 and 0 is more than 1 then interleaving is not possible. 
        if(Math.abs(oneCount - zeroCount) > 1){
            return -1;
        }
        
        // if 1's are more than 0's then string should start with '1'
        if(oneCount > zeroCount){
            return countSwap(s, '1');
        }
        
        // if 0's are more than 1's then string should start with '0'
        if(zeroCount > oneCount){
            return countSwap(s, '0');
        }
        
        // if 1's and 0's are equal then take the min 
        // as the string can start either by 1 or 0 depending on the initial setup. 
        return Math.min(countSwap(s, '0'), countSwap(s, '1'));
    }
    
    private int countSwap(String s, char start){
        int swapCount = 0;
        
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) != start){
                swapCount++;
            }
            
            start = (start == '1') ? '0' : '1';
        }
        
        return swapCount/2;
    }
}
