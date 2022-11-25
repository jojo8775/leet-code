package interview.leetcode.prob;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.

We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:

Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions. Answers within 10-5 of the actual answer will be accepted.

 

Example 1:

Input: quality = [10,20,5], wage = [70,50,30], k = 2
Output: 105.00000
Explanation: We pay 70 to 0th worker and 35 to 2nd worker.
Example 2:

Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
Output: 30.66667
Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.
 

Constraints:

n == quality.length == wage.length
1 <= k <= n <= 104
1 <= quality[i], wage[i] <= 104
Accepted
56.1K
Submissions
107.8K
 * @author jojo
 * Nov 25, 2022 11:05:16 AM
 */
public class MinimumCostToHireKWorkers {
	public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int len = quality.length;
        
        double[][] workers = new double[len][2];
        
        for(int i=0; i<len; i++){
            // since the wage should be based on quality ratio so stoing 
            // [0]: wage per quality unit 
            // [1]: total quality of a worker. 
            // the minimum wage should be the [0] * [1];
            workers[i] = new double[]{(double)wage[i] / quality[i], quality[i]};
        }
        
        // sorting based on the wage per quality unit in asc order 
        Arrays.sort(workers, (a,b) -> Double.compare(a[0], b[0]));
        
        // storing the quality in a max heap. So that we can remove the max quality worker to reduce overall cost. 
        PriorityQueue<Double> pQueue = new PriorityQueue<>((a,b) -> Double.compare(b, a));
        
        double result = Double.MAX_VALUE;
        double qualitySum = 0;
        
        for(int i=0; i<len; i++){
            qualitySum += workers[i][1];
            pQueue.offer(workers[i][1]);
                
            if(pQueue.size() > k){
                // removing the highesst quality worker to reduce the cost of operation. 
                qualitySum -= pQueue.poll();
            }
            
            if(pQueue.size() == k){
                // since we have the sum of quality, if we multuply with the highest quality unit,
                // all the workers will have minimum wage + the wage should be as per the ratio of the highest
                // paid worker. 
                result = Math.min(result, qualitySum * workers[i][0]);
            }
        }
        
        return result;
    }
}
