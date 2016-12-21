package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a list of words, please write a program that returns all concatenated words in the given list of words.

A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.
 * @author jojo
 *
 */
public class ConcatenatedWords {
    private List<String> findAllConcatenatedWordsInADict_DP(String[] words) {
        // sort input as longer words will be made up of smaller words
        Arrays.sort(words, new Comparator<String>(){
           public int compare(String s1, String s2){
               return s1.length() - s2.length();
           }
        });
        
        Set<String> dict = new HashSet<String>();
        List<String> result = new ArrayList<String>();
        
        for(String word : words){
            validateWord(result, dict, word);
            dict.add(word);
        }
        
        return result;
    }
    
    private void validateWord(List<String> result, Set<String> dict, String word){
        if(dict.isEmpty()){
            return;
        }
        
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        int count = 0;
        
        for(int i=1; i<= word.length(); i++){
            for(int j=0; j<i; j++){
                // this ensures that word is completely made up of other words 
                if(dp[j] && dict.contains(word.substring(j, i))){
                    dp[i] = true;
                    count ++;
                    break;
                }
            }
        }
        
        if(count > 1 && dp[word.length()] == true){
            result.add(word);
        }
    }
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        TrieNode root = new TrieNode();

        // adding words to the trie
        for (String word : words) {
            if(word.isEmpty()){
                continue;
            }
            
            addWord(root, word);
        }

        List<String> result = new ArrayList<String>();
        searchWord(result, root, root, root, 0);

        return result;
    }

    private void searchWord(List<String> result, TrieNode node1, TrieNode node2, TrieNode root, int wordCount){
        // if node2 has participated in word combination then we need to terminate that look up.
        if(node2.combo){
            return;
        }
        
        if(node2.end){
            // if the current word is a combination atleast two words and not been added then add it to the result 
            if (node1.end && !node1.added && wordCount + 1 > 1) {
                result.add(node1.word);
                node1.combo = true;
                node1.added = true;
            }

            // we need to keep searching with rest of node1 as it may be combination of more than two words
            searchWord(result, node1, root, root, wordCount + 1);
        }
        
        // this will do dfs for each node. 
        for(int i=0; i<26; i++){
            if(node1.nodes[i] != null && node2.nodes[i] != null){
                searchWord(result, node1.nodes[i], node2.nodes[i], root, wordCount);
            }
        }
    }
    
    public void addWord(TrieNode root, String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()) {
            if(node.nodes[ch - 'a'] == null){
                node.nodes[ch - 'a'] = new TrieNode();
            }
            
            node = node.nodes[ch - 'a'];
        }

        node.end = true;
        node.word = word;
    }

    private static class TrieNode {
        TrieNode[] nodes = new TrieNode[26];
        String word;
        boolean combo = false, end = false, added = false;
    }

    public static void main(String[] args) {
        List<String> result = new ConcatenatedWords().findAllConcatenatedWordsInADict(new String[] { "cat", "cats",
                "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat" });

        for (String s : result) {
            System.out.println(s);
        }
        
        System.out.println("Calling DP method:");
        
        result = new ConcatenatedWords().findAllConcatenatedWordsInADict_DP(new String[] { "cat", "cats",
                "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat" });

        for (String s : result) {
            System.out.println(s);
        }
    }
}
