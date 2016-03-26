package interview.leetcode.tushar.dynamicprograming;

import java.util.ArrayList;
import java.util.List;

public class SubsetSumProblem
{
	public static void main(String[] args){
		int[] nums = {2,3,7,8,10};
		System.out.println(subSetExist(nums, 11));
	}
	
	private static boolean subSetExist(int[] nums, int total){
		
		boolean[][] board = new boolean[nums.length+1][total+1];
		
		for(int i=1; i<=nums.length; i++){
			for(int j=0; j<=total; j++){
				if(j==0 || nums[i-1] == j || board[i-1][j]){
					board[i][j] = true;
				}
				else if(j<nums[i-1]){
					board[i][j] = false;
				}
				else {
					board[i][j] = board[i-1][j-nums[i-1]];
				}
			}
		}
		
		List<Integer> subSet = new ArrayList<Integer>();
		int x=total, y = nums.length;
		while(x>0 && y>0){
			if(board[y][x] == true && board[y-1][x] == false){
				subSet.add(nums[y-1]);
				x -= nums[y-1];
			}
			y--;
		}
		
		for(int i : subSet){
			System.out.print(i + ", ");
		}
		
		System.out.println();
		
		return board[nums.length][total];
	}
}
