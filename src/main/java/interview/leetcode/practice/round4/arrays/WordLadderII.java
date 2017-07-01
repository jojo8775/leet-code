package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        dict.add(beginWord);
        
        Set<String> visited1 = new HashSet<>(), visited2 = new HashSet<>();
        visited1.add(beginWord);
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(null, beginWord, 0));
        
        int prevCount = 0, curCount = 0, minCount = Integer.MAX_VALUE;
        
        List<List<String>> result = new ArrayList<>();
        
        while(!queue.isEmpty()){
            Node top = queue.poll();
            
            if(minCount < top.count){
                continue;
            }
            
            if(endWord.equals(top.word)){
                minCount = top.count;
                
                List<String> list = new ArrayList<>();
                list.add(top.word);
                top = top.prev;
                
                while(top != null){
                    list.add(0, top.word);
                    top = top.prev;
                }
                
                result.add(list);
                continue;
            }
            
            if(top.count > prevCount){
                visited1.addAll(visited2);   
                prevCount = top.count;
            }
            
            char[] cArr = top.word.toCharArray();
            for(int i=0; i<cArr.length; i++){
                char ref = cArr[i];
                for(int j=0; j<26; j++){
                    char temp = (char) ('a' + j);
                    if(temp != ref){
                        cArr[i] = temp;
                        
                        String str = String.valueOf(cArr);
                        if(dict.contains(str) && !visited1.contains(str)){
                            visited2.add(str);
                            queue.offer(new Node(top, str, top.count + 1));
                        }
                    }
                }
                
                cArr[i] = ref;
            }
        }
        
        return result;
    }
    
    private static class Node{
        Node prev;
        String word;
        int count;
        
        Node(Node prev, String word, int count){
            this.prev = prev;
            this.word = word;
            this.count = count;
        }
    }
}
