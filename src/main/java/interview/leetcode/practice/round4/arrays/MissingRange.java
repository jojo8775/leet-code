package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class MissingRange {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        long expected = lower;
        for(int i=0; i<nums.length; i++){
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            if(nums[i] != expected){
                if(expected == nums[i] - 1){
                    result.add(String.valueOf(expected));
                }
                else{
                    result.add(expected + "->" + (nums[i] - 1));
                }
            }
            
            long val = nums[i];
            expected = nums[i] + 1L;
            //System.out.println("E : " + expected);
        }
        
        if(expected <= upper){
            if(expected == upper){
                result.add(String.valueOf(expected));
            }
            else{
                result.add(expected + "->" + upper);
            }
        }
        
        return result;
    }
}
