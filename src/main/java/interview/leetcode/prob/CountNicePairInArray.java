package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array nums that consists of non-negative integers. Let us define rev(x) as the reverse of the non-negative integer x. For example, rev(123) = 321, and rev(120) = 21. A pair of indices (i, j) is nice if it satisfies all of the following conditions:

0 <= i < j < nums.length
nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
Return the number of nice pairs of indices. Since that number can be too large, return it modulo 109 + 7.

 

Example 1:

Input: nums = [42,11,1,97]
Output: 2
Explanation: The two pairs are:
 - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
 - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.
Example 2:

Input: nums = [13,10,35,24,76]
Output: 4
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 109
Accepted
53,193
Submissions
113,693
 * @author jojo
 * Nov. 21, 2023 12:34:30 a.m.
 */
public class CountNicePairInArray {
	public int countNicePairs(int[] nums) {
        int len = nums.length;
        
        int mod = (int)(1e9 + 7);
        
        // based on the problem statement 
        // nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
        //
        // this can be written as 
        // x + rev(y) == y + rev(x);
        // x - rev(x) == y - rev(y)
        //
        // for this reason arr we are adding only the diff of each number. 
        int[] arr = new int[len];
        for(int i=0; i<len; i++){
            arr[i] = nums[i] - rev(nums[i]);
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        
        int pairs = 0;
        
        // based on our deduction 
        // x - rev(x) == y - rev(y)
        // counting the number of common pairs. 
        for(int i=0; i<len; i++){
            pairs = (pairs + map.getOrDefault(arr[i], 0)) % mod;
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        
        
        return pairs;
    }
    
    private int rev(int num){
        int revNum = 0;
        while(num > 0){
            revNum *= 10;
            revNum += num % 10;
            num /= 10;
        }
        
        return revNum;
    }
}
