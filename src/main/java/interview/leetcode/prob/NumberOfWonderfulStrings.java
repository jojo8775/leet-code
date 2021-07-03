package interview.leetcode.prob;

/**
 * A wonderful string is a string where at most one letter appears an odd number of times.

For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number of wonderful non-empty substrings in word. If the same substring appears multiple times in word, then count each occurrence separately.

A substring is a contiguous sequence of characters in a string.

 

Example 1:

Input: word = "aba"
Output: 4
Explanation: The four wonderful substrings are underlined below:
- "aba" -> "a"
- "aba" -> "b"
- "aba" -> "a"
- "aba" -> "aba"
Example 2:

Input: word = "aabb"
Output: 9
Explanation: The nine wonderful substrings are underlined below:
- "aabb" -> "a"
- "aabb" -> "aa"
- "aabb" -> "aab"
- "aabb" -> "aabb"
- "aabb" -> "a"
- "aabb" -> "abb"
- "aabb" -> "b"
- "aabb" -> "bb"
- "aabb" -> "b"
Example 3:

Input: word = "he"
Output: 2
Explanation: The two wonderful substrings are underlined below:
- "he" -> "h"
- "he" -> "e"
 

Constraints:

1 <= word.length <= 105
word consists of lowercase English letters from 'a' to 'j'.
Accepted
3,124
Submissions
9,278
 * @author jojo
 * Jul 2, 2021  12:16:49 AM
 */
public class NumberOfWonderfulStrings {
    public long wonderfulSubstrings(String word) {
        int[] countArr = new int[1024]; // 2^10 representation of an integer. Since there are 10 letters 
        
        countArr[0] = 1;
        
        long result = 0;
        int mask = 0;
        
        // for the logic below try to work with "aba" - it will make sense 
        for(char ch : word.toCharArray()){
            // marking the bit 
            mask ^= (1 << (int)(ch - 'a'));
            
            // adding the previously found numbers for the current mask
            result += countArr[mask];
            
            // flipping each characters to make it odd 
            for(int i=0; i<10; i++){
                result += countArr[mask ^ (1 << i)];
            }
            
            // adding the mask count for future calculation
            countArr[mask]++;
        }
        
        
        return result;
    }
}
