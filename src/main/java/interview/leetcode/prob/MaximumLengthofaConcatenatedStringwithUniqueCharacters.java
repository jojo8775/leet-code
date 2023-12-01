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
    public int maxLength(List<String> arr) {
        return backTrack(new int[26], arr, 0);
    }

    private int backTrack(int[] arr, List<String> words, int idx){
        int max = 0;

        for(int i=idx; i<words.size(); i++){
            String word = words.get(i);

            int j = 0, len = word.length();

            while(j < len){
                char ch = word.charAt(j);
                arr[ch - 'a']++;

                if(arr[ch - 'a'] > 1){
                    break;
                }
                
                j++;
            }

            if(j == len){
                max = Math.max(max, len + backTrack(arr, words, i + 1));
                j--;
            }

            while(j >= 0){
                char ch = word.charAt(j--);
                arr[ch - 'a']--;
            }
        }
        
        return max;
    }
}
