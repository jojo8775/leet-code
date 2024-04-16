package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Mar 16, 2024 - 2:34:37 PM
 * Jojo
 * 
 * You are given an array arr of size n consisting of non-empty strings.

Find a string array answer of size n such that:

answer[i] is the shortest substring of arr[i] that does not occur as a substring in any other string in arr. If multiple such substrings exist, answer[i] should be the lexicographically smallest. And if no such substring exists, answer[i] should be an empty string.
Return the array answer.

 

Example 1:

Input: arr = ["cab","ad","bad","c"]
Output: ["ab","","ba",""]
Explanation: We have the following:
- For the string "cab", the shortest substring that does not occur in any other string is either "ca" or "ab", we choose the lexicographically smaller substring, which is "ab".
- For the string "ad", there is no substring that does not occur in any other string.
- For the string "bad", the shortest substring that does not occur in any other string is "ba".
- For the string "c", there is no substring that does not occur in any other string.
Example 2:

Input: arr = ["abc","bcd","abcd"]
Output: ["","","abcd"]
Explanation: We have the following:
- For the string "abc", there is no substring that does not occur in any other string.
- For the string "bcd", there is no substring that does not occur in any other string.
- For the string "abcd", the shortest substring that does not occur in any other string is "abcd".
 

Constraints:

n == arr.length
2 <= n <= 100
1 <= arr[i].length <= 20
arr[i] consists only of lowercase English letters.
Accepted
17,233
Submissions
38,976
 *  
 */
public class ShortestUncommonSubstringInAnArray {
	public String[] shortestSubstrings(String[] arr) {
        // contains the frequency of all the possible substring across all the words 
        Map<String, Integer> substringCount = new HashMap<>();
        
        // contains the collection of substrings for a given word
        List<TreeSet<String>> list = new ArrayList<>();
        
        for(String s : arr){
            
            // since the problem asks for lexicographically smallest entry we first do it by length
            // then by the alphabets.
            TreeSet<String> set = new TreeSet<>((a,b) -> {
                if(a.length() == b.length()){
                    return a.compareTo(b);
                }
                
                return a.length() - b.length();
            });
            
            // finding all possible substring for a given string.
            for(int i=0; i<s.length(); i++){
                for(int j=i+1; j<=s.length(); j++){
                    String substr = s.substring(i,j);
                    set.add(substr);
                }
            }
            
            // if a string contains repetitive letters like "abb", the substring "b" should be counted a 1 and not 2 
            // if we count "b" = 2 and even if there are no other string with letter "b", it will still disqualify "b"
            // as an answer. 
            for(String entry : set){
                substringCount.put(entry, substringCount.getOrDefault(entry, 0) + 1);
            }
            
            list.add(set);
        }
        
        String[] result = new String[arr.length];
        
        for(int i=0; i<list.size(); i++){
            boolean found = false;
            
            for(String s : list.get(i)){
                if(substringCount.get(s) == 1){
                    found = true;
                    result[i] = s;
                    break;
                }
            }
            
            if(!found){
                result[i] = "";
            }
        }
        
        return result;
    }
}
