package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSquare {
    public List<List<String>> wordSquares(String[] words) {
        Node root = new Node();
        int width = words[0].length();
        for (String w : words) {
            addString(root, w);
        }

        List<List<String>> result = new ArrayList<List<String>>();
        List<String> placeHolder = new ArrayList<>();
        
        for(String word : words){
            placeHolder.add(word);
            dfs(result, placeHolder, root, width);
            placeHolder.remove(placeHolder.size() - 1);
        }
        return result;
    }

    private void dfs(List<List<String>> result, List<String>placeHolder, Node root, int width) {
        if (placeHolder.size() == width) {
            result.add(new ArrayList<String>(placeHolder));
            return;
        }
        
        int idx = placeHolder.size();
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<idx; i++){
            sb.append(placeHolder.get(i).charAt(idx));
        }
        
        List<String> commonPrefixWords = findCommonPrefixWords(root, sb.toString());
        for(String word : commonPrefixWords){
            placeHolder.add(word);
            dfs(result, placeHolder, root, width);
            placeHolder.remove(placeHolder.size() - 1);
        }
    }

    private List<String> findCommonPrefixWords(Node root, String str) {
        Node curNode = root;
        for (char ch : str.toCharArray()) {
            Node childNode = curNode.children.get(ch);
            if (childNode == null) {
                return new ArrayList<String>();
            }

            curNode = childNode;
        }

        return new ArrayList<String>(curNode.commonPrefixWord);
    }

    private void addString(Node root, String str) {
        Node curNode = root;

        for (char ch : str.toCharArray()) {
            Node childNode = curNode.children.get(ch);

            if (childNode == null) {
                childNode = new Node();
                curNode.children.put(ch, childNode);
            }
            
            childNode.commonPrefixWord.add(str);
            curNode = childNode;
        }
    }

    private static class Node {
        List<String> commonPrefixWord = new ArrayList<String>();
        Map<Character, Node> children = new HashMap<>();
    }
    
    public static void main(String[] args){
        List<List<String>> result = new WordSquare().wordSquares(new String[] {"abat", "baba", "atan", "atal"});
        
        for(List<String> s : result){
            for(String ss : s){
                System.out.println(ss);
            }
            
            System.out.println("==================");
        }
    }
}
