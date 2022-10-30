package interview.leetcode.prob;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Your car starts at position 0 and speed +1 on an infinite number line. Your car can go into negative positions. Your car drives automatically according to a sequence of instructions 'A' (accelerate) and 'R' (reverse):

When you get an instruction 'A', your car does the following:
position += speed
speed *= 2
When you get an instruction 'R', your car does the following:
If your speed is positive then speed = -1
otherwise speed = 1
Your position stays the same.
For example, after commands "AAR", your car goes to positions 0 --> 1 --> 3 --> 3, and your speed goes to 1 --> 2 --> 4 --> -1.

Given a target position target, return the length of the shortest sequence of instructions to get there.

 

Example 1:

Input: target = 3
Output: 2
Explanation: 
The shortest instruction sequence is "AA".
Your position goes from 0 --> 1 --> 3.
Example 2:

Input: target = 6
Output: 5
Explanation: 
The shortest instruction sequence is "AAARA".
Your position goes from 0 --> 1 --> 3 --> 7 --> 7 --> 6.
 

Constraints:

1 <= target <= 104
Accepted
64,329
Submissions
147,628
 * @author jojo
 * Oct 18, 2022 8:51:28 PM
 */
public class RaceCar {
	public int racecar(int target) {
        Set<String> visited = new HashSet<>();
        
        Queue<Car> queue = new LinkedList<>();
        queue.offer(new Car(1, 0));
        
        int instructionCount = 0;
            
        while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int i=0; i<size; i++){
                // at each position there are two options. Going forwars(A) or backward (R)
                Car top = queue.poll();
                
                if(top.position == target){
                    return instructionCount;
                }
                
                // forward movement (A)
                int nextPosition = top.position + top.speed;
                int nextSpeed = top.speed * 2;
                
                while(!visited.contains(nextSpeed + "-" + nextPosition) && Math.abs(target - nextPosition) < target){
                    queue.offer(new Car(nextSpeed, nextPosition));
                    visited.add(nextSpeed + "-" + nextPosition);
                }
                
                // backward movement (R)
                nextPosition =  top.position;
                nextSpeed = top.speed > 0 ? -1 : 1;
                
                while(!visited.contains(nextSpeed + "-" + nextPosition) && Math.abs(target - nextPosition) < target){
                    queue.offer(new Car(nextSpeed, nextPosition));
                    visited.add(nextSpeed + "-" + nextPosition);
                }
            }
            
            instructionCount++;
        }
        
        return -1;
    }
    
    private static class Car{
        int position, speed;
        
        public Car(int speed, int position){
            this.speed = speed;
            this.position = position;
        }
    }
}
