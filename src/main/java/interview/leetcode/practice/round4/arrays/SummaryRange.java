package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class SummaryRange {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();

        if(nums.length == 0){
            return result;
        }
        
        StringBuilder sb = new StringBuilder().append(nums[0]);
        
        int prev = nums[0];
        for(int i=1; i<nums.length; i++){
            if(prev + 1 != nums[i]){
                if(prev != Integer.valueOf(sb.toString())){
                    sb.append("->").append(prev);
                }
                               
                result.add(sb.toString());
                sb = new StringBuilder().append(nums[i]);
                prev = nums[i];
            }
            else{
                prev += 1;
            }
        }
        
        if(Integer.valueOf(sb.toString()) != nums[nums.length - 1]){
            sb.append("->").append(nums[nums.length - 1]);
        }
        
        result.add(sb.toString());
        
        return result;
    }
}
