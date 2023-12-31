package interview.leetcode.prob;

/**
 * Given a string s, return the length of the longest substring between two equal characters, excluding the two characters. If there is no such substring return -1.

A substring is a contiguous sequence of characters within a string.

 

Example 1:

Input: s = "aa"
Output: 0
Explanation: The optimal substring here is an empty substring between the two 'a's.
Example 2:

Input: s = "abca"
Output: 2
Explanation: The optimal substring here is "bc".
Example 3:

Input: s = "cbzxy"
Output: -1
Explanation: There are no characters that appear twice in s.
 

Constraints:

1 <= s.length <= 300
s contains only lowercase English letters.
Accepted
81,682
Submissions
125,613
 * Author: jojo
 * Dec. 30, 2023 - 11:37:05 p.m.
 */
public class LargestSubstringBetweenTwoEqualCharacters {
	public int maxLengthBetweenEqualCharacters(String s) {
        Integer[] arr = new Integer[26];
        int len = s.length();
        
        for(int i=0; i<len; i++){
            char ch = s.charAt(i);
            
            if(arr[ch - 'a'] == null){
                arr[ch - 'a'] = i;
            }
        }
        
        int result = -1;
        for(int i=len-1; i>=0; i--){
            char ch = s.charAt(i);
            int prevIdx = arr[ch - 'a'];
            
            if(i - prevIdx > 0){
                result = Math.max(result, i - prevIdx - 1);
            }
        }
        
        return result;
    }
}
