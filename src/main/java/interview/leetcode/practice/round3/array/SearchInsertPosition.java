package interview.leetcode.practice.round3.array;

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int beg = 0, end = nums.length - 1; 
        
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            
            if(nums[mid] < target){
                beg = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        
        return beg;
    }
}
