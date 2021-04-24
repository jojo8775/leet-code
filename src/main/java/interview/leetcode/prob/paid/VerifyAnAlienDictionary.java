package interview.leetcode.prob.paid;

/**
 * 
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

 

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
Accepted
229,543
Submissions
437,637
 * @author jojo
 * Apr 24, 2021  12:15:02 PM
 */
public class VerifyAnAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        int[] map = new int[26];
        
        for(int i=0; i<26; i++){
            map[order.charAt(i) - 'a'] = i;
        }
        
        for(int i=1; i<words.length; i++){
            if(isBigger(words[i-1], words[i], map)){
                return false;
            }
        }
        
        return true;
    }
    
    private boolean isBigger(String s1, String s2, int[] map){
        int n = s1.length(), m = s2.length();
            
        for(int i=0; i<n && i < m; i++){
            if(map[s1.charAt(i) - 'a'] != map[s2.charAt(i) - 'a']){
                return map[s1.charAt(i) - 'a'] > map[s2.charAt(i) - 'a'];
            }
        }
        
        return n > m;
    }
}
