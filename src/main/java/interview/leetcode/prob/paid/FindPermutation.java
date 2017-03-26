package interview.leetcode.prob.paid;

/**
 * By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' represents a decreasing relationship between two numbers, 'I' represents an increasing relationship between two numbers. And our secret signature was constructed by a special integer array, which contains uniquely all the different number from 1 to n (n is the length of the secret signature plus 1). For example, the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2], but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal constructing special string that can't represent the "DI" secret signature.

On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could refer to the given secret signature in the input.

Example 1:
Input: "I"
Output: [1,2]
Explanation: [1,2] is the only legal initial spectial string can construct secret signature "I", where the number 1 and 2 construct an increasing relationship.
Example 2:
Input: "DI"
Output: [2,1,3]
Explanation: Both [2,1,3] and [3,1,2] can construct the secret signature "DI", 
but since we want to find the one with the smallest lexicographical permutation, you need to output [2,1,3]
 * @author jojo
 *Mar 26, 20174:21:03 PM
 */
public class FindPermutation {
    public int[] findPermutation(String s) {
        int[] arr = new int[s.length() + 1];
        
        for(int i=0; i<arr.length; i++){
            arr[i] = i+1;
        }
        
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == 'D'){
                int beg = i, end = i;
                while(end < s.length() && s.charAt(end) == 'D'){
                    end++;
                }
                
                reverse(arr, beg, end);
                i = end;
            }
        }
        
        return arr;
    }
    
    private void reverse(int[] arr, int beg, int end){
        while(beg < end){
            int temp = arr[beg];
            arr[beg++] = arr[end];
            arr[end--] = temp;
        }
    }
}
