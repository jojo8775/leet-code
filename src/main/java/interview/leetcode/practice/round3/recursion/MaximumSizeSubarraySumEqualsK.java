package interview.leetcode.practice.round3.recursion;

import java.util.HashMap;
import java.util.Map;

public class MaximumSizeSubarraySumEqualsK {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int maxLen = 0, curSum = 0;
        
        for(int i=0; i<nums.length; i++){
            curSum += nums[i];
            if(curSum == k){
                maxLen = Math.max(maxLen, i + 1);
            }
            else if(map.containsKey(curSum - k)){
                maxLen = Math.max(maxLen, i - map.get(curSum - k));
            }
            
            if(!map.containsKey(curSum)){
                map.put(curSum, i);
            }
        }
        
        return maxLen;
    }
}
