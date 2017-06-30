package interview.leetcode.practice.round3.recursion;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        
        int sum = 0, len = 0;
        
        for(int i=0; i<nums.length; i++){
            if(nums[i] == 1){
                sum++;
            }
            else{
                sum--;
            }
            
            if(map.containsKey(sum)){
                len = Math.max(len, i-map.get(sum));
            }
            else{
                map.put(sum, i);
            }
        }
        
        return len;
    }
}
