
public class FindFirstAndLastPosition {
	public int[] searchRange(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		
		while(left < right) {
			int mid = (right - left)/2 + left;
			
			if(nums[mid] < target) {
				left = mid + 1;
			}
			else{
				right = mid;
			}
		}
		
		if(nums[left] != target) {
			return new int[] {-1, -1};
		}
		
		int idx1 = left;
		
		right = nums.length - 1;
		while(left < right) {
			int mid = (right - left)/2 + left + 1;
			
			if(nums[mid] > target) {
				right = mid - 1;
			}
			else {
				left = mid;
			}
		}
		
		return new int[] {idx1, right};
	}
	
	public static void main(String[] args) {
		int[] result = new FindFirstAndLastPosition().searchRange(new int[] {5,7,7,8,8,10}, 8);
		System.out.println(result[0] + ", " + result[1]);
		
		result = new FindFirstAndLastPosition().searchRange(new int[] {5,7,7,8,8,10}, 4);
		System.out.println(result[0] + ", " + result[1]);
		
		result = new FindFirstAndLastPosition().searchRange(new int[] {5,7,7,8,8,10}, 11);
		System.out.println(result[0] + ", " + result[1]);
	}
}
