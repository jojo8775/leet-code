package interview.leetcode.prob.paid;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.
 * @author jojo
 *
 */
public class WordPatternII {
    public boolean wordPatternMatch(String pattern, String str) {
        // pattern cannot be larger than input string
        if(pattern.length() > str.length()){
            return false;
        }
        
        // set is used soto avoid assigning multiple keys to the same value
        return patternMatch(new HashMap<Character, String>(), new HashSet<String>(), pattern, str, 0, 0);
    }
    
    private boolean patternMatch(Map<Character, String> wordMap, Set<String> values, String pattern, String str, int patternIdx, int wordIdx){
        // if both index rechead to end then its a matching pattern
        if(wordIdx == str.length() && patternIdx == pattern.length()){
            return true;
        }
        
        // if any one of the index reached to end then there is no matching pattern
        if(wordIdx == str.length() || patternIdx == pattern.length()){
            return false;
        }
        
        char ch = pattern.charAt(patternIdx);
        // because substring is last index exlusive
        for(int i = wordIdx + 1; i<= str.length(); i++){
            String subStr = str.substring(wordIdx, i);
            
            // add if both key and value dont exist
            if(!wordMap.containsKey(ch) && !values.contains(subStr)){
                wordMap.put(ch, subStr);
                values.add(subStr);
                
                // if match found then return true
                if(patternMatch(wordMap, values, pattern, str, patternIdx + 1, i)){
                    return true;
                }
                
                // back track
                wordMap.remove(ch);
                values.remove(subStr);
            }
            
            // if both key and value exists check if they match
            else if(wordMap.containsKey(ch) && values.contains(subStr)){
                // if value match then call recursively for the next match
                if(wordMap.get(ch).equals(subStr)){
                    return patternMatch(wordMap, values, pattern, str, patternIdx + 1, i);
                }
                
                //terminate and backtrack
                return false;
            }
        }
        
        // match not found
        return false;
    }
}
