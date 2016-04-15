package interview.leetcode.tushar.assorted;

public class LongestSubSequeneInNLogN
{
	public static void main(String[] args){
		int[] arr = {3,4,-1, 5,8,2,3,12,7,9,10};
		System.out.println(findLongestSubSequence(arr));
	}
	
	private static int findLongestSubSequence(int[] arr){
		
		if(arr.length == 0){
			return 0;
		}
		
		int[] temp = new int[arr.length]; 
		int[] result = new int[arr.length];
		int length = 0, tempCount = 0;
		
		result[0] = -1;
		
		for(int i=1; i<arr.length; i++){
			result[i] = -1;
			
			if(arr[i] > arr[temp[tempCount]]){
				temp[++tempCount] = i;
				length++;
				result[i] = temp[tempCount - 1];
			}
			else{
				int index = findCelling(temp, arr, 0, tempCount, arr[i]);
				temp[index] = i;
				if(index > 0){
					result[i] = temp[index - 1];
				}
			}
		}

		int resultIndex = temp[tempCount];
		while(resultIndex != -1){
			System.out.print(arr[resultIndex] + ", ");
			resultIndex = result[resultIndex];
		}
		System.out.println();	
		
		return length + 1;
	}
	
	private static int findCelling(int[] temp, int[] arr, int beg, int end, int key){
		if(beg >= end){
			return beg;
		}
		
		int mid = (beg + end) / 2;
		
		if(arr[temp[mid]] == key){
			return mid;
		}
		else if(arr[temp[mid]] > key){
			if((mid - 1) >= beg && arr[temp[mid - 1]] < key){
				return mid;
			}
			
			return findCelling(temp, arr, beg, mid - 1, key);
		}
		else{
			return findCelling(temp, arr, mid + 1, end, key);
		}
	}
}
