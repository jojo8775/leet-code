package interview.leetcode.practice.round4.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class FormSentence {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(null, 0));
        
        List<String> result = new ArrayList<>();
        
        int len = s.length();
            
        while(!stack.isEmpty()){
            Node top = stack.pop();
            
            for(String word : wordDict){
                if(s.startsWith(word, top.idx)){
                    Node node = new Node(word, top.idx + word.length());
                    node.prev = top;
                    
                    if(node.idx == len){
                        StringBuilder sb = new StringBuilder(node.word);
                        node = node.prev;
                        
                        while(node != null && node.word != null){
                            sb.insert(0, " ");
                            sb.insert(0, node.word);
                            node = node.prev;
                        }
                        
                        result.add(sb.toString());
                    }
                    else {
                    	stack.push(node);
                    }
                }
            }
        }
        
        return result;
    }
    
    private static class Node{
        String word;
        int idx;
        Node prev;
        
        public Node(String word, int idx){
            this.word = word;
            this.idx = idx;
        }
    }
    
    public static void main(String[] args) {
    	List<String> result = new FormSentence().wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
    	result.forEach(s -> System.out.println(s));
    }
}
