package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums and an integer target.

Return the maximum number of non-empty non-overlapping subarrays such that the sum of values in each subarray is equal to target.

 

Example 1:

Input: nums = [1,1,1,1,1], target = 2
Output: 2
Explanation: There are 2 non-overlapping subarrays [1,1,1,1,1] with sum equals to target(2).
Example 2:

Input: nums = [-1,3,5,1,4,2,-9], target = 6
Output: 2
Explanation: There are 3 subarrays with sum equal to 6.
([5,1], [4,2], [3,5,1,4,2,-9]) but only the first 2 are non-overlapping.
Example 3:

Input: nums = [-2,6,6,3,5,4,1,2,8], target = 10
Output: 3
Example 4:

Input: nums = [0,0,0], target = 0
Output: 3
 

Constraints:

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
0 <= target <= 10^6
Accepted
17,073
Submissions
37,481
 * @author jojo
 * Dec 7, 2021 11:23:17 PM
 */
public class MaximumNumberOfNonOverlappingSubarraysWithSumEqualsTarget {
    public int maxNonOverlapping(int[] nums, int target) {
        Map<Integer, Integer> map= new HashMap<>();
          
          int prefixSum=0, availableIdx=-1, res=0;
          // to counter 0 index values 
          map.put(0,-1);
          
          for (int i=0; i<nums.length; i++){
              prefixSum+=nums[i];
              int remain = prefixSum - target;
              
              if (map.containsKey(remain) && map.get(remain)>=availableIdx){
                  res++;
                  availableIdx=i;
              }
              
              map.put(prefixSum, i);
          }
          return res;
      }
    
    //-------------------------------------------------
    //  dp O(n^2)
    public int maxNonOverlapping_1(int[] nums, int target) {
	    int N = nums.length;
	    int[] dp = new int[N + 1];
	
	    for (int i = 0; i < N; ++i) {
	
	    	int sum = 0;
	    	for (int j = i; j >= 0; --j) {
	    		sum += nums[j];
	    		dp[i + 1] = Math.max(dp[i + 1], dp[j] + (sum == target ? 1 : 0));
	    	}
	    }
	
	    return dp[N];
    }
    
    // --------------------------------------------------------
    // modification of above algo for O(n)
    
    public int maxNonOverlapping_2(int[] nums, int target) {
    	Map<Integer, Integer> map = new HashMap<>();
    	map.put(0, 0);

    	int res = 0;
    	int sum = 0;

    	for (int i = 0; i < nums.length; ++i) {
    		sum += nums[i];
    		if (map.containsKey(sum - target)) {
    			res = Math.max(res, map.get(sum - target) + 1);
    		}
    		map.put(sum, res);
    	}

    	return res;
    }
}
