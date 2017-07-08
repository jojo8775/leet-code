package interview.leetcode.practice.round4.arrays;

public class FindKthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private int quickSelect(int[] nums, int beg, int end, int k){
        if(beg > end){
            return -1;
        }
        
        int pivot = findPivot(beg, end);
        swap(nums, pivot, end);
        pivot = end;
        int left = beg;
        
        for(int i=beg; i<end; i++){
            if(nums[pivot] >= nums[i]){
                swap(nums, left, i);
                left++;
            }
        }
        
        swap(nums, left, pivot);
        if(left == k){
            return nums[k];
        }   
        
        if(left < k){
            return quickSelect(nums, left + 1, end, k);
        }
        else{
            return quickSelect(nums, beg, left - 1, k);
        }
    }
    
    private void swap(int[] nums, int idx1, int idx2){
        if(idx1 == idx2){
            return;
        }
        
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
    
    private int findPivot(int beg, int end){
        return beg + (int) (Math.random() * (end - beg + 1));
        // return end;
    }
}
