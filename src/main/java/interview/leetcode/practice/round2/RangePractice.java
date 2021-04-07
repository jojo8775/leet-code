package interview.leetcode.practice.round2;

public class RangePractice {
	public int[] findRange(int[] arr, int num) {
		int idx1 = findFloor(arr, num);
		
		if(idx1 == -1) {
			return new int[] {-1,-1};
		}
		
		int idx2 = findCelling(arr, num);
		
		return new int[] {idx1, idx2};
	}
	
	private int findFloor(int[] arr, int num) {
		int beg = 0, end = arr.length;
		
		while(beg < end) {
			int mid = beg + (end - beg)/2;
			
			if(arr[mid] < num) {
				beg = mid + 1;
			}
			else {
				end = mid;
			}
		}
		
		if(beg == arr.length || arr[beg] != num) {
			return -1;
		}
		
		return beg;
	}
	
	private int findCelling(int[] arr, int num) {
		int beg = 0, end = arr.length;
		
		while(beg < end - 1) {
			int mid = beg + (end - beg)/2 + 1;
			
			if(arr[mid] > num) {
				end = mid - 1;
			}
			else {
				beg = mid;
			}
		}
		
//		if(beg == arr.length || arr[beg] != num) {
//			return -1;
//		}
		
		return beg;
	}
	
	public static void main(String[] args) {
		int[] result = new RangePractice().findRange(new int[]{1,2,2,2,3,3,4,5}, 0);
		System.out.println(result[0] + "-" + result[1]);
	}
}
