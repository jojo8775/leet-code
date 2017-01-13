package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class WordSquare {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<List<String>>();
        Node root = new Node();
        for (String w : words) {
            addStr(w, root);
        }

        int len = words.length, rightMostRow = words[0].length() - 1;
        int[] idxArr = new int[len];
        
        boolean[] visited = new boolean[len];
        
        int curIdx = -1;
        
        Stack<Integer> stack = new Stack<Integer>();
        
        while(curIdx < len){
            String key = String.valueOf(words[curIdx].charAt(rightMostRow));
            
            if(isPrefix(key, root)){
                stack.push(curIdx);
                visited[curIdx] = true;
                
                while(!stack.isEmpty()){
                    int top = stack.peek();
                    
                }
            }
            
            curIdx++;
        }
        
        return result;
    }
    
    private void dfs(List<List<String>> result, boolean[] visited, String entry, int[] idxArr, String[] words){
    }

    private void addStr(String str, Node node) {
        Node curNode = node;
        for (char ch : str.toCharArray()) {
            if (!curNode.children.containsKey(ch)) {
                curNode.children.put(ch, new Node());
            }

            curNode = curNode.children.get(ch);
        }

        curNode.isWord = true;
    }

    private boolean isPrefix(String str, Node node) {
        Node curNode = node;
        for (char ch : str.toCharArray()) {
            Node childNode = curNode.children.get(ch);
            if (childNode == null) {
                return false;
            }

            curNode = childNode;
        }

        return true;
    }

    private static class Node {
        Map<Character, Node> children = new HashMap<Character, Node>();
        boolean isWord;
    }
    
    public static void main(String[] args){
        List<List<String>> result = new WordSquare().wordSquares(new String[]{"abat","baba","atan","atal"});
        
        for(List<String> ll : result){
            for(String s : ll){
                System.out.println(s);
            }
            
            System.out.println("=========================");
        }
    }
}
