package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)

A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.

Return a list of all uncommon words. 

You may return the list in any order.

 

Example 1:

Input: A = "this apple is sweet", B = "this apple is sour"
Output: ["sweet","sour"]
Example 2:

Input: A = "apple apple", B = "banana"
Output: ["banana"]
 

Note:

0 <= A.length <= 200
0 <= B.length <= 200
A and B both contain only spaces and lowercase letters.
Accepted
73,282
Submissions
114,037
 * @author jojo
 * Apr 18, 2021  9:24:51 AM
 */
public class UncommonWordsFromTwoSentence {
    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> wordMap = new HashMap<>();
        
        for(String s : A.split("\\s+")){
            wordMap.put(s, wordMap.getOrDefault(s, 0) + 1);
        }
        
        for(String s : B.split("\\s+")){
            wordMap.put(s, wordMap.getOrDefault(s, 0) + 1);
        }
        
        List<String> words = new ArrayList<>();
        
        for(Map.Entry<String, Integer> e : wordMap.entrySet()){
            if(e.getValue() == 1){
                words.add(e.getKey());
            }
        }
        
        return words.stream().toArray(String[] :: new);
    }
}
