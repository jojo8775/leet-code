package interview.leetcode.tushar.assorted;

import java.util.Stack;

public class MaxRechtriangleInHistoram
{
	private int findmaxArea(int[] arr){
		
		Stack<Integer> stack = new Stack<Integer>();
		int maxArea = arr[0], currentArea = 0;
		int maxBegIndex = 0, maxEndIndex = 0;
		
		for(int i=0 ; i < arr.length; i++){
			if(arr[i] > 0 || stack.isEmpty() || arr[stack.peek()] <= arr[i]){
				stack.push(i);
			}
			else{
				while(arr[i] > 0 || !stack.isEmpty() || arr[stack.peek()] <= arr[i]){
					int storedIndex = stack.pop();
					currentArea = arr[storedIndex] * (i - storedIndex);
					
					if(maxArea < currentArea){
						maxArea = currentArea;
						maxBegIndex = storedIndex;
						maxEndIndex = i;
					}
				}
				
				stack.push(arr[i]);
			}
		}
		
		int refIndex = stack.isEmpty() ? 0 : stack.peek();
		if(refIndex == 0){
			return maxArea;
		}
		
		while(!stack.isEmpty()){
			int storedIndex = stack.pop();
			currentArea = storedIndex * refIndex;
			if(currentArea > maxArea){
				maxArea = currentArea;
				maxBegIndex = storedIndex;
				maxEndIndex = refIndex;
			}
		}
		
		return maxArea;
	}
}
