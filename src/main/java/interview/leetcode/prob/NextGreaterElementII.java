package interview.leetcode.prob;

import java.util.Stack;

/**
 * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.
 * @author jojo
 *Mar 19, 20171:48:54 PM
 */
public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        if(nums.length == 0){
            return new int[0];
        }
        
        // idea is to have a rolling range
        int start = 0, len = nums.length, end = start + len;
        int[] result = new int[nums.length];
        
        Stack<Integer> stack = new Stack<Integer>();
        
        // runtime = 2 O(n) ~ O(n)
        // space complexity = 2O(n) ~ O(n)
        while(start <= end){
            // we need to cover the use case where we need to skip the same index comaprison
            while(!stack.isEmpty() && nums[stack.peek()] < nums[start%len] && stack.peek() != start%len){
                result[stack.pop()] = nums[start%len];
            }
            
            // if stack is empty then its time to increase the end for a cyclic lookup
            if(stack.isEmpty()){
                end = start + len;
            }
            
            stack.push(start%len);
            start ++;
        }
        
        // mark the rest of the stack element as -1 as there are no next greater element 
        while(!stack.isEmpty()){
            result[stack.pop()] = -1;
        }
        
        return result;
    }
}
