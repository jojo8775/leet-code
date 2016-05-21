package interview.leetcode.tushar.assorted;

import java.util.Stack;

public class MaxRechtriangleInHistoram
{
	public static void main(String[] args){
//		int[] arr = {1,2,2,3, 0, 4, 4};
		int[] arr = { 90, 58, 69, 70, 82, 100, 13, 57, 47, 18 };
		System.out.println(findMaxArea(arr));
	}
	
	private static int findMaxArea(int[] arr){
		
		Stack<Integer> stack = new Stack<Integer>();
		
		int idx = 0, topIdx = 0, maxArea = arr[0];
		while(idx < arr.length){
			if(stack.isEmpty() || arr[stack.peek()] <= arr[idx]){
				stack.push(idx++);
			}
			else{
				topIdx = stack.pop();
				
				if(stack.isEmpty()){
					maxArea = Math.max(maxArea, arr[topIdx] * idx);
				}
				else{
					maxArea = Math.max(maxArea, arr[topIdx] * (idx - stack.peek() - 1));
				}
			}
		}
		
		while(!stack.isEmpty()){
			topIdx = stack.pop();
			
			if(stack.isEmpty()){
				maxArea = Math.max(maxArea, arr[topIdx] * idx);
			}
			else{
				maxArea = Math.max(maxArea, arr[topIdx] * (idx - stack.peek() - 1));
			}
		}
		
		return maxArea;
	}
}
