package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 * @author jojo
 *
 */
public class ThreeSum {
	public List<List<Integer>> find(int[] nums){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(nums == null || nums.length < 3){
			return result;
		}
		
		Arrays.sort(nums);
		
		for(int i=0; i<nums.length - 2; i++){
			//removing duplicates for first pointer
			if(i == 0 || nums[i-1] < nums[i]){
				int j = i+1, k = nums.length - 1;
				while(j < k){
					if(nums[i] + nums[j] + nums[k] == 0){
						result.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[j], nums[k])));
						j++;
						k--;
						
						//removing duplicates for second pointer
						while(j < k && nums[j-1] == nums[j]){
							j++;
						}
						
						//removing duplicates for third pointer
						while(j < k && nums[k+1] == nums[k]){
							k--;
						}
					}
					else if(nums[i] + nums[j] + nums[k] < 0){
						j++;
					}
					else{
						k--;
					}
				}
			}
		}
		
		return result;
	}
}
