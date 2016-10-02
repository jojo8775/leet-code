package interview.leetcode.prob.paid;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].

Show Company Tags
Show Tags
Show Similar Problems

 * @author jojo
 *
 */
public class WiggleSort {
	// taking iterative approach as recursive one get out of stack runtime.
	public void wiggleSort(int[] nums) {
        for(int i=1; i<nums.length; i++){
            //if 'i' is even then it has be greater than prev 
            if(i%2 == 0 && nums[i-1] < nums[i]){
                swap(nums, i, i-1);
            }
            // if 'i' is odd the it has to be lesser than prev
            else if(i%2 == 1 && nums[i-1] > nums[i]){
                swap(nums, i, i-1);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
	
	public static void main(String[] args){
		int[] arr = {3, 5, 2, 1, 6, 4};
		new WiggleSort().wiggleSort(arr);
		
		for(int n : arr){
			System.out.print(n + ", ");
		}
	}
}
