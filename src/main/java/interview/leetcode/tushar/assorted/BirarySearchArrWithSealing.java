package interview.leetcode.tushar.assorted;

public class BirarySearchArrWithSealing
{
	public static void main(String[] args){
		int[] arr = {2,4,6,8,10};
		System.out.println(findElementOrSealing(arr, 10, 0, arr.length - 1));
	}
	
	private static int findElementOrSealing(int[] arr, int key, int beg, int end){
		if(beg == end){
			return arr[beg];
		}
		
		int mid = (beg + end) / 2;
		if(arr[mid] == key){
			return key;
		}
		
		if(key > arr[mid]){
			return findElementOrSealing(arr, key, mid+1, end);
		}
		else{
			return findElementOrSealing(arr, key, beg, end - 1);
		}
	}
}
