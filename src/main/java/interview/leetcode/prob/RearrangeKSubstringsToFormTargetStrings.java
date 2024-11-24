package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given two strings s and t, both of which are anagrams of each other, and an integer k.

Your task is to determine whether it is possible to split the string s into k equal-sized substrings, rearrange the substrings, and concatenate them in any order to create a new string that matches the given string t.

Return true if this is possible, otherwise, return false.

An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, using all the original letters exactly once.

A substring is a contiguous non-empty sequence of characters within a string.

 

Example 1:

Input: s = "abcd", t = "cdab", k = 2

Output: true

Explanation:

Split s into 2 substrings of length 2: ["ab", "cd"].
Rearranging these substrings as ["cd", "ab"], and then concatenating them results in "cdab", which matches t.
Example 2:

Input: s = "aabbcc", t = "bbaacc", k = 3

Output: true

Explanation:

Split s into 3 substrings of length 2: ["aa", "bb", "cc"].
Rearranging these substrings as ["bb", "aa", "cc"], and then concatenating them results in "bbaacc", which matches t.
Example 3:

Input: s = "aabbcc", t = "bbaacc", k = 2

Output: false

Explanation:

Split s into 2 substrings of length 3: ["aab", "bcc"].
These substrings cannot be rearranged to form t = "bbaacc", so the output is false.
 

Constraints:

1 <= s.length == t.length <= 2 * 105
1 <= k <= s.length
s.length is divisible by k.
s and t consist only of lowercase English letters.
The input is generated such that s and t are anagrams of each other.
Accepted
9,589
Submissions
21,468
 * 
 * 
 * Nov 23, 2024 - 8:30:36 PM
 * Jojo 
 */
public class RearrangeKSubstringsToFormTargetStrings {
	public boolean isPossibleToRearrange(String s, String t, int k) {
        if(s.length() != t.length()){
            return false;
        }
        
        Map<String, Integer> split1 = new HashMap<>();
        Map<String, Integer> split2 = new HashMap<>();
        
        int len = s.length() / k; 
        
        for(int i=0; i<s.length(); i+=len){
            String s1 = s.substring(i, i+len);
            split1.put(s1, split1.getOrDefault(s1, 0) + 1);
            
            String t1 = t.substring(i, i+len);
            split2.put(t1, split2.getOrDefault(t1, 0) + 1);
        }   
        
        for(String key : split1.keySet()){
            if(!split2.containsKey(key) || !split2.get(key).equals(split1.get(key))){
                return false;
            }
        }
        
        return true;
    }   
}
