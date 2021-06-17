package interview.leetcode.prob;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
 * @author jojo
 *
 */
public class SimplifyPath {
	public String simplifyPath(String path) {
        if(path.isEmpty()){
            return "/";
        }
        
        // check if path is terminated by '/'
        if(path.charAt(path.length() - 1) == '/'){
            path = path.substring(0, path.length() - 1);
        }
        
        Stack<String> stack1 = new Stack<String>();
        
        int prev = 0;
        for(int i=0; i<path.length(); i++){
            if(path.charAt(i) == '/'){
                stack1.push(path.substring(prev, i));
                prev = i;
            }
        }
        
        if(prev != (path.length() - 1)){
            stack1.push(path.substring(prev));
        }
        
        Stack<String> stack2 = new Stack<String>();
        int back = 0;
        while(!stack1.isEmpty()){
            String str = stack1.pop();
            
            if(str.equals("/") || str.equals("/.") || str.isEmpty()){
                //no nothing
                continue;
            }
            
            if(str.equals("/..")){
                back++;
            }
            else{
                if(back > 0){
                    back --;
                }
                else{
                    stack2.push(str);
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
	
    public String simplifyPath_adv(String path) {
        String[] arr = path.split("/");
        
        // for(String s : arr){
        //     System.out.println(s);
        // }
        
        Deque<String> dQueue = new LinkedList<>();
        
        for(String s : arr){
            if(s.isEmpty() || s.equals(".")){
                continue;
            }
            
            if(s.equals("..")){
                if(!dQueue.isEmpty()){
                    dQueue.pollLast();
                }
            }
            else{
                dQueue.offerLast(s);
            }
        }
        
        
        StringBuilder sb = new StringBuilder();
        while(!dQueue.isEmpty()){
            sb.append("/").append(dQueue.pollFirst());
        }
        
        return sb.length() == 0 ? "/" : sb.toString();
    }
	
	public static void main(String[] args){
		System.out.println(new SimplifyPath().simplifyPath("//"));
	}
}
