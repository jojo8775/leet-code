package interview.leetcode.prob;

import java.util.Stack;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 

Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9
 

Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
Accepted
1,145,279
Submissions
2,014,552
 * @author jojo
 * Jun 6, 2022 9:43:22 PM
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int len = height.length;
        
        for(int i=0; i<len; i++){
            if(stack.isEmpty() || height[stack.peek()] < height[i]){
                stack.push(i);
            }
        }
        
        int result = 0, right = 0;
        for(int i=len - 1; i>0 && !stack.isEmpty(); i--){
            int left = height[stack.peek()];
            int diff = Math.min(left,right);
            int val = Math.max(0, diff - height[i]);
            
            result += val;
            
            if(i == stack.peek()){
                stack.pop();
            }
            right = Math.max(right, height[i]);
        }
        
        return result;
    }
}
