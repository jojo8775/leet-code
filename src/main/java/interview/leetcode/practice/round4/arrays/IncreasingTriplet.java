package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        if(nums.length < 3){
            return false;
        }
        
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        
        for(int i=1; i<nums.length; i++){
            int idx = binarySearch(list, nums[i]);
            
            if(idx == list.size()){
                list.add(nums[i]);
            }
            else{
                list.set(idx, nums[i]);
            }
            
            if(list.size() == 3){
                return true;
            }
        }
        
        return false;
    }
    
    private int binarySearch(List<Integer> list, int target){
        int beg = 0, end = list.size() - 1;
        
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            
            if(list.get(mid) == target){
                return mid;
            }
            
            if(list.get(mid) > target){
                end = mid - 1;
            }
            else{
                beg = mid + 1;
            }
        }
        
        return beg;
    }
}
