package interview.leetcode.prob;

/**
 * You are given a binary array nums and an integer k.

A k-bit flip is choosing a subarray of length k from nums and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.

Return the minimum number of k-bit flips required so that there is no 0 in the array. If it is not possible, return -1.

A subarray is a contiguous part of an array.

 

Example 1:

Input: nums = [0,1,0], k = 1
Output: 2
Explanation: Flip nums[0], then flip nums[2].
Example 2:

Input: nums = [1,1,0], k = 2
Output: -1
Explanation: No matter how we flip subarrays of size 2, we cannot make the array become [1,1,1].
Example 3:

Input: nums = [0,0,0,1,0,1,1,0], k = 3
Output: 3
Explanation: 
Flip nums[0],nums[1],nums[2]: nums becomes [1,1,1,1,0,1,1,0]
Flip nums[4],nums[5],nums[6]: nums becomes [1,1,1,1,1,0,0,0]
Flip nums[5],nums[6],nums[7]: nums becomes [1,1,1,1,1,1,1,1]
 

Constraints:

1 <= nums.length <= 105
1 <= k <= nums.length
Accepted
37,349
Submissions
71,473
 * 
 * Jun 23, 2024 - 7:03:10 PM
 * Jojo 
 */
public class MinimumNumberOfKConsecutiveBitFlips {
	public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        boolean[] flippedStates = new boolean[n];
        
        int validFlipsFromPastWindow = 0;
        int flipCount = 0;
        
        for(int i=0; i<n; i++){
            if(i >= k){
                // taking a sliding window approach and reducing the number of previous flips.`
                if(flippedStates[i-k]){
                    validFlipsFromPastWindow--;
                }
            }
            
            // if the current index is within the window 
            // if validFlipsFromPastWindow == 1 and nums[i] = 0 no changed needed. This is because 
            // previous flip will make the current nums[i] = 1;
            //
            // if validFlipsFromPastWindow == 0 and nums[i] = 1 no changed needed. This is because 
            // the current nums[i] = 1;
            // 
            // if validFlipsFromPastWindow == 1 and nums[i] = 1, the current index needs to be 
            // flipped again because flip will make num[i] = 0
            //
            // if validFlipsFromPastWindow == 0 and nums[i] = 0, the current index needs to be 
            // flipped to make num[i] = 1
            if(validFlipsFromPastWindow % 2 == nums[i]){
                
                if(i + k > n){
                    return -1;
                }
                
                validFlipsFromPastWindow++;
                flippedStates[i] = true;
                flipCount++;
            }
        }
        
        return flipCount;
    }
}
