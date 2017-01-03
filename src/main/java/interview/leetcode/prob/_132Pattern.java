package interview.leetcode.prob;

import java.util.Stack;

/**
 * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 * @author jojo
 *
 */
public class _132Pattern {
    public boolean find132pattern(int[] nums) {
        // stack of intervals where [0] is min and [1] is max
        Stack<int[]> stack = new Stack<int[]>();
        
        for(int n : nums){
            // if the current sequense is decreasing order we need to add it to stack for later possible use
            if(stack.isEmpty() || stack.peek()[0] > n){
                stack.push(new int[]{n,n});
            }
            // if the current number is greater than min of latest interval need to find if current number is an overlap
            else if(n > stack.peek()[0]){
                // if the current number is greater than min but less than max of latest interval then 132 sequence formed
                if(n < stack.peek()[1]){
                    return true;
                }
                
                int[] top = stack.pop();
                top[1] = n;
                
                // we can safely discard all the intervals which has max less than current 
                while(!stack.isEmpty() && stack.peek()[1] <= n){
                    stack.pop();
                }
                
                // if the stack is non empty and there is an interval which has min less than n then 132 sequence achieved.
                if(!stack.isEmpty() && stack.peek()[0] < n){
                    return true;
                }
                
                
                // push the consolidated interval back to stack so that it can be used for later use.
                stack.push(top);
            }
        }
        
        return false;
    }
}
