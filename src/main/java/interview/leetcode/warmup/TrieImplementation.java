package interview.leetcode.warmup;

import java.util.HashMap;
import java.util.Map;

public class TrieImplementation {
    private Node head = new Node();
    
    public void addWord(String word){
        Node currentNode = head;
        for(char ch : word.toCharArray()){
            if(!currentNode.children.containsKey(ch)){
                currentNode.children.put(ch, new Node());
            }
            
            currentNode = currentNode.children.get(ch);
        }
        
        currentNode.isEnd = true;
    }
    
    public boolean isPrefix(String str){
        Node currentNode = head;
        for(char ch : str.toCharArray()){
            if(!currentNode.children.containsKey(ch)){
                return false;
            }
            currentNode = currentNode.children.get(ch);
        }
        
        return !currentNode.isEnd;
    }
    
    public boolean isWord(String str){
        Node currentNode = head;
        for(char ch : str.toCharArray()){
            if(!currentNode.children.containsKey(ch)){
                return false;
            }
            
            currentNode = currentNode.children.get(ch);
        }
        
        return currentNode.isEnd;
    }
    
    private static class Node{
        public Map<Character, Node> children = new HashMap<Character, Node>();
        public boolean isEnd;
    }
    
    public static void main(String[] args){
        TrieImplementation trie = new TrieImplementation();
        trie.addWord("sand");
        trie.addWord("sands");
        trie.addWord("sound");
        
        System.out.println("is boat a word : " + trie.isWord("boat"));
        System.out.println("is soun a word : " + trie.isWord("soun"));
        System.out.println("is soun a prefix : " + trie.isPrefix("soun"));
        System.out.println("is sound a word : " + trie.isWord("sound"));
    }
}
