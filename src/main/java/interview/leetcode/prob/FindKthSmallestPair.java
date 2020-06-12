package interview.leetcode.prob;

import java.util.Arrays;

/**
 * Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0 
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Note:
2 <= len(nums) <= 10000.
0 <= nums[i] < 1000000.
1 <= k <= len(nums) * (len(nums) - 1) / 2.
Accepted
31,047
Submissions
99,576
 * @author jojo
 * Jun 11, 2020  11:22:36 PM
 */
public class FindKthSmallestPair {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        
        int len = nums.length;
        int lo = nums[1] - nums[0], hi = nums[len - 1] - nums[0];
        
        for(int i=1; i<len - 1; i++){
            if(nums[i+1] - nums[i] < lo){
                lo = nums[i+1] - nums[i];
            }
        }
        
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            
            int pairCountLessThanMid = 0;
            for(int i=0, j=0; i<len; i++){
                while(j<len && nums[i] - nums[j] > mid){
                    j++;
                }
                
                pairCountLessThanMid += (i - j);
            }
            
            if(pairCountLessThanMid >= k){
                hi = mid;
            }
            else{
                lo = mid + 1;
            }
        }
        
        return lo;
    }
    
    // iterative approach 
	  public int smallestDistancePair_iterative(int[] nums, int k) {
		  int len1 = nums.length, len2 = 1000001;
		  int[] dp = new int[len2];
	 
		  for(int i=0; i<len1; i++){
		      for(int j=0; j<i; j++){
		          int diff = Math.abs(nums[i] - nums[j]);
		          dp[diff]++;
		      }
		  }
		 
		  int sum = 0;
		  for(int i=0; i<len2; i++){
		      sum += dp[i];
		     
		      if(sum >= k){
		          return i;
		      }
		  }
		 
		  return 0;
	  }
}
