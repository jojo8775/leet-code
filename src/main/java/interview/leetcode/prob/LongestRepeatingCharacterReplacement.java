package interview.leetcode.prob;

/**
 * Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
 * @author jojo
 *
 */
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int[] cArr = new int[26];
        int maxCount = 0, start = 0, maxSize = 0;
        
        for(int end = 0; end < s.length(); end ++){
            cArr[s.charAt(end) - 'A']++;
            maxCount = Math.max(maxCount, cArr[s.charAt(end) - 'A']);
            
            // if max character frequency + distance between start and end is greater than k
            // this means we have considered changing more than k charactres. So time to shrink window
            if(end - start + 1 - maxCount > k){
                cArr[s.charAt(start) - 'A']--;
                start ++;
            }
            
            maxSize = Math.max(maxSize, end - start + 1);
        }
        
        return maxSize;
    }
}
