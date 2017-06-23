package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == 0){
            return result;
        }
        
        Arrays.sort(nums);
        
        for(int i=0; i<nums.length - 2; i++){
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            
            int beg = i + 1, end = nums.length - 1;
            while(beg < end){
                int val = nums[i] + nums[beg] + nums[end];
                if(val == 0){
                    result.add(Arrays.asList(nums[i], nums[beg], nums[end]));
                    beg++;
                    end--;
                    while(beg < end && nums[beg] == nums[beg-1]){
                        beg++;
                    }
                    
                    while(beg < end && nums[end] == nums[end + 1]){
                        end--;
                    }
                }
                else if(val > 0){
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
