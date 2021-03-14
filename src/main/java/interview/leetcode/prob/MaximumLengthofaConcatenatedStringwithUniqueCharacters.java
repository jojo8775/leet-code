package interview.leetcode.prob;

import java.util.List;

/**
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.

 

Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
 

Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.
Accepted
50,527
Submissions
101,682
 * @author jojo
 * Mar 7, 2021  11:48:47 PM
 */
public class MaximumLengthofaConcatenatedStringwithUniqueCharacters {
    private int maxLen = 0;
    
    public int maxLength(List<String> arr) {
        dfs(new int[26], arr, 0, 0);
        
        return maxLen;
    }
    
    private void dfs(int[] charArr, List<String> input, int idx, int len){
        if(idx >= input.size()){
            return;
        }
        
        for(int i=idx; i<input.size(); i++){
            String e = input.get(i);
            
            int j=0;
            while(j < e.length()){
                if(++charArr[e.charAt(j) - 'a'] != 1){
                    break;
                }
                j++;
            }
            
            if(j == e.length()){
                
                maxLen = Math.max(maxLen, len + e.length());
                
                dfs(charArr, input, i + 1, len + e.length());
                
                j--;
            }
            
            while(j>=0){
                charArr[e.charAt(j) - 'a']--;
                j--;
            }
        }
    }
}
