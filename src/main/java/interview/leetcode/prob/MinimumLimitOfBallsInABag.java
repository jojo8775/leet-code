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
        int beg = 1, end = nums[0];

        for(int n : nums){
            end = Math.max(end, n);
        }

        while(beg < end){
            int mid = beg + (end - beg)/2;

            int count = findCount(nums, mid, maxOperations);

            //System.out.println("b: " + beg + "   e: " + end + "   m: " + mid + "   c: " + count);

            if(count > maxOperations){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
        }

        return beg;
    }

    private int findCount(int[] nums, int limit, int target){
        int count = 0;
        
        for(int i=0; i<nums.length; i++){

            // (-1) because need to find the split count. e.g then target is 4, then 8 the split count will be 1
            // for 4 the split count should be 0 since there is nothing greater than 4
            count += ((nums[i] - 1)/limit);

            if(count > target){
                return count;
            }
        }

        return count;
    }
}
