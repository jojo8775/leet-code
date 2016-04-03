package interview.leetcode.tushar.assorted;

import java.util.Stack;

public class MaxRechtriangleInHistoram
{
	public static void main(String[] args){
		int[] arr = {1,2,2,3, 0, 4, 4};
		System.out.println(findMaxArea(arr));
	}
	
	private static int findMaxArea(int[] arr){
		
		Stack<Integer> stack = new Stack<Integer>();
		int maxArea = arr[0], currentArea = 0;
		int maxBegIndex = 0, maxEndIndex = 0;
		
		for(int i=0 ; i < arr.length; i++){
			if(stack.isEmpty() || arr[stack.peek()] <= arr[i]){
				stack.push(i);
			}
			else{
				while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
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
		
		int refIndex = arr.length;
		
		while(!stack.isEmpty()){
			int storedIndex = stack.pop();
			currentArea = arr[storedIndex] * (refIndex - storedIndex);
			if(currentArea > maxArea){
				maxArea = currentArea;
				maxBegIndex = storedIndex;
				maxEndIndex = refIndex;
			}
		}
		
		return maxArea;
	}
}
