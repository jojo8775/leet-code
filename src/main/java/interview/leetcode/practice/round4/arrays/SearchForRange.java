package interview.leetcode.practice.round4.arrays;

public class SearchForRange {
    public int[] searchRange(int[] nums, int target) {
        int upperLimit = upperLimit(nums, target);
        if(upperLimit == -1){
            return new int[]{-1, -1};
        }
        else{
            return new int[]{lowerLimit(nums, target), upperLimit};
        }
    }
    
    private int lowerLimit(int[] arr, int target){
        int beg = 0, end = arr.length - 1;
        
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            
            if(arr[mid] == target){
                if(mid > 0 && arr[mid] == arr[mid - 1]){
                    end = mid-1;
                }
                else{
                    return mid;
                }
            }
            else if(arr[mid] > target){
                end = mid - 1;
            }
            else{
                beg = mid + 1;
            }
        }
        
        return -1;
    }
    
    private int upperLimit(int[] arr, int target){
        int beg = 0, end = arr.length - 1;
        
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            
            if(arr[mid] == target){
                if(mid < arr.length - 1 && arr[mid] == arr[mid + 1]){
                    beg = mid+1;
                }
                else{
                    return mid;
                }
            }
            else if(arr[mid] > target){
                end = mid - 1;
            }
            else{
                beg = mid + 1;
            }
        }
        
        return -1;
    }
}
