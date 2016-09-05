package interview.leetcode.prob;

import java.util.Arrays;

public class ShuffleAnArray {
	private int[] arrRef;
	private int[] arr;
	
	public ShuffleAnArray(int[] arr){
		this.arr = arr;
		arrRef = Arrays.copyOf(arr, arr.length);
	}
	
	public int[] shuffle(){
		for(int i=0; i<arr.length; i++){
			int rand = getRandom(i, arr.length - 1);
			if(i != rand){
				int temp = arr[i];
				arr[i] = arr[rand];
				arr[rand] = temp;
			}
		}
		
		return arr;
	}
	
	private int getRandom(int min, int max){
		return min + (int) (Math.random() * (max - min + 1));
	}
	
	public static void main(String[] args){
		ShuffleAnArray shuffleAnArray = new ShuffleAnArray(new int[]{1,2,3,4});
		print(shuffleAnArray.shuffle());
		print(shuffleAnArray.shuffle());
		print(shuffleAnArray.shuffle());
		print(shuffleAnArray.shuffle());
		print(shuffleAnArray.shuffle());
		print(shuffleAnArray.shuffle());
		print(shuffleAnArray.shuffle());
	}
	
	private static void print(int[] arr){
		for(int n : arr){
			System.out.print(n + ", ");
		}
		
		System.out.println();
	}
}
