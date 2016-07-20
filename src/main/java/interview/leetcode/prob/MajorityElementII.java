package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        //since n/3 majority elements are required, regardless of the length of the array it will have atmost two numbers 

        Integer r1 = null, r2 = null;
        int c1=0,c2=0;
        
        //getting the majority number (r1 and r2)
        for(int i=0; i<nums.length; i++){
            if(r1!=null && nums[i] == r1.intValue()){
                c1++;
            }
            else if(r2!=null && nums[i] == r2.intValue()){
                c2++;
            }
            else if(r1 == null || c1 == 0){
                c1 = 1;
                r1 = nums[i];
            }
            else if(r2 == null || c2 == 0){
                c2 = 1;
                r2 = nums[i];
            }
            else{
                c1--;
                c2--;
            }
        }
        
        //resetting the count of r1 and r2
        c1=0;
        c2=0;
        
        // finding the actual count of r1 and r2
        for(int i=0; i<nums.length; i++){
            if(r1 != null && nums[i] == r1.intValue()){
                c1++;
            }
            else if(r2 != null && nums[i] == r2.intValue()){
                c2++;
            }
        }
        
        //populating result
        List<Integer> result = new ArrayList<Integer>();
        
        if(c1 > nums.length/3){
            result.add(r1);
        }
        
        if(c2 > nums.length/3){
            result.add(r2);
        }
        
        return result;
    }
}
