package interview.leetcode.prob;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You have n robots. You are given two 0-indexed integer arrays, chargeTimes and runningCosts, both of length n. The ith robot costs chargeTimes[i] units to charge and costs runningCosts[i] units to run. You are also given an integer budget.

The total cost of running k chosen robots is equal to max(chargeTimes) + k * sum(runningCosts), where max(chargeTimes) is the largest charge cost among the k robots and sum(runningCosts) is the sum of running costs among the k robots.

Return the maximum number of consecutive robots you can run such that the total cost does not exceed budget.

 

Example 1:

Input: chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
Output: 3
Explanation: 
It is possible to run all individual and consecutive pairs of robots within budget.
To obtain answer 3, consider the first 3 robots. The total cost will be max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 which is less than 25.
It can be shown that it is not possible to run more than 3 consecutive robots within budget, so we return 3.
Example 2:

Input: chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
Output: 0
Explanation: No robot can be run that does not exceed the budget, so we return 0.
 

Constraints:

chargeTimes.length == runningCosts.length == n
1 <= n <= 5 * 104
1 <= chargeTimes[i], runningCosts[i] <= 105
1 <= budget <= 1015
Accepted
11,445
Submissions
36,209
 * @author jojo
 * Oct 17, 2022 7:52:18 PM
 */
public class MaximumNumberOfRobotsWithinBudget {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        Deque<Integer> maxQueue = new LinkedList<>();
        long runningCostSum = 0, len = runningCosts.length;
        
        int result = 0;
        
        for(int i=0, j=0; i<len; i++){
            runningCostSum += runningCosts[i];
            
            while(!maxQueue.isEmpty() && chargeTimes[maxQueue.peekFirst()] <= chargeTimes[i]){
                maxQueue.pollFirst();
            }
            
            maxQueue.offerFirst(i);
            
            while(j <= i && (chargeTimes[maxQueue.peekLast()] + ((i == j ? 1 : i - j + 1) * runningCostSum)) > budget){
                runningCostSum -= runningCosts[j];
                if(j == maxQueue.peekLast()){
                    maxQueue.pollLast();
                }
                
                j++;
            }
            
            result = Math.max(result, i == j ? 1 : i - j + 1);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	var result = new MaximumNumberOfRobotsWithinBudget().maximumRobots(
    			new int[] {19,3,21}, 
    			new int[] {91,23,39}, 
    			29);
    	
//    	var result = new MaximumNumberOfRobotsWithinBudget().maximumRobots(
//    			new int[] {19,63,21,8,5,46,56,45,54,30,92,63,31,71,87,94,67,8,19,89,79,25}, 
//    			new int[] {91,92,39,89,62,81,33,99,28,99,86,19,5,6,19,94,65,86,17,10,8,42}, 
//    			85);
    	
    	System.out.println(result);
    }
}
