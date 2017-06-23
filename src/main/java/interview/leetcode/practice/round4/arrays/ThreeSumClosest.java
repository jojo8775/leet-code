package interview.leetcode.practice.round4.arrays;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        
        int diff = Integer.MAX_VALUE, result = 0;
        for(int i=0; i<nums.length - 2; i++){
            int beg=i+1, end=nums.length - 1;
            
            while(beg < end){
                int val = nums[i] + nums[beg] + nums[end];
                
                if(diff > Math.abs(target - val)){
                    diff = Math.abs(target - val);
                    result = val;
                }
                
                if(val > target){
                    end--;
                }
                else{
                    beg++;
                }
            }
        }
        
        return result;
    }
}
