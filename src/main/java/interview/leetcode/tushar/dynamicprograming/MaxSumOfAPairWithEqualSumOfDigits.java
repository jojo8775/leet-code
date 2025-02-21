package interview.leetcode.tushar.dynamicprograming;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 0-indexed array nums consisting of positive integers. You can choose two indices i and j, such that i != j, and the sum of digits of the number nums[i] is equal to that of nums[j].

Return the maximum value of nums[i] + nums[j] that you can obtain over all possible indices i and j that satisfy the conditions.

 

Example 1:

Input: nums = [18,43,36,13,7]
Output: 54
Explanation: The pairs (i, j) that satisfy the conditions are:
- (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
- (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
So the maximum sum that we can obtain is 54.
Example 2:

Input: nums = [10,12,19,14]
Output: -1
Explanation: There are no two numbers that satisfy the conditions, so we return -1.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
Seen this question in a real interview before?
1/5
Yes
No
Accepted
56.7K
Submissions
100.8K
Acceptance Rate
56.2%
 * 
 * Feb 11, 2025 - 4:41:21 PM
 * Jojo 
 */
public class MaxSumOfAPairWithEqualSumOfDigits {
	public int maximumSum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        int max = -1;

        for(int i=0; i<nums.length; i++){
            int sum = findSum(nums[i]);

            if(map.containsKey(sum)){
                max = Math.max(max, nums[map.get(sum)] + nums[i]);

                if(nums[map.get(sum)] < nums[i]){
                    map.put(sum, i);
                }
            }
            else{
                map.put(sum, i);
            }
        }

        return max;
    }

    private int findSum(int n){
        int sum = 0;
        while(n > 0){
            sum += (n % 10);
            n /= 10;
        }

        return sum;
    }
}
