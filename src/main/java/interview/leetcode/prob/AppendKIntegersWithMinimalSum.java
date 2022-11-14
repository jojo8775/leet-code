package interview.leetcode.prob;

import java.util.Arrays;

/**
 * You are given an integer array nums and an integer k. Append k unique positive integers that do not appear in nums to nums such that the resulting total sum is minimum.

Return the sum of the k integers appended to nums.

 

Example 1:

Input: nums = [1,4,25,10,25], k = 2
Output: 5
Explanation: The two unique positive integers that do not appear in nums which we append are 2 and 3.
The resulting sum of nums is 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70, which is the minimum.
The sum of the two integers appended is 2 + 3 = 5, so we return 5.
Example 2:

Input: nums = [5,6], k = 6
Output: 25
Explanation: The six unique positive integers that do not appear in nums which we append are 1, 2, 3, 4, 7, and 8.
The resulting sum of nums is 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36, which is the minimum. 
The sum of the six integers appended is 1 + 2 + 3 + 4 + 7 + 8 = 25, so we return 25.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= k <= 108
Accepted
9,272
Submissions
46,887
 * @author jojo
 * Mar 7, 2022 1:00:37 AM
 */
public class AppendKIntegersWithMinimalSum {
	   public long minimalKSum(int[] nums, int k) {
	        Arrays.sort(nums);
	        int prev = -1;
	        long sum = 0;
	        for (int num : nums) {
	            // Taking care of the duplicate numbers.
	            if (prev == num){
	              continue;  
	            } 
	            
	            // If num > k, we already have found the needed k numbers upto num. 
	            if (num > k) {
	                break;
	            }
	            
	            // this mean the current num == k so we need increase the num
	            k++;
	            
	            // sum of the existing numbers withing the sequence 
	            sum += num;
	            
	            // traking the prev num to skip duplicates
	            prev = num;
	        }

	           // this is the formula for increasing seq -: (n * (n+1)) / 2
	        return (long)(k + 1) * k / 2 - sum;
	    }
	   
    public long minimalKSum_old(int[] nums, int k) {
        Arrays.sort(nums);
        
        int len = nums.length, idx = 0, cur = 1;
        long total = 0;
        
        while(idx < len && k > 0){
            if(nums[idx] <= cur){
                if(cur == nums[idx]){
                    cur++;
                }
                
                idx++;
            }
            else{
                k--;
                total += cur;
                cur++;
            }
        }
        
        while(k > 0){
            k--;
            total += cur;
            cur++;
        }
        
        return total;
    }
}
