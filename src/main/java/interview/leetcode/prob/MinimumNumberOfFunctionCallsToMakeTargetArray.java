package interview.leetcode.prob;

/**
 * Your task is to form an integer array nums from an initial array of zeros arr that is the same size as nums.

Return the minimum number of function calls to make nums from arr.

The answer is guaranteed to fit in a 32-bit signed integer.

 

Example 1:

Input: nums = [1,5]
Output: 5
Explanation: Increment by 1 (second element): [0, 0] to get [0, 1] (1 operation).
Double all the elements: [0, 1] -> [0, 2] -> [0, 4] (2 operations).
Increment by 1 (both elements)  [0, 4] -> [1, 4] -> [1, 5] (2 operations).
Total of operations: 1 + 2 + 2 = 5.
Example 2:

Input: nums = [2,2]
Output: 3
Explanation: Increment by 1 (both elements) [0, 0] -> [0, 1] -> [1, 1] (2 operations).
Double all the elements: [1, 1] -> [2, 2] (1 operation).
Total of operations: 2 + 1 = 3.
Example 3:

Input: nums = [4,2,5]
Output: 6
Explanation: (initial)[0,0,0] -> [1,0,0] -> [1,0,1] -> [2,0,2] -> [2,1,2] -> [4,2,4] -> [4,2,5](nums).
Example 4:

Input: nums = [3,2,2,4]
Output: 7
Example 5:

Input: nums = [2,4,8,16]
Output: 8
 

Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 10^9
Accepted
6,627
Submissions
10,720
 * @author jojo
 * Sep 3, 2020  4:08:52 PM
 */
public class MinimumNumberOfFunctionCallsToMakeTargetArray {
	public int minOperations(int[] nums) {
        // op1 is addition on 1. All the 1 bits will need this operation. 
        // op2 is multiplication of all. In other words same as >> 1. Since this is shared we need max
        int op2 = 0, op1 = 0;
        
        for(int n : nums){
            int bits = 0;
            while(n > 0){
                op1 += (n & 1);
                bits++;
                n = n >> 1;
            }
            
            // bits - 1 because 1 will become 8 after 1 << 3 bit the index of 1 will be at 4 (1000)
            op2 = Math.max(op2, bits - 1);
        }
        
        return op1 + op2;
    }
}
