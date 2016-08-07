package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
 * @author jojo
 *
 */
public class WordDictionary {
    private TrieNode root;
    
    public WordDictionary(){
        root = new TrieNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode node = root;
        
        for(char ch : word.toCharArray()){
            TrieNode temp = node.map.get(ch);
            if(temp == null){
                temp = new TrieNode();
                node.map.put(ch, temp);
            }
            
            node = temp;
        }
        
        node.isEnd = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        TrieNode node = root;
        return search(word, 0, node);
    }
    
    private boolean search(String word, int idx, TrieNode node){
        char ch = word.charAt(idx);
        if(ch != '.'){
            TrieNode temp = node.map.get(ch);
            
            if(temp == null){
                return false;
            }
            
            if(idx == word.length() - 1){
                return temp.isEnd;
            }
            
            return search(word, idx + 1, temp);
        }
        else{
            for(Map.Entry<Character, TrieNode> e : node.map.entrySet()){
                TrieNode temp = e.getValue();
                if(idx != word.length() - 1 && search(word, idx + 1, temp)){
                    return true;
                }
                else if(idx == word.length() - 1 && temp.isEnd){
                    return true;
                }
            }
            
            return false;
        }
    }
    
    private static class TrieNode{
        Map<Character, TrieNode> map = new HashMap<Character, TrieNode>();
        boolean isEnd;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
