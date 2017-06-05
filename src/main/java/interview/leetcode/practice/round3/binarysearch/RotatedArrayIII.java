package interview.leetcode.practice.round3.binarysearch;

public class RotatedArrayIII {
    public int search(int[] nums, int target) {
        int beg = 0, end = nums.length - 1;
        
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            
            // System.out.println("Beg : " + beg + " End : " + end + " Mid : " + mid);
            
            if(nums[mid] == target){
                return mid;
            }
            else{
                if(nums[mid] < nums[end]){
                    if(target > nums[mid] && target <= nums[end]){
                        beg = mid + 1;
                    }
                    else{
                        end = mid - 1;
                    }
                }
                else{
                    if(target >= nums[beg] && target < nums[mid]){
                        end = mid - 1;
                    }
                    else{
                        beg = mid + 1;
                    }
                }
            }
        }
        
        return -1;
    }
}
