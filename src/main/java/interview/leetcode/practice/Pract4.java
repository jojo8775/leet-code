package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pract4 {
	 public List<String> removeSubfolders(String[] folder) {
	        Node head = new Node();
	        
	        Arrays.sort(folder, (a,b) -> {
	                if(a.length() == b.length()){
	                    return a.compareTo(b);
	                }
	            
	                return a.length() - b.length();
	            });
	        
	        List<String> result = new ArrayList<>();
	        for(String s : folder){
	            if(insert(s, head)){
	                if(!s.equals("/")){
	                    result.add(s);
	                }
	            }
	        }
	        
	        return result;
	    }
	    
	    private boolean insert(String str, Node node){
	        boolean unique = false;
	        
	        for(char ch : str.toCharArray()){
	            int idx = -1;
	            if(ch == '/'){
	                idx = 26;
	            }
	            else{
	                idx = (int)(ch - 'a');
	            }
	            
	            if(node.children[idx] == null)
	            {
	                unique = true;
	                node.children[idx] = new Node();
	            }
	            
	            node = node.children[idx];
	        }
	        
	        return unique;
	    }
	    
	    private static class Node{
	        Node[] children = new Node[27];
	    }
}
