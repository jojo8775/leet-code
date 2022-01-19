package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

/**
 * Sometimes people repeat letters to represent extra feeling. For example:

"hello" -> "heeellooo"
"hi" -> "hiiii"
In these strings like "heeellooo", we have groups of adjacent letters that are all the same: "h", "eee", "ll", "ooo".

You are given a string s and an array of query strings words. A query word is stretchy if it can be made to be equal to s by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is three or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has a size less than three. Also, we could do another extension like "ll" -> "lllll" to get "helllllooo". If s = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = s.
Return the number of query strings that are stretchy.

 

Example 1:

Input: s = "heeellooo", words = ["hello", "hi", "helo"]
Output: 1
Explanation: 
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
Example 2:

Input: s = "zzzzzyyyyy", words = ["zzyy","zy","zyy"]
Output: 3
 

Constraints:

1 <= s.length, words.length <= 100
1 <= words[i].length <= 100
s and words[i] consist of lowercase letters.
Accepted
88,500
Submissions
191,051
 * @author jojo
 * Jan 18, 2022 10:26:03 PM
 */
public class ExpressiveWords {
	 public int expressiveWords(String s, String[] words) {
	        List<Pair<Character, Integer>> l1 = findPairs(s);
	        int count = 0, len = l1.size();
	        
	        for(String w : words){
	            List<Pair<Character, Integer>> l2 = findPairs(w);
	            
	            boolean match = true;
	            
	            if(l2.size() == len){
	                for(int i=0; i<len; i++){
	                    Pair<Character, Integer> p1 = l1.get(i), p2 = l2.get(i);
	                    
	                    if(p1.getKey() != p2.getKey()){
	                        match = false;
	                        break;
	                    }
	                    
	                    int v1 = p1.getValue(), v2 = p2.getValue();
	                    if(v1 != v2 && (v1 < v2 || v1 < 3)){
	                        match = false;
	                        break;
	                    }
	                }
	                
	                if(match){
	                    // System.out.println(w);
	                    count++;
	                }
	            }
	        }
	        
	        return count;
	    }
	    
	    private List<Pair<Character, Integer>> findPairs(String s){
	        int count = 1;
	        char ch = s.charAt(0);
	        
	        List<Pair<Character, Integer>> list = new ArrayList<>();
	        
	        for(int i=1; i<s.length(); i++){
	            if(s.charAt(i) == ch){
	                count++;
	            }
	            else{
	                list.add(new Pair<Character, Integer>(ch, count));
	                
	                count = 1;
	                ch = s.charAt(i);
	            }
	        }
	        
	        list.add(new Pair<Character, Integer>(ch, count));
	        
	        return list;
	    }
}
