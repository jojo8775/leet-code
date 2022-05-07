package interview.leetcode.prob.paid;

/**
 * You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening brackets '[' and n / 2 closing brackets ']'.

A string is called balanced if and only if:

It is the empty string, or
It can be written as AB, where both A and B are balanced strings, or
It can be written as [C], where C is a balanced string.
You may swap the brackets at any two indices any number of times.

Return the minimum number of swaps to make s balanced.

 

Example 1:

Input: s = "][]["
Output: 1
Explanation: You can make the string balanced by swapping index 0 with index 3.
The resulting string is "[[]]".
Example 2:

Input: s = "]]][[["
Output: 2
Explanation: You can do the following to make the string balanced:
- Swap index 0 with index 4. s = "[]][][".
- Swap index 1 with index 5. s = "[[][]]".
The resulting string is "[[][]]".
Example 3:

Input: s = "[]"
Output: 0
Explanation: The string is already balanced.
 

Constraints:

n == s.length
2 <= n <= 106
n is even.
s[i] is either '[' or ']'.
The number of opening brackets '[' equals n / 2, and the number of closing brackets ']' equals n / 2.
Accepted
26,687
Submissions
39,566
 * @author jojo
 * May 4, 2022 11:30:06 PM
 */
public class MinimumNumberOfSwapsToMakeStringBalanced {
    public int minSwaps(String s) {
        int beg = 0, end = s.length() - 1;
        int swapCount = 0;
        int obcFromBeg = 0, obcFromEnd = 0;
        
        while(beg < end){
            // find the first extra open bracket from end
            while(beg < end && obcFromEnd <= 0){
                if(s.charAt(end) == ']'){
                    obcFromEnd--;
                }
                else{
                    obcFromEnd++;
                }
                
                if(obcFromEnd <= 0){
                    end--;
                }
            }
            
            // find the first extra closed bracket from beg
            while(beg < end && obcFromBeg >= 0){
                if(s.charAt(beg) == '['){
                    obcFromBeg++;
                }
                else{
                    obcFromBeg--;
                }
                
                if(obcFromBeg >= 0){
                    beg++;
                }
            }
            
            // if beg is still less then end then swap is needed
            if(beg < end){
                swapCount++;
                
                beg++;
                end--;
                
                // since string is immutable we are mimicing the swap by incrementing these two values.
                obcFromBeg = 1;
                obcFromEnd = -1;
            }
        }
        
        return swapCount;
    }
}
