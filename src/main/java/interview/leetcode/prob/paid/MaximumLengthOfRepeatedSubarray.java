package interview.leetcode.prob.paid;

/**
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

 

Example 1:

Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
Output: 3
Explanation: The repeated subarray with maximum length is [3,2,1].
Example 2:

Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
Output: 5
 

Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 100
Accepted
169,423
Submissions
329,880
 * @author jojo
 * Apr 30, 2022 4:12:04 PM
 */
public class MaximumLengthOfRepeatedSubarray {
    public int findLength(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length;
        
        int[][] dp = new int[l1 + 1][l2 + 1];
        
        int ans = 0;
        for(int i=1; i<=l1; i++){
            for(int j=1; j<=l2; j++){
                if(nums1[i-1] == nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        
        return ans;
    }
}
