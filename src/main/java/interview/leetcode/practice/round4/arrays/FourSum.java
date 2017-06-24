package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == 0){
            return result;
        }
        
        Arrays.sort(nums);
        
        for(int i=0; i<nums.length - 3; i++){
            if(i > 0 && nums[i-1] == nums[i]){
                continue;
            }
            
            for(int j=i+1; j<nums.length - 2; j++){
                if(j > i+1 && nums[j-1] == nums[j]){
                    continue;
                }
                
                int beg = j+1, end = nums.length - 1;
                
                while(beg < end){
                    int val = nums[i] + nums[j] + nums[beg] + nums[end];
                    if(val == target){
                        result.add(Arrays.asList(nums[i], nums[j], nums[beg], nums[end]));
                        end--;
                        beg++;
                        
                        while(beg < end && nums[beg] == nums[beg - 1]){
                            beg++;
                        }
                        
                        while(beg < end && nums[end] == nums[end + 1]){
                            end--;
                        }
                    }
                    else if(val > target){
                        end--;
                    }
                    else{
                        beg++;
                    }
                }
            }
        }
        
        return result;
    }
}
