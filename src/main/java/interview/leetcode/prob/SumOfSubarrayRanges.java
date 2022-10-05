package interview.leetcode.prob;

import java.util.Stack;

/**
 * You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray.

Return the sum of all subarray ranges of nums.

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,2,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0 
[2], range = 2 - 2 = 0
[3], range = 3 - 3 = 0
[1,2], range = 2 - 1 = 1
[2,3], range = 3 - 2 = 1
[1,2,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.
Example 2:

Input: nums = [1,3,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0
[3], range = 3 - 3 = 0
[3], range = 3 - 3 = 0
[1,3], range = 3 - 1 = 2
[3,3], range = 3 - 3 = 0
[1,3,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.
Example 3:

Input: nums = [4,-2,-3,4,1]
Output: 59
Explanation: The sum of all subarray ranges of nums is 59.
 

Constraints:

1 <= nums.length <= 1000
-109 <= nums[i] <= 109
 

Follow-up: Could you find a solution with O(n) time complexity?

Accepted
58,129
Submissions
96,517
 * @author jojo
 * Oct 4, 2022 10:19:18 PM
 */
public class SumOfSubarrayRanges {
	public long subArrayRanges(int[] A) {
		int n = A.length;
		long result = 0;

		Stack<Integer> minStack = new Stack<>();
		for (int i = 0; i <= n; i++) {
			while (!minStack.isEmpty() && A[minStack.peek()] > (i == n ? Integer.MIN_VALUE : A[i])) {
				int idx = minStack.pop();
				int left = minStack.isEmpty() ? -1 : minStack.peek();
				int right = i;
				
				// think of arr: 5,3,1,6
				// max number of sub array where 1 is min will be 
				// left: (3)         right: (2)     == left * right = 3 * 2 = 6; 
				//              1         1,6
				//            3,1
				//          5,3,1
				
				// total: (6)      
				//              1 
				//            3,1
				//              1,6
				//            3,1,6
				//          5,3,1
				//          5,3,1,6
				result -= (long) A[idx] * (right - idx) * (idx - left);
			}
			
			minStack.push(i);
		}

		Stack<Integer> maxStack = new Stack<>();
		for (int i = 0; i <= n; i++) {
			while (!maxStack.isEmpty() && A[maxStack.peek()] < (i == n ? Integer.MAX_VALUE : A[i])) {
				int idx = maxStack.pop();
				int left = maxStack.isEmpty() ? -1 : maxStack.peek();
				int right = i;
				result += (long) A[idx] * (right - idx) * (idx - left);
			}
			
			maxStack.push(i);
		}
		
		return result;
	}
}
