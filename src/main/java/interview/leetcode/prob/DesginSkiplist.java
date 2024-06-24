package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Jun 22, 2024 - 11:53:08 AM
 * Jojo 
 */
public class DesginSkiplist {
	public class Skiplist {
	    private Map<Integer, Stack<Integer>> map = new HashMap<>();
	    public Skiplist() {
	        
	    }
	    
	    public boolean search(int target) {
	        return map.containsKey(target);
	    }
	    
	    public void add(int num) {
	        map.computeIfAbsent(num, v -> new Stack<Integer>()).push(num);
	    }
	    
	    public boolean erase(int num) {
	        Stack<Integer> val = map.get(num);
	        
	        if(val == null){
	            return false;
	        }
	        
	        val.pop();
	        if(val.isEmpty()){
	            map.remove(num);
	        }
	        
	        return true;
	    }
	}

}
