package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.

 

Example 1:

Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4
Explanation: The two words can be "ab", "cd".
Example 3:

Input: words = ["a","aa","aaa","aaaa"]
Output: 0
Explanation: No such pair of words.
 

Constraints:

2 <= words.length <= 1000
1 <= words[i].length <= 1000
words[i] consists only of lowercase English letters.
Accepted
136,898
Submissions
243,919
 * @author jojo
 * Dec 8, 2021 10:14:17 PM
 */
public class MaximumProductOfWordLength {
	 public int maxProduct(String[] words) {
	        Map<Integer, Integer> wordMap = new HashMap<>();
	        
	        for(String word : words){
	            int hash = findHash(word);
	            
	            wordMap.put(hash, Math.max(wordMap.getOrDefault(hash, 0), word.length()));
	        }
	        
	        int result = 0;
	        
	        for(int x : wordMap.keySet()){
	            for(int y : wordMap.keySet()){
	                if((x & y) == 0){
	                    result = Math.max(result, wordMap.get(x) * wordMap.get(y));
	                }
	            }
	        }
	        
	        return result;
	    }
	    
	    private int findHash(String word){
	        int val = 0, len = word.length();
	        
	        for(int i=0; i<len; i++){
	            int bit = (int) (word.charAt(i) - 'a');
	            val |= 1 << bit;
	        }
	        
	        return val;
	    }
}
