package interview.leetcode.prob;

import java.util.Arrays;

/**
 * You are given an integer array jobs, where jobs[i] is the amount of time it takes to complete the ith job.

There are k workers that you can assign jobs to. Each job should be assigned to exactly one worker. The working time of a worker is the sum of the time it takes to complete all jobs assigned to them. Your goal is to devise an optimal assignment such that the maximum working time of any worker is minimized.

Return the minimum possible maximum working time of any assignment.

 

Example 1:

Input: jobs = [3,2,3], k = 3
Output: 3
Explanation: By assigning each person one job, the maximum time is 3.
Example 2:

Input: jobs = [1,2,4,7,8], k = 2
Output: 11
Explanation: Assign the jobs the following way:
Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
Worker 2: 4, 7 (working time = 4 + 7 = 11)
The maximum working time is 11.
 

Constraints:

1 <= k <= jobs.length <= 12
1 <= jobs[i] <= 107
Accepted
19,057
Submissions
44,664
 * @author jojo
 * Nov 11, 2022 11:36:59 PM
 */
public class FindMinimumTImeToFinishAllJobs {
	public int minimumTimeRequired(int[] jobs, int k) {
        if(k >= jobs.length){
            return Arrays.stream(jobs).max().getAsInt();
        }
        
        // sorting to improve runtime 
        Arrays.sort(jobs);
        
        int[] result = {Integer.MAX_VALUE};
        dfs(jobs, jobs.length - 1, new int[k], result, 0);
        
        return result[0];
    }
    
    private void dfs(int[] jobs, int idx, int[] workers, int[] result, int curMax){
    	// base case
        if(idx == -1){
            result[0] = Math.min(result[0], curMax);
            return;
        }
        
        if(curMax > result[0]){
            return;
        }
        
        for(int i = 0; i < workers.length; i++){
        	// if thwew are multiple workwe with same workload then they will take the same time to complete the next work. For thsi reason we are skipping the
        	// same workload. 
            if(i > 0 && workers[i] == workers[i-1]){
                continue;
            }
            
            workers[i] += jobs[idx];
        
            // taking max here because we need to track the max time taken by a worker 
            dfs(jobs, idx - 1, workers, result, Math.max(curMax, workers[i]));
            
            workers[i] -= jobs[idx];
        }
    }
}
