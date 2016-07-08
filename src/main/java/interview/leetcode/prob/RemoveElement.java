package interview.leetcode.prob;

/**
 * Given an array and a value, remove all instances of that value in place and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example:
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.
 * @author jojo
 *
 */
public class RemoveElement {
	public int remove(int[] arr, int val){
		int end = arr.length;
		
		for(int i=end - 1; i>=0; i--){
			if(arr[i] == val){
				int temp = arr[--end];
				arr[end] = arr[i];
				arr[i] = temp;
			}
		}
		
		return end;
	}
	
	public static void main(String[] args){
		int[] arr = {1,3,2,2,3};
		int r = new RemoveElement().remove(arr, 3);
		System.out.println(r);
	}
}
