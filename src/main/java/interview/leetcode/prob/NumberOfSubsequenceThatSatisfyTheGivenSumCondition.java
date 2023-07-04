package interview.leetcode.prob;

import java.util.Arrays;

/**
 * You are given an array of integers nums and an integer target.

Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target. Since the answer may be too large, return it modulo 109 + 7.

 

Example 1:

Input: nums = [3,5,6,7], target = 9
Output: 4
Explanation: There are 4 subsequences that satisfy the condition.
[3] -> Min value + max value <= target (3 + 3 <= 9)
[3,5] -> (3 + 5 <= 9)
[3,5,6] -> (3 + 6 <= 9)
[3,6] -> (3 + 6 <= 9)
Example 2:

Input: nums = [3,3,6,8], target = 10
Output: 6
Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
[3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
Example 3:

Input: nums = [2,3,3,4,6,7], target = 12
Output: 61
Explanation: There are 63 non-empty subsequences, two of them do not satisfy the condition ([6,7], [7]).
Number of valid subsequences (63 - 2 = 61).
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 106
1 <= target <= 106
Accepted
98,782
Submissions
221,739
 * @author jojo
 * Jul 4, 2023 9:37:05 AM
 */
public class NumberOfSubsequenceThatSatisfyTheGivenSumCondition {
	public int numSubseq(int[] nums, int target) {
        // taking clues from 2Sum problem 
        Arrays.sort(nums);
        
        int left = 0, right = nums.length - 1, result = 0;
        
        // based on the problem statement
        int mod = (int)(1e9 + 7);
        
        // maintaining a powArr because for a subsequence of 4 element there are 
        // two possible state for each element. To pick it or to skip it. So for a subsequence of 4 element 
        // the result will be 2 ^ ((j-1) - (i+1)) since i represents the smallest and j represents the largest entry.
        int[] powArr = new int[nums.length];
        powArr[0] = 1;
        
        for(int i=1; i<powArr.length; i++){
            powArr[i] = (powArr[i-1] * 2) % mod;
        }
        
        while(left <= right){
            int val = nums[left] + nums[right];
            if(val > target){
                right--;
            }
            else{
                // based on the problem statement all the <= target sum should be added. 
                result = (result + (powArr[right - left])) % mod;
                left++;
            }
        }
        
        return result;
    }
}
