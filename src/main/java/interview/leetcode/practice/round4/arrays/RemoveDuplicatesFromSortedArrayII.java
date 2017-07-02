package interview.leetcode.practice.round4.arrays;

public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        if(nums.length < 2){
            return nums.length;
        }
        
        int idx = 0, dupCount = 0;
        for(int i=1; i<nums.length; i++){
            if(nums[i] == nums[idx]){
                if(dupCount++ < 1){
                    if(++idx != i){
                        swap(nums, idx, i);
                    }
                }
            }
            else{
                dupCount = 0;
                if(++idx != i){
                    swap(nums, idx, i);
                }
            }
        }
        
        return idx + 1;
    }
    
    private void swap(int[] arr, int num1, int num2){
        int temp = arr[num1];
        arr[num1] = arr[num2];
        arr[num2] = temp;
    }
}
