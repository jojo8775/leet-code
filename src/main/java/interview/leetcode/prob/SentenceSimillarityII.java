package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * We can represent a sentence as an array of words, for example, the sentence "I am happy with leetcode" can be represented as arr = ["I","am",happy","with","leetcode"].

Given two sentences sentence1 and sentence2 each represented as a string array and given an array of string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that the two words xi and yi are similar.

Return true if sentence1 and sentence2 are similar, or false if they are not similar.

Two sentences are similar if:

They have the same length (i.e., the same number of words)
sentence1[i] and sentence2[i] are similar.
Notice that a word is always similar to itself, also notice that the similarity relation is transitive. For example, if the words a and b are similar, and the words b and c are similar, then a and c are similar.

 

Example 1:

Input: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"], similarPairs = [["great","good"],["fine","good"],["drama","acting"],["skills","talent"]]
Output: true
Explanation: The two sentences have the same length and each word i of sentence1 is also similar to the corresponding word in sentence2.
Example 2:

Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"], similarPairs = [["manga","onepiece"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
Output: true
Explanation: "leetcode" --> "platform" --> "anime" --> "manga" --> "onepiece".
Since "leetcode is similar to "onepiece" and the first two words are the same, the two sentences are similar.
Example 3:

Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"], similarPairs = [["manga","hunterXhunter"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
Output: false
Explanation: "leetcode" is not similar to "onepiece".
 

Constraints:

1 <= sentence1.length, sentence2.length <= 1000
1 <= sentence1[i].length, sentence2[i].length <= 20
sentence1[i] and sentence2[i] consist of lower-case and upper-case English letters.
0 <= similarPairs.length <= 2000
similarPairs[i].length == 2
1 <= xi.length, yi.length <= 20
xi and yi consist of English letters.
Accepted
55,488
Submissions
116,578
 * @author jojo
 * Jan 7, 2022 11:33:00 PM
 */
public class SentenceSimillarityII {
	public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if(sentence1.length != sentence2.length){
            return false;
        }
        
        Map<String, List<String>> map = new HashMap<>();
        for(List<String> e : similarPairs){
            List<String> val = map.get(e.get(0));
            if(val == null){
                val = new ArrayList<>();
                map.put(e.get(0), val);
            }
            
            val.add(e.get(1));
            
            val = map.get(e.get(1));
            if(val == null){
                val = new ArrayList<>();
                map.put(e.get(1), val);
            }
            
            val.add(e.get(0));
        }
        
        for(int i=0; i<sentence1.length; i++){
            String s1 = sentence1[i], s2 = sentence2[i];
            
            Stack<String> stack = new Stack<>();
            stack.push(s1);
            
            Set<String> visited = new HashSet<>();
            visited.add(s1);
            
            boolean matchFound = false;
            
            while(!stack.isEmpty()){
                String top = stack.pop();
                if(top.equals(s2)){
                    matchFound = true;
                    break;
                }
                
                for(String e : map.getOrDefault(top, new ArrayList<>())){
                    if(visited.add(e)){
                        stack.push(e);
                    }
                }
            }
            
            if(!matchFound){
                return false;
            }
        }
        
        return true;
    }
}
