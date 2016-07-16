package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].


 * @author jojo
 *
 */
public class SummaryRange {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<String>();
        
        if(nums.length == 0){
            return result;
        }
        
        int prev = nums[0], prevRef = nums[0];
        for(int i=1; i<nums.length; i++){
            if(prev + 1 != nums[i]){
                StringBuilder  sb = new StringBuilder();
                sb.append(prevRef);
                if(prev != prevRef){
                    sb.append("->").append(prev);
                }
                result.add(sb.toString());
                
                prevRef = nums[i];
            }
            
            prev = nums[i];
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(prevRef);
        
        if(prev != prevRef){
            sb.append("->").append(prev);
        }
        
        result.add(sb.toString());
        
        return result;
    }
}
