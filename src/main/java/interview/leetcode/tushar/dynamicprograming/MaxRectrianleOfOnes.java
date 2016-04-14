package interview.leetcode.tushar.dynamicprograming;

import java.util.Stack;

public class MaxRectrianleOfOnes
{
	public static void main(String[] args){
		int[][] board = new int[4][5];
		board[0] = getArr(1,0,0,1,1,1);
		board[1] = getArr(1,0,1,1,0,1);
		board[2] = getArr(0,1,1,1,1,1);
		board[3] = getArr(0,0,1,1,1,1);
		
		System.out.println(maxArea(board));
	}
	
	private static int[] getArr(int ... i){
		return i;
	}
	
	private static int maxArea(int[][] arr){
		
		int[] row = new int[arr[0].length];
		int max = 0;
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[0].length; j++){
				row[j] = arr[i][j] == 0 ? 0 : (row[j] + arr[i][j]); 
			}
			
			max = Math.max(max, findMax(row));
		}
		
		return max;
	}
	
	private static int findMax(int[] arr){
		Stack<Integer> stack = new Stack<Integer>();
		int max = 0;
		for(int i=0; i<arr.length; i++){
			while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
				max = Math.max(max, (arr[stack.peek()] * (i - stack.pop())));
			}
			
			stack.push(i);
		}	
		
		int ref = arr.length;
		while(!stack.isEmpty()){
			int n = arr[stack.peek()] * (ref - stack.pop());
			max = Math.max(max, n);
		}
		
		return max;
	}
}
