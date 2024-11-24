package interview.leetcode.prob;

import java.util.TreeSet;

/**
 * Given an array nums, return the maximum value of a triplet (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k].

The value of a triplet (i, j, k) is nums[i] - nums[j] + nums[k].

 

Example 1:

Input: nums = [5,6,9] 

Output: 8 

Explanation: We only have one choice for an increasing triplet and that is choosing all three elements. The value of this triplet would be 5 - 6 + 9 = 8.

Example 2:

Input:  nums = [1,5,3,6] 

Output:  4 

Explanation: There are only two increasing triplets:

(0, 1, 3): The value of this triplet is nums[0] - nums[1] + nums[3] = 1 - 5 + 6 = 2.

(0, 2, 3): The value of this triplet is nums[0] - nums[2] + nums[3] = 1 - 3 + 6 = 4.

Thus the answer would be 4.

 

Constraints:

3 <= nums.length <= 105
1 <= nums[i] <= 109
The input is generated such that at least one triplet meets the given condition.
Accepted
887
Submissions
2,327
 * 
 * Nov 24, 2024 - 10:26:45 AM
 * Jojo 
 */
public class MaximumIncreasingTripletValue {
    public int maximumTripletValue(int[] nums) {
        int[] rightMax = new int[nums.length];
        
        // ensures we get the highest right number from the current index
        rightMax[nums.length - 1] = nums[nums.length - 1];
        for(int i=nums.length - 2; i>=0; i--){
            rightMax[i] = Math.max(rightMax[i+1], nums[i]);
        }
        
        int max = 0;
        
        // ensures we can get the lower key from the current index
        TreeSet<Integer> leftSet = new TreeSet<>();
        
        for(int i=0; i<nums.length - 1; i++){
            if(leftSet.lower(nums[i]) == null){
                leftSet.add(nums[i]);
                continue;
            }
            
            int left = leftSet.lower(nums[i]);
                        
            int right = rightMax[i+1];
            
            if(right > nums[i]){
                max = Math.max(max, left - nums[i] + right);
            }
            
            leftSet.add(nums[i]);
        }
        
        return max;
    }
}
