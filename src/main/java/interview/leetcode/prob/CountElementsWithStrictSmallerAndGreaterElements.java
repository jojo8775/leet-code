package interview.leetcode.prob;

/**
 * Given an integer array nums, return the number of elements that have both a strictly smaller and a strictly greater element appear in nums.

 

Example 1:

Input: nums = [11,7,2,15]
Output: 2
Explanation: The element 7 has the element 2 strictly smaller than it and the element 11 strictly greater than it.
Element 11 has element 7 strictly smaller than it and element 15 strictly greater than it.
In total there are 2 elements having both a strictly smaller and a strictly greater element appear in nums.
Example 2:

Input: nums = [-3,3,3,90]
Output: 2
Explanation: The element 3 has the element -3 strictly smaller than it and the element 90 strictly greater than it.
Since there are two elements with the value 3, in total there are 2 elements having both a strictly smaller and a strictly greater element appear in nums.
 

Constraints:

1 <= nums.length <= 100
-105 <= nums[i] <= 105
Accepted
16,022
Submissions
26,357
 * @author jojo
 * Jan 25, 2022 10:48:56 PM
 */
public class CountElementsWithStrictSmallerAndGreaterElements {
    public int countElements(int[] nums) {
        int smallest = Integer.MAX_VALUE, biggest = Integer.MIN_VALUE;
        for(int n : nums){
            smallest = Math.min(smallest, n);
            biggest = Math.max(biggest, n);
        }
        
        int count = 0;
        for(int n : nums){
            if(smallest < n && n < biggest){
                count++;
            }
        }
        
        return count;
    }
}
