package interview.leetcode.practice;

import java.util.Arrays;

public class LargestNumbers {
	public String largestNumber(int[] nums) {
		Integer[] aa = new Integer[nums.length];
		for(int i=0; i<nums.length; i++) {
			aa[i] = nums[i];
		}
		
        Arrays.sort(aa, (a, b) -> {
            String s1 = String.valueOf(a) + String.valueOf(b);
            String s2 = String.valueOf(b) + String.valueOf(a);
            
            for(int i=0; i<s1.length(); i++){
                if(s1.charAt(i) != s2.charAt(i)){
                    return s2.charAt(i) - s1.charAt(i);
                }
            }
            
            return 0;
        });
        
        StringBuilder sb = new StringBuilder();
        for(int n : aa){
            if(n == 0){
                continue;
            }
            
            sb.append(n);
        }
        
        if(sb.length() == 0){
            sb.append(0);
        }
        
        return sb.toString();
    }
}
