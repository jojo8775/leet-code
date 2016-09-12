package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.management.Query;

public class DecodeString {
    public String decodeString(String s) {
    	Stack<Entry> stack = new Stack<Entry>();
    	stack.push(new Entry(1, new StringBuilder()));
    	
    	int idx = 0, val = 0;
    	while(idx < s.length()){
    		char ch = s.charAt(idx++);
    		
    		if(ch >= '0' && ch <= '9'){
    			val = (val * 10) + (int)(ch - '0');
    		}
    		else if(ch == '['){
    			stack.push(new Entry(val, new StringBuilder()));
    			val = 0;
    		}
    		else if (ch == ']'){
    			StringBuilder sb1 = new StringBuilder();
    			Entry top = stack.pop();
    			String str = top.sb.toString();
    			while(top.val > 0){
    				sb1.append(str);
    				top.val--;
    			}
    			
    			stack.peek().sb.append(sb1);
    		}
    		else{
    			stack.peek().sb.append(ch);
    		}
    	}
    	
    	return stack.peek().sb.toString();
    }
    
    private static class Entry{
    	int val;
    	StringBuilder sb;
    	
    	public Entry(int val, StringBuilder sb){
    		this.val = val;
    		this.sb = sb;
    	}
    }
    
    public static void main(String[] args){
    	System.out.println(new DecodeString().decodeString("2[ae3[b]]ef"));
    	
    	Queue<Integer> q = new LinkedList<>();
    	q.add(null);
    	q.add(null);
    	q.add(null);
    }
}
