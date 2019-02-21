package interview.leetcode.practice;

import java.util.Stack;

public class CorrectPath {
	class Solution {
	    public String simplifyPath(String path) {
	        if(path.isEmpty()){
	            return "/";
	        }
	        
	        if(path.charAt(path.length() - 1) == '/'){
	            path = path.substring(0,path.length() - 1);
	        }
	        
	        Stack<String> stack1 = new Stack<>();
	        int prev = 0;
	        for(int i=0; i<path.length(); i++){
	            if(path.charAt(i) == '/'){
	                stack1.push(path.substring(prev, i));
	                prev = i;
	            }
	        }
	        
	        if(prev != path.length() - 1){
	            stack1.push(path.substring(prev));
	        }
	        
	        Stack<String> stack2 = new Stack<>();
	        int back = 0;
	        
	        while(!stack1.isEmpty()){
	            String top = stack1.pop();
	            
	            if(top.equals("/") || top.equals("./") || top.equals("")){
	                continue;
	            }
	            
	            if(top.equals("../")){
	                back++;
	            }
	            else{
	                if(back > 0){
	                    back--;
	                }
	                else{
	                    stack2.push(top);
	                }
	            }
	        }
	        
	        if(stack2.isEmpty()){
	            return "/";
	        }
	        
	        StringBuilder sb = new StringBuilder();
	        
	        while(!stack2.isEmpty()){
	            sb.append(stack2.pop());
	        }
	        
	        return sb.toString();
	    }
	}
}
