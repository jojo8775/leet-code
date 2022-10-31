package interview.leetcode.prob;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be available to process at enqueueTimei and will take processingTimei to finish processing.

You have a single-threaded CPU that can process at most one task at a time and will act in the following way:

If the CPU is idle and there are no available tasks to process, the CPU remains idle.
If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
Once a task is started, the CPU will process the entire task without stopping.
The CPU can finish a task then start a new one instantly.
Return the order in which the CPU will process the tasks.

 

Example 1:

Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
Output: [0,2,3,1]
Explanation: The events go as follows: 
- At time = 1, task 0 is available to process. Available tasks = {0}.
- Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
- At time = 2, task 1 is available to process. Available tasks = {1}.
- At time = 3, task 2 is available to process. Available tasks = {1, 2}.
- Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
- At time = 4, task 3 is available to process. Available tasks = {1, 3}.
- At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
- At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
- At time = 10, the CPU finishes task 1 and becomes idle.
Example 2:

Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
Output: [4,3,2,0,1]
Explanation: The events go as follows:
- At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
- Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
- At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
- At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
- At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
- At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
- At time = 40, the CPU finishes task 1 and becomes idle.
 

Constraints:

tasks.length == n
1 <= n <= 105
1 <= enqueueTimei, processingTimei <= 109
Accepted
42,778
Submissions
102,021
 * @author jojo
 * Oct 30, 2022 8:44:55 PM
 */
public class SingleThreadedCPU {
	public int[] getOrder(int[][] tasks) {
        int len = tasks.length;
        int[][] events = new int[len][3];
        
        for(int i=0; i<len; i++){
            events[i] = new int[] {i, tasks[i][0], tasks[i][1]};
        }
        
        // sorting based on the job scheduled start time.
        Arrays.sort(events, (a,b) -> a[1] - b[1]);
        
        // returns the smallest processing time job. If there is a conflict returns the smallest index event 
        PriorityQueue<int[]> pQueue = new PriorityQueue<>((a,b) -> a[2] == b[2] ? a[0] - b[0] : a[2] - b[2]);
        
        int[] result = new int[len];
        
        // curTime: represents the time at which the cpu will be ready to take the next job
        int resultIdx = 0, eventIdx = 0, curTime = 0;
        
        while(resultIdx < len){
            // represents jobs which are scheduled while the cpu is executing the current job.
            while(eventIdx < len && events[eventIdx][1] <= curTime){
                pQueue.offer(events[eventIdx++]);
            }
            
            // represents the cpu at idle state waiting for next job
            if(pQueue.isEmpty()){
                curTime = events[eventIdx][1];
                continue;
            }
            
            // represts cpu taking the smallest job from the queue
            int[] bestFitEvent = pQueue.poll();
            result[resultIdx++] = bestFitEvent[0];
            
            // updating the time at which the CPU will be ready for the next job
            curTime += bestFitEvent[2];
        }
        
        return result;
    }   
}
