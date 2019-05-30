package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Solutions {
	public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        boolean missMatchFound = false;
        Set<Character> chars = new HashSet<>();
        
        for(String word : words){
            for(char ch : word.toCharArray()){
                chars.add(ch);
            }
        }
        
        for(int i=0; i<words.length-1; i++){
            String current = words[i];
            String next = words[i+1];
            
            int minLen = Math.min(current.length(), next.length());
            
            for(int j=0; j<minLen; j++){
                char c1 = current.charAt(j);
                char c2 = next.charAt(j);
                
                if(c1 == c2){
                    continue;
                }
                
                if(!graph.containsKey(c2)){
                    graph.put(c2, new HashSet<Character>());
                }
                
                graph.get(c2).add(c1);
                missMatchFound = true;
                break;
            }
            
            if(!missMatchFound && current.length() > next.length()){
                return "";
            }
        }
        
        PriorityQueue<Character> queue = new PriorityQueue<>();
        
        for(char ch : chars){
            if(!graph.containsKey(ch)){
                queue.offer(ch);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            char top = queue.poll();
            sb.append(top);
            
            List<Character> temp = new ArrayList<>();
            for(Map.Entry<Character, Set<Character>> entry : graph.entrySet()){
                if(entry.getValue().contains(top)){
                    entry.getValue().remove(top);
                }
                
                if(entry.getValue().isEmpty()){
                    temp.add(entry.getKey());
                }
            }
            
            temp.forEach( x -> {
               graph.remove(x);
                queue.offer(x);
            });
        }
        
        if(sb.length() != chars.size()){
            return "";
        }
        
        return sb.toString();
    }
}
