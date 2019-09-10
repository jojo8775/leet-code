package interview.leetcode.prob;

import java.util.Stack;

/**
 * We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

Example 1:
Input: 
asteroids = [5, 10, -5]
Output: [5, 10]
Explanation: 
The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
Example 2:
Input: 
asteroids = [8, -8]
Output: []
Explanation: 
The 8 and -8 collide exploding each other.
Example 3:
Input: 
asteroids = [10, 2, -5]
Output: [10]
Explanation: 
The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
Example 4:
Input: 
asteroids = [-2, -1, 1, 2]
Output: [-2, -1, 1, 2]
Explanation: 
The -2 and -1 are moving left, while the 1 and 2 are moving right.
Asteroids moving the same direction never meet, so no asteroids will meet each other.
Note:

The length of asteroids will be at most 10000.
Each asteroid will be a non-zero integer in the range [-1000, 1000]..
Accepted
30,924
Submissions
79,189
 * @author jojo
 * Sep 9, 2019 10:54:59 PM
 */
public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        int idx = 0, len = asteroids.length;
        Stack<Integer> stack = new Stack<>();
        
        while(idx < len){
            // if the stack is empty or asteroid going right then store. 
            if(stack.isEmpty() || asteroids[idx] > 0){
                stack.push(asteroids[idx++]);
            }
            // if stack top and asteroid going left then store.
            else if(stack.peek() < 0 && asteroids[idx] < 0){
                stack.push(asteroids[idx++]);
            }
            // else there is a collision. 
            else{
                
                int top = stack.pop();
                
                // if the incoming is >= top
                if(Math.abs(top) <= Math.abs(asteroids[idx])){
                    
                    // if they are equal both explodes
                    if(Math.abs(top) == Math.abs(asteroids[idx])){
                        idx++;
                    }
                }
                
                // if incoming is smaller than top, then the smaller explodes and the top stays.
                else{
                    stack.push(top);
                    idx++;
                }
            }
        }
        
        int[] result = new int[stack.size()];
        int i = result.length - 1;
        while(!stack.isEmpty()){
            result[i--] = stack.pop();
        }
        
        return result;
    }
}
