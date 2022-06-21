package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * A valid encoding of an array of words is any reference string s and array of indices indices such that:

words.length == indices.length
The reference string s ends with the '#' character.
For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.

 

Example 1:

Input: words = ["time", "me", "bell"]
Output: 10
Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
Example 2:

Input: words = ["t"]
Output: 2
Explanation: A valid encoding would be s = "t#" and indices = [0].
 

Constraints:

1 <= words.length <= 2000
1 <= words[i].length <= 7
words[i] consists of only lowercase letters.
Accepted
57,669
Submissions
100,242
 * @author jojo
 * Jun 19, 2022 11:47:44 PM
 */
public class ShortEncodingOfWords {
    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet<>();
        for(String s : words){
            set.add(s);
        }
        
        for(String s : words){
            for(int i=1; i < s.length(); i++){
                set.remove(s.substring(i));
            }
        }
        
        int ans = 0;
        for(String s : set){
            ans += s.length();
            ans += 1;
        }
        
        return ans;
    }
}
