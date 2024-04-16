package interview.leetcode.prob;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You are given a string word and an array of strings forbidden.

A string is called valid if none of its substrings are present in forbidden.

Return the length of the longest valid substring of the string word.

A substring is a contiguous sequence of characters in a string, possibly empty.

 

Example 1:

Input: word = "cbaaaabc", forbidden = ["aaa","cb"]
Output: 4
Explanation: There are 11 valid substrings in word: "c", "b", "a", "ba", "aa", "bc", "baa", "aab", "ab", "abc" and "aabc". The length of the longest valid substring is 4. 
It can be shown that all other substrings contain either "aaa" or "cb" as a substring. 
Example 2:

Input: word = "leetcode", forbidden = ["de","le","e"]
Output: 4
Explanation: There are 11 valid substrings in word: "l", "t", "c", "o", "d", "tc", "co", "od", "tco", "cod", and "tcod". The length of the longest valid substring is 4.
It can be shown that all other substrings contain either "de", "le", or "e" as a substring. 
 

Constraints:

1 <= word.length <= 105
word consists only of lowercase English letters.
1 <= forbidden.length <= 105
1 <= forbidden[i].length <= 10
forbidden[i] consists only of lowercase English letters.
Accepted
22,412
Submissions
61,432
 * 
 * Apr 15, 2024 - 11:23:34 PM
 * Jojo 
 */
public class LengthOfLongestValidSubstring {
	// This is using hash set
	public int longestValidSubstring_1(String word, List<String> forbidden) {
        Set<String> forbiddenSet = new HashSet<>();
        
        int longestForbiddenWord = 0;
        for(String s : forbidden){
            forbiddenSet.add(s);
            
            longestForbiddenWord = Math.max(longestForbiddenWord, s.length());
        }
        
        int len = word.length();
        
        int result = 0;
        
        for(int i=len - 1, right = len; i >= 0 && right > result ; i--){
            
            StringBuilder sb = new StringBuilder();
            for(int j = i; j < right && (j - i) < longestForbiddenWord; j++){
                sb.append(word.charAt(j));
                
                if(forbiddenSet.contains(sb.toString())){
                    right = j;
                    break;
                }
            }
            
            result = Math.max(result, right - i);
        }
        
        return result;
    }
    
	// this is using Trie. This is more performent. 
    public int longestValidSubstring(String word, List<String> forbidden) {
        Tri root = new Tri();
        
        for(String s : forbidden){
            storeWord(root, s);
        }
        
        int result = 0;
        
        for(int left = 0, right = 0; right < word.length(); right++){
            left = findLeftIdx(root, left, right, word);
            
            result = Math.max(result, right - left + 1);    
        }
        
        return result;
    }
    
    // find the max left index till when the string is valid. 
    private int findLeftIdx(Tri root, int left, int right, String s){
        int idx = right;
        
        while(idx >= left){
            char ch = s.charAt(idx);
            
            if(root.children[ch - 'a'] == null){
                return left;
            }
            else{
                root = root.children[ch - 'a'];
                
                if(root.isWord){
                    return idx + 1;
                }
                
                idx--;
            }
        }
        
        return left;
    }
    
    // stores the word in tri from right to left so that it is easy
    // to calculate the overlap
    private void storeWord(Tri root, String s){
        Tri cur = root;
            
        for(int i=s.length() - 1; i>=0; i--){
            char ch = s.charAt(i);

            if(cur.children[ch - 'a'] == null){
                cur.children[ch - 'a'] = new Tri();
            }

            cur = cur.children[ch - 'a'];
        }

        cur.isWord = true;
    }
    
    private static class Tri{
        Tri[] children = new Tri[26];
        boolean isWord = false;
    }
}
