package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
Note:

S will be a string with length at most 12.
S will consist only of letters or digits.
 * @author jojo
 *Mar 13, 20181:05:05 AM
 */
public class LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        
        char[] cArr = S.toCharArray();
    
        for(int i=0 ; i<cArr.length; i++){
            if(cArr[i] >= 'A' && cArr[i] <= 'Z'){
                cArr[i] = (char) ('a' + (cArr[i] - 'A')); // turning all letters to lower case
            }
        }
        
        result.add(String.valueOf(cArr));
        for(int i=0; i<cArr.length; i++){
            if(cArr[i] >= 'a' && cArr[i] <='z'){
                int size = result.size();
                for(int j=0; j<size; j++){
                    // adding another copy of the existing combination replacing current letter with upper case
                    String str = result.get(j).substring(0,i) + (char)('A' + (cArr[i] - 'a')) + result.get(j).substring(i+1);
                    result.add(str);
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args){
        List<String> result = new LetterCasePermutation().letterCasePermutation("2142");
        for(String s : result){
            System.out.println(s);
        }
    }
}
