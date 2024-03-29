
package interview.leetcode.prob;

import java.util.Stack;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 * @author jojo
 * Sep 8, 2019 2:34:14 AM
 */
public class DailyTemparature {
	public int[] dailyTemperatures_new(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        
        int[] result = new int[temperatures.length];
        
        for(int i=0; i<temperatures.length; i++){
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                result[stack.peek()] = i - stack.pop();
            }
            
            stack.push(i);
        }
        
        return result;
    }
	
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        
        int[] result = new int[T.length];
        
        for(int i=T.length - 1; i>=0; i--){
            while(!stack.isEmpty() && T[stack.peek()] <= T[i]){
                stack.pop();
            }
            
            if(!stack.isEmpty()){
                result[i] = stack.peek() - i;
            }
            
            stack.push(i);
        }
        
        return result;
    }
}
