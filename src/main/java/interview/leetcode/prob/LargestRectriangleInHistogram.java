package interview.leetcode.prob;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given heights = [2,1,5,6,2,3],
return 10.
 * @author jojo
 *
 */
public class LargestRectriangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<Integer>();
        
        int idx = 0, area = 0;
        while(idx < heights.length){
            if(stack.isEmpty() || heights[stack.peek()] <= heights[idx]){
                stack.push(idx++);
            }
            else{
                while(!stack.isEmpty() && heights[stack.peek()] > heights[idx]){
                    int topIdx = stack.pop();
                    if(stack.isEmpty()){
                        area = Math.max(area, idx * heights[topIdx]);
                    }
                    else{
                        area = Math.max(area, (idx - (stack.peek() + 1)) * heights[topIdx]);
                    }
                }
            }
        }
        
        while(!stack.isEmpty()){
            int topIdx = stack.pop();
            if(stack.isEmpty()){
                area = Math.max(area, idx * heights[topIdx]);
            }
            else{
                area = Math.max(area, (idx - (stack.peek() + 1)) * heights[topIdx]);
            }
        }
        
        return area;
    }
}
