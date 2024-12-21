package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

/**
 * You are given a string s that consists of lowercase English letters.

A string is called special if it is made up of only a single character. For example, the string "abc" is not special, whereas the strings "ddd", "zz", and "f" are special.

Return the length of the longest special substring of s which occurs at least thrice, or -1 if no special substring occurs at least thrice.

A substring is a contiguous non-empty sequence of characters within a string.

 

Example 1:

Input: s = "aaaa"
Output: 2
Explanation: The longest special substring which occurs thrice is "aa": substrings "aaaa", "aaaa", and "aaaa".
It can be shown that the maximum length achievable is 2.
Example 2:

Input: s = "abcdef"
Output: -1
Explanation: There exists no special substring which occurs at least thrice. Hence return -1.
Example 3:

Input: s = "abcaba"
Output: 1
Explanation: The longest special substring which occurs thrice is "a": substrings "abcaba", "abcaba", and "abcaba".
It can be shown that the maximum length achievable is 1.
 

Constraints:

3 <= s.length <= 50
s consists of only lowercase English letters.
Accepted
32,296
Submissions
67,918
 * 
 * 
 * Dec 9, 2024 - 11:49:34 PM
 * Jojo 
 */
public class FindTheLongestSpecialStringThatOccursTriceI {
	public int maximumLength_1(String s) {
        // Create a map to store the count of all substrings
        Map<Pair<Character, Integer>, Integer> count = new HashMap<>();
        int substringLength = 0;

        for (int start = 0; start < s.length(); start++) {
            char character = s.charAt(start);
            substringLength = 0;

            for (int end = start; end < s.length(); end++) {
                // If the current character matches the initial character, increment the count
                if (character == s.charAt(end)) {
                    substringLength++;
                    Pair<Character, Integer> key = new Pair<>(character, substringLength);
                    count.put(key, count.getOrDefault(key, 0) + 1);
                } else {
                    break;
                }
            }
        }

        // Variable to store the longest substring length with frequency at least 3
        int ans = 0;
        for (Map.Entry<Pair<Character, Integer>,Integer> entry : count.entrySet()) {
            int length = entry.getKey().getValue();
            if (entry.getValue() >= 3 && length > ans) {
                ans = length;
            }
        }

        return ans == 0 ? -1 : ans;
    }
    
    // using tri
    public int maximumLength(String s) {
        Tri head = new Tri();
        
        int max = -1;
        
        for(int i=0; i<s.length(); i++){
            
            Tri cur = head;
            
            for(int j=i; j<s.length(); j++){
                if(j == i || s.charAt(j) == s.charAt(j-1)){
                    if(cur.children[s.charAt(j) - 'a'] == null){
                        cur.children[s.charAt(j) - 'a'] = new Tri();
                    }
                    
                    cur = cur.children[s.charAt(j) - 'a'];
                    cur.count += 1;
                    
                    if(cur.count >= 3 && (j - i + 1) > max){
                        max = j - i + 1;
                    }
                }
                else{
                    // if the chars are not same then it is no longer special.
                    break;
                }
            }
        }
        
        return max;
    }
    
    private static class Tri{
        int count = 0;
        Tri[] children = new Tri[26];
    }
}

