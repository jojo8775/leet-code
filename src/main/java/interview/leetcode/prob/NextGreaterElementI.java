package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 * @author jojo
 *
 */
public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Map<Integer, Integer> nextGreaterNum = new HashMap<Integer, Integer>();
        
        int idx = 0, len = nums.length;
        Stack<Integer> stack = new Stack<Integer>();
        
        while(idx < len){
            if(stack.isEmpty() || stack.peek() > nums[idx]){
                stack.push(nums[idx++]);
            }    
            else{
                nextGreaterNum.put(stack.pop(), nums[idx]);
            }
        }
        
        for(int i=0; i<findNums.length; i++){
            findNums[i] = nextGreaterNum.getOrDefault(findNums[i], -1);
        }
        
        return findNums;
    }
    
    public static void main(String[] args){
        int[] result = new NextGreaterElementI().nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
        for(int n : result){
            System.out.print(n + ", ");
        }
    }
}
