package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].

Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:

0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
F.length >= 3;
and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.

Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.

Example 1:

Input: "123456579"
Output: [123,456,579]
Example 2:

Input: "11235813"
Output: [1,1,2,3,5,8,13]
Example 3:

Input: "112358130"
Output: []
Explanation: The task is impossible.
Example 4:

Input: "0123"
Output: []
Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.
Example 5:

Input: "1101111"
Output: [110, 1, 111]
Explanation: The output [11, 0, 11, 11] would also be accepted.
Note:

1 <= S.length <= 200
S contains only digits.
 * @author jojo
 * Dec 6, 2018 1:10:27 AM
 */
public class SplitArrayIntoFibbonacciSequence {
	public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> result = new ArrayList<>();
        helper(S, 0, result);
        return result;
    }
    
    private boolean helper(String str, int idx, List<Integer> result){
        if(idx == str.length() && result.size() > 2){
            return true;
        }
        
        for(int i=idx; i<str.length(); i++){
            // since there cannot be any leading 0
            if(str.charAt(idx) == '0' && i > idx){
                break;
            }
            
            // if the entry is larger than Integer then backtrack
            long val = Long.parseLong(str.substring(idx, i+1));
            if(val > Integer.MAX_VALUE){
                break;
            }
            
            int splitSize = result.size();
            
            // if the current number is greater than last two entries then backtrack
            if(splitSize >= 2 && (val > (result.get(splitSize - 1) + result.get(splitSize - 2)))){
                break;
            }
            
            // this if statement is needed for building the current number
            if(splitSize <= 1 || ((result.get(splitSize - 1) + result.get(splitSize - 2)) == val)){
                result.add((int) val);
                if(helper(str, i+1, result)){
                    return true;
                }
                
                result.remove(result.size() - 1);
            }
        }
        
        return false;
    }
}
