package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of string words. Return all strings in words which is substring of another word in any order. 

String words[i] is substring of words[j], if can be obtained removing some characters to left and/or right side of words[j].

 

Example 1:

Input: words = ["mass","as","hero","superhero"]
Output: ["as","hero"]
Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
["hero","as"] is also a valid answer.
Example 2:

Input: words = ["leetcode","et","code"]
Output: ["et","code"]
Explanation: "et", "code" are substring of "leetcode".
Example 3:

Input: words = ["blue","green","bu"]
Output: []
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 30
words[i] contains only lowercase English letters.
It's guaranteed that words[i] will be unique.
Accepted
15,816
Submissions
26,239
 * @author jojo
 * May 26, 2020  11:56:26 PM
 */
public class StringMatchingInArray {
	public List<String> stringMatching_adv(String[] words) {
        StringBuilder sb = new StringBuilder();
        
        for(String word : words){
            sb.append(word).append("|");
        }
        
        String str = sb.toString();
        
        List<String> result = new ArrayList<>();
        
        for(String word : words){
            int firstIdx = str.indexOf(word);
            int secondIdx = str.lastIndexOf(word);
            
            if(firstIdx != secondIdx){
                result.add(word);
            }
        }
        
        return result;
    }
	
	
	public List<String> stringMatching(String[] words) {
        Arrays.sort(words, (a,b) -> b.length() - a.length());
        
        Node root = new Node();
        
        List<String> result = new ArrayList<>();
        
        for(String word : words){
            Node cur = root;
            System.out.println("Word: [ " + word + " ]");
            for(char ch : word.toCharArray()){
                if(cur.children[ch - 'a'] == null){
                    System.out.println("child char: [ " + ch + " ]");
                    break;
                }
                
                cur = cur.children[ch - 'a'];
            }
            
            System.out.println("last char node flag:[ " + cur.isWord + " ]");
            
            if(cur.isWord){
                result.add(word);
            }
            else{
                indexWord(word, root);
            }
        }
        
        return result;
    }
    
    private void indexWord(String word, Node node){
        for(int i=0; i<word.length(); i++){
            String subStr = word.substring(i);
            Node cur = node;
            for(char ch : subStr.toCharArray()){
                if(cur.children[ch - 'a'] == null){
                    cur.children[ch - 'a'] = new Node();
                }
                
                cur = cur.children[ch - 'a'];
            }
            
            if(i==0){
                cur.isWord = true;
                System.out.println("word indexed:[ " + subStr + " ]");
            }
        }
    }
    
    private static class Node{
        Node[] children = new Node[26];
        boolean isWord = false;
    }
    
    public static void main(String[] args) {
    	List<String> result = new StringMatchingInArray().stringMatching(new String[] {"mass","as","hero","superhero"});
    	result.forEach(x -> System.out.println(x));
    }
}
