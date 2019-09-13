package interview.leetcode.prob;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/***
 * 
Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

If there is no answer, return the empty string.
Example 1:
Input: 
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation: 
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:
Input: 
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation: 
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
Note:

All the strings in the input will only contain lowercase letters.
The length of words will be in the range [1, 1000].
The length of words[i] will be in the range [1, 30].

 * @author jojo
 * Sep 13, 2019 3:13:15 AM
 */
public class LongestWordInDictionary {
	public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> seenSofar = new HashSet<>();
        
        String result = "";
        int len = 0;
        
        for(String w : words){
            if(w.length() == 1 || seenSofar.contains(w.substring(0, w.length() - 1))){
                if(len < w.length()){
                    result = w;
                    len = w.length();
                }
                
                // need to add only those words which forms a chain 
                seenSofar.add(w);
            }
        }
        
        return result;
    }
}
