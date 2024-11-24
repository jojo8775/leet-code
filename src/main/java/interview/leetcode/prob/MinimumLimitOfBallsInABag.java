package interview.leetcode.prob;

import java.util.Arrays;

/**
 * 
 * You are given an integer array nums where the ith bag contains nums[i] balls. You are also given an integer maxOperations.

You can perform the following operation at most maxOperations times:

Take any bag of balls and divide it into two new bags with a positive number of balls.
For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.
Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after the operations.

Return the minimum possible penalty after performing the operations.

 

Example 1:

Input: nums = [9], maxOperations = 2
Output: 3
Explanation: 
- Divide the bag with 9 balls into two bags of sizes 6 and 3. [9] -> [6,3].
- Divide the bag with 6 balls into two bags of sizes 3 and 3. [6,3] -> [3,3,3].
The bag with the most number of balls has 3 balls, so your penalty is 3 and you should return 3.
Example 2:

Input: nums = [2,4,8,2], maxOperations = 4
Output: 2
Explanation:
- Divide the bag with 8 balls into two bags of sizes 4 and 4. [2,4,8,2] -> [2,4,4,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,4,4,4,2] -> [2,2,2,4,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,4,4,2] -> [2,2,2,2,2,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2].
The bag with the most number of balls has 2 balls, so your penalty is 2, and you should return 2.
 

Constraints:

1 <= nums.length <= 105
1 <= maxOperations, nums[i] <= 109
Accepted
42,221
Submissions
69,880
 * 
 * Nov 14, 2024 - 12:33:03 AM
 * Jojo 
 */
public class MinimumLimitOfBallsInABag {
	public int minimumSize(int[] nums, int maxOperations) {
        Arrays.sort(nums);
        
        int beg = 1, end = 0;
        
        for(int n : nums){
            end = Math.max(end, n);
        }
        
        int minPenalty = 0;
        
        while(beg <= end){
            // mid is assumed max 
            int mid = beg + (end - beg) / 2;
            
            int opsCount = findMaxOpsCount(nums, mid, maxOperations);
            
            if(opsCount > maxOperations){
                beg = mid + 1;
            }
            else{
                minPenalty = mid;
                end = mid - 1;
            }
        }
        
        return minPenalty;
    }
    
    private int findMaxOpsCount(int[] nums, int limit, int maxOps){
        int opsCount = 0;
        
        for(int i=0; i<nums.length; i++){
            int cur = nums[i];
            
            opsCount += (cur - 1)/limit;
            
            if(opsCount > maxOps){
                break;
            }
        }
        
        return opsCount;
    }
}
