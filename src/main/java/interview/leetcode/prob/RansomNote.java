package interview.leetcode.prob;

/**
 * Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

 

Example 1:

Input: ransomNote = "a", magazine = "b"
Output: false
Example 2:

Input: ransomNote = "aa", magazine = "ab"
Output: false
Example 3:

Input: ransomNote = "aa", magazine = "aab"
Output: true
 

Constraints:

You may assume that both strings contain only lowercase letters.
Accepted
260,269
Submissions
486,503

 * @author jojo
 * Apr 22, 2021  11:46:48 PM
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote.length() > magazine.length()){
            return false;
        }
        
        int[] arr = new int[26];
        
        for(char ch : magazine.toCharArray()){
            arr[ch - 'a']++;
        }
        
        for(char ch : ransomNote.toCharArray()){
            if(--arr[ch - 'a'] < 0){
                return false;
            }
        }
        
        return true;
    }
}
