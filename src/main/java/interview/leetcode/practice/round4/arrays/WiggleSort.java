package interview.leetcode.practice.round4.arrays;

public class WiggleSort {
    public void wiggleSort(int[] nums) {
        int flag = 1;
        for(int i=1; i<nums.length; i++){
            if(flag == 1){
                flag = 0;
                if(nums[i] < nums[i-1]){
                    swap(nums, i, i-1);
                }
            }
            else{
                flag = 1;
                if(nums[i - 1] < nums[i]){
                    swap(nums, i, i-1);
                }
            }
        }
    }
    
    private void swap(int[] arr, int idx1, int idx2){
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}
