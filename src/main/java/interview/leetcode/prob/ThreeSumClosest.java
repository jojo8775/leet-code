package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * @author jojo
 *
 */
public class ThreeSumClosest {
	public int threeSumClosest(int[] nums, int target) {
        int result = 0, diff = Integer.MAX_VALUE;
        Arrays.sort(nums);
        
        for(int i=0; i<nums.length - 2; i++){
            if(i==0 || nums[i]>nums[i-1]){
                int j = i + 1;
                int k = nums.length - 1;
                
                while(j < k){
                    int sum = nums[i] + nums[j] + nums[k];
                    
                    if(sum == target){
                        return target;
                    }
                    else if(sum > target){
                        if(diff > sum - target){
                            diff = sum - target;
                            result = sum;
                        }
                        k --;
                        
                        //removing duplicates for third pointer
                        while(j < k && nums[k+1] == nums[k]){
                            k--;
                        }
                    }
                    else{
                        if(diff > target - sum){
                            diff = target - sum;
                            result = sum;
                        }
                        j++;
                        
                        //removing duplicates for second pointer
                        while(j < k && nums[j-1] == nums[j]){
                            j++;
                        }
                    }
                }
            }
        }
        
        return result;
    }
	
	public static void main(String[] args){
		int[] arr = {-1,2,1,-4};
		System.out.println(new ThreeSumClosest().threeSumClosest(arr, 1));
	}
}
