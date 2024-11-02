package interview.leetcode.prob;

/**
 * You are given a 0-indexed integer array nums. For each index i (1 <= i <= nums.length - 2) the beauty of nums[i] equals:

2, if nums[j] < nums[i] < nums[k], for all 0 <= j < i and for all i < k <= nums.length - 1.
1, if nums[i - 1] < nums[i] < nums[i + 1], and the previous condition is not satisfied.
0, if none of the previous conditions holds.
Return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.

 

Example 1:

Input: nums = [1,2,3]
Output: 2
Explanation: For each index i in the range 1 <= i <= 1:
- The beauty of nums[1] equals 2.
Example 2:

Input: nums = [2,4,6,4]
Output: 1
Explanation: For each index i in the range 1 <= i <= 2:
- The beauty of nums[1] equals 1.
- The beauty of nums[2] equals 0.
Example 3:

Input: nums = [3,2,1]
Output: 0
Explanation: For each index i in the range 1 <= i <= 1:
- The beauty of nums[1] equals 0.
 

Constraints:

3 <= nums.length <= 105
1 <= nums[i] <= 105
Accepted
24,717
Submissions
50,969
 * 
 * Oct 1, 2024 - 6:36:22 AM
 * Jojo 
 */
public class SumOfBeautyInTheArray {
	public int sumOfBeauties(int[] nums) {
        int len = nums.length;
        
        int[] leftMaxArr = new int[len];
        int[] rightMinArr = new int[len];
        
        leftMaxArr[0] = nums[0];
        for(int i=1; i<len; i++){
            leftMaxArr[i] = Math.max(leftMaxArr[i-1], nums[i]);
        }
        
        rightMinArr[len - 1] = nums[len - 1];
        for(int i=len-2; i>=0; i--){
            rightMinArr[i] = Math.min(rightMinArr[i+1], nums[i]);
        }
        
        int beautyCount = 0;
        
        for(int i=1; i<len-1; i++){
            if(nums[i] > leftMaxArr[i-1] && nums[i] < rightMinArr[i+1]){
                beautyCount += 2;
            }
            else if(nums[i] > nums[i-1] && nums[i] < nums[i + 1]){
                beautyCount += 1;
            }
        }
        
        return beautyCount;
    }
}
