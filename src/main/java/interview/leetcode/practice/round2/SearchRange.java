package interview.leetcode.practice.round2;

public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        return new int[]{findLowerBound(nums, target), findUpperBound(nums, target)};
    }
    
    private int findLowerBound(int[] nums, int target){
        int beg = 0, end = nums.length - 1;
        
        while(beg <= end){
            int mid = beg + (end - beg) / 2;
            
            if(target == nums[mid]){
                if(mid > 0 && nums[mid] == nums[mid - 1]){
                    end = mid - 1;
                }
                else{
                    return mid;
                }
            }
            else if(target > nums[mid]){
                beg = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        
        return -1;
    }
    
    private int findUpperBound(int[] nums, int target){
        int beg = 0, end = nums.length - 1;
        
        while(beg <= end){
            int mid = beg + (end - beg) / 2;
            
            if(target == nums[mid]){
                if(mid < end && nums[mid] == nums[mid + 1]){
                    beg = mid + 1;
                }
                else{
                    return mid;
                }
            }
            else if(target > nums[mid]){
                beg = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        
        return -1;
    }
}
