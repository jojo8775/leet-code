package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
 

Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2
 

Constraints:

1 <= s.length <= 5 * 104
1 <= words.length <= 5000
1 <= words[i].length <= 50
s and words[i] consist of only lowercase English letters.
Accepted
238,280
Submissions
469,246
 * 
 * Nov 17, 2024 - 12:49:53 PM
 * Jojo 
 */
public class NumberOfMatchingSubsequence {
	public int numMatchingSubseq_usingNodeStructure(String s, String[] words) {
        List<List<Node>> graph = new ArrayList<>();
        
        for(int i=0; i<26; i++){
            graph.add(new ArrayList<Node>());
        }
        
        for(String w : words){
            char ch = w.charAt(0);
            graph.get(ch - 'a').add(new Node(w, 0));
        }
        
        int result = 0;
        
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            
            List<Node> neiList = new ArrayList<>(graph.get(ch - 'a'));
            
            // dropping the previous calculated neighbours 
            graph.get(ch - 'a').clear();
            
            for(Node nei : neiList){
                nei.idx++;
                
                if(nei.idx == nei.word.length()){
                    result ++;
                }
                else{
                    char nextChar = nei.word.charAt(nei.idx);
                    graph.get(nextChar - 'a').add(nei);
                }
            }
        }
        
        return result;
    }
    
    private static class Node{
        String word;
        int idx;
        
        public Node(String word, int idx){
            this.word = word;
            this.idx = idx;
        }
    }
    
    
    public int numMatchingSubseq(String s, String[] words) {
        List<int[]>[] graph = new List[26];
        
        for(int i=0; i<26; i++){
            graph[i] = new ArrayList<int[]>();
        }
        
        for(int i=0; i<words.length; i++){
            char ch = words[i].charAt(0);
            
            // assigning each "Node" where 0: is the char index of the word and 1: is the index of the word
            graph[ch - 'a'].add(new int[] {0, i});
        }
        
        int result = 0;
        
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            
            List<int[]> neiList = new ArrayList<>(graph[ch - 'a']);
            graph[ch - 'a'].clear();
            
            for(int[] nei : neiList){
                nei[0]++; // moving to the next index of the word
                
                // this means reached the end of the current word. Time to put it in the result;
                if(nei[0] == words[nei[1]].length()){ 
                    result++;
                }
                else{
                    // time to put it on the new group based on the cur index char
                    char nextChar = words[nei[1]].charAt(nei[0]);
                    graph[nextChar - 'a'].add(nei);
                }
            }
        }
        
        return result;
    }
}
