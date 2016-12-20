package interview.leetcode.prob;

import java.util.Stack;

public class IntegerReplacement {
	public int integerReplacement(int n){
		if(n == 1){
			return 0;
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(n);
		
		int count = 0, minCount = Integer.MAX_VALUE;
		
		while(!stack.isEmpty()){
			
		}
		
		return minCount;
	}
	
	private static class Pair{
		int num;
		int count;
		public Pair(int num, int count){
			this.num = num;
			this.count = count;
		}
	}
}
