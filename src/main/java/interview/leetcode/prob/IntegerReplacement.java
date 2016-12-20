package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:

Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1
Example 2:

Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1
 * @author jojo
 *
 */
public class IntegerReplacement {
    public int integerReplacement_r(int n) {
		return helper(n, new HashMap<Long, Integer>());
	}
	
	private int helper(long n, Map<Long, Integer> memorization){
		if(n == 1){
			return 0;
		}
		
		int count = 0;
		if(memorization.containsKey(n)){
			count = memorization.get(n);
			return count;
		}
		
		if(n%2==0){
			count = 1 + helper(n/2, memorization);
		}
		else {
			count = 1 + Math.min(helper(n+1, memorization), helper(n-1, memorization));
		}
		
		memorization.put(n, count);
		
		return count;
	}
	
    public int integerReplacement(int n) {
        Stack<Pair> stack = new Stack<Pair>();
    	stack.push(new Pair(n, 0));
    	
    	int minCount = Integer.MAX_VALUE;
    	
    	Map<Integer, Integer> memorization = new HashMap<Integer, Integer>();
    	
    	while(!stack.isEmpty()){
    		Pair top = stack.pop();
    		
    		if(memorization.containsKey(top.val)){
    		    if(top.count > memorization.get(top.val)){
    		        continue;
    		    }
    		}
    		
    		memorization.put(top.val, top.count);
    		
    		if(top.count == minCount){
    			continue;
    		}
    		
    		if(top.val % 2 == 0){
    			stack.push(new Pair(top.val/2, top.count+1));
    		}
    		else if(top.val > 1){
    			stack.push(new Pair(top.val+1, top.count+1));
    			stack.push(new Pair(top.val-1, top.count+1));
    		}
    		else{
    			minCount = Math.min(minCount, top.count);
    		}
    	}
    	
    	return minCount;
    }
    
    private static class Pair{
    	private int count; 
    	private int val;
    	public Pair(int val, int count){
    		this.count = count; 
    		this.val = val;
    	}
    }
}
