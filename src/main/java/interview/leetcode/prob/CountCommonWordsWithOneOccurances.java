package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two string arrays words1 and words2, return the number of strings that appear exactly once in each of the two arrays.

 

Example 1:

Input: words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
Output: 2
Explanation:
- "leetcode" appears exactly once in each of the two arrays. We count this string.
- "amazing" appears exactly once in each of the two arrays. We count this string.
- "is" appears in each of the two arrays, but there are 2 occurrences of it in words1. We do not count this string.
- "as" appears once in words1, but does not appear in words2. We do not count this string.
Thus, there are 2 strings that appear exactly once in each of the two arrays.
Example 2:

Input: words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
Output: 0
Explanation: There are no strings that appear in each of the two arrays.
Example 3:

Input: words1 = ["a","ab"], words2 = ["a","a","a","ab"]
Output: 1
Explanation: The only string that appears exactly once in each of the two arrays is "ab".
 

Constraints:

1 <= words1.length, words2.length <= 1000
1 <= words1[i].length, words2[j].length <= 30
words1[i] and words2[j] consists only of lowercase English letters.
Accepted
6,930
Submissions
9,633
 * @author jojo
 * Nov 30, 2021 10:10:06 PM
 */
public class CountCommonWordsWithOneOccurances {
	public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> wordIdx = new HashMap<>();
        
        for(String w : words1){
            wordIdx.put(w, wordIdx.getOrDefault(w, 0) + 1);
        }
        
        int count = 0;
        
        for(String w : words2){
            if(!wordIdx.containsKey(w)){
                continue;
            }
            
            int val = wordIdx.get(w);
            if(val == 1){
                count++;
                wordIdx.put(w, 0);
            }
            else if(val == 0){
                count--;
                wordIdx.put(w, -1);
            }
        }
        
        return count;
    }
    
    
//     public int countWords(String[] words1, String[] words2) {
//         Set<String> u1 = uniqueWords(words1);
//         Set<String> u2 = uniqueWords(words2);
        
//         int count = 0;
//         for(String s : u1){
//             if(u2.contains(s)){
//                 count++;
//             }
//         }
        
//         return count;
//     }
    
//     private Set<String> uniqueWords(String[] words){
//         Set<String> unique = new HashSet<>(), visited = new HashSet<>();
        
//         for(String w : words){
//             if(visited.contains(w)){
//                 unique.remove(w);
//             }
//             else{
//                 unique.add(w);
//             }
            
//             visited.add(w);
//         }
        
//         return unique;
//     }
}
