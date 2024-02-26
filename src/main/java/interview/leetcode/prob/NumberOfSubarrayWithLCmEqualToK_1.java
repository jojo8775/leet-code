package interview.leetcode.prob;

/**
 * Given an integer array nums and an integer k, return the number of subarrays of nums where the least common multiple of the subarray's elements is k.

A subarray is a contiguous non-empty sequence of elements within an array.

The least common multiple of an array is the smallest positive integer that is divisible by all the array elements.

 

Example 1:

Input: nums = [3,6,2,7,1], k = 6
Output: 4
Explanation: The subarrays of nums where 6 is the least common multiple of all the subarray's elements are:
- [3,6,2,7,1]
- [3,6,2,7,1]
- [3,6,2,7,1]
- [3,6,2,7,1]
Example 2:

Input: nums = [3], k = 2
Output: 0
Explanation: There are no subarrays of nums where 2 is the least common multiple of all the subarray's elements.
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i], k <= 1000
Accepted
13.1K
Submissions
34.2K
 * @author jojo
 * Nov 14, 2022 8:29:02 PM
 */
public class NumberOfSubarrayWithLCmEqualToK_1 {
	public int subarrayLCM(int[] nums, int k) {
        int result = 0, len = nums.length, beg = 0, prev = -1;
        
        for(int i=0; i<len; i++){
            if(nums[i] == k){
                result++;
            }
            
            // if the current number is a factor then check if it forms the lcm with prev
            if( k % nums[i] == 0){
                if(prev != -1 && lcm(prev, nums[i]) == k){
                    result += (i - beg ); // since beg is 0 indexed
                }
                
                // since it is a contiguous sub array needs to ensue all elements 
                // in the array follow the same rule.
                prev = nums[i];
            }
            else{
                // if the current number is not a factor then next can only be a potential candidate. 
                beg = i + 1;
            }
        }
        
        return result;
    }
    
    private int lcm(int a, int b){
        return (a / gcm(a,b)) * b; // maths formula
    }
    
    private int gcm(int a, int b){
        if(a == 0){
            return b;
        }
        
        return gcm(b % a, a);
    }
}
