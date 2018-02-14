package interview.leetcode.prob;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.


 * @author jojo
 *Feb 4, 201812:39:15 AM
 */
public class KthLargestElelment_QuickSelection {
    public int findKthLargest(int[] nums, int k) {
        return quickSort(0, nums.length - 1, nums, nums.length - k);
    }
    
    private int quickSort(int beg, int end, int[] nums, int k){
        int pivot = random(beg, end);
        swap(nums, pivot, end);
        
        int left = beg;
        for(int i=beg; i<end; i++){
            if(nums[i] <= nums[end]){
                swap(nums, i, left++);
            }
        }
        
        swap(nums, left, end);
        if(left == k){
            return nums[left];
        }
        
        if(left > k){
            return quickSort(beg, left - 1, nums, k);
        }
        
        return quickSort(left+1, end, nums, k);
    }
    
    private void swap(int[] arr, int i, int j){
        if(i == j){
            return;
        }
        
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private int random(int beg, int end){
        return beg + (int) (Math.random() * (end - beg));
    }
}
