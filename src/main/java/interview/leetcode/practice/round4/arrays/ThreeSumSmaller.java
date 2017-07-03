package interview.leetcode.practice.round4.arrays;

import java.util.Arrays;

public class ThreeSumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        
        int count = 0;
        for(int i=0; i<nums.length - 2; i++){
            int j = i+1, k = nums.length - 1;
            while(j < k){
                int val = nums[i] + nums[j] + nums[k];
                if(val < target){
                    //  k -j --> because all combination from j to k - i will be smaller than current
                    count += (k - j);
                    j++;
                }
                else {
                    k--;
                }
            }
        }
        
        return count;
    }
}
