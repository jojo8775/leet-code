package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.

 

Example 1:



Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job. 
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
Example 2:



Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job. 
Profit obtained 150 = 20 + 70 + 60.
Example 3:



Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6
 

Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 104
1 <= startTime[i] < endTime[i] <= 109
1 <= profit[i] <= 104
Accepted
97,200
Submissions
191,735
 * @author jojo
 * Jan 20, 2022 11:33:42 PM
 */
public class MaximumProfitInJobScheduling {
	public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len = startTime.length;
        
        // 0 - start time; 1 - end time; 2 - profit;
        List<int[]> jobs = new ArrayList<>();
        
        for(int i=0; i<len; i++){
            jobs.add(new int[]{startTime[i], endTime[i], profit[i]});
        }
        
        // sorting by start time.
        Collections.sort(jobs, (a,b) -> a[0] - b[0]);
        // sorting for easy lookup of next start time. 
        Arrays.sort(startTime);
        
        return findMaxProfit(jobs, startTime, len, 0, new HashMap<Integer, Integer>());
    }
    
    private int findMaxProfit(List<int[]> jobs, int[] startTime, int len, int pos, Map<Integer, Integer> memo){
        // reached the last job, no more to pick for profit. 
        if(pos == len){
            return 0;
        }
        
        // Check if the max profit for pos was previously computed.
        if(memo.containsKey(pos)){
            return memo.get(pos);
        }
        
        // next non-conflicting position if the current job is taken.
        int nextPos = findNextPos(startTime, jobs.get(pos)[1]);
        
        // 2 options, either take the current job and look for the next non-conflicting job, or skip the ocurrent job. 
        int profitBySkipping = findMaxProfit(jobs, startTime, len, pos + 1, memo);
        int profitByTaking = jobs.get(pos)[2] + findMaxProfit(jobs, startTime, len, nextPos, memo);
        
        int maxProfit = Math.max(profitBySkipping, profitByTaking);
        
        // storing the computed result for future ref.
        memo.put(pos, maxProfit);
        
        return maxProfit;
    }
    
    private int findNextPos(int[] arr, int target){
        int beg = 0, end = arr.length - 1, nextIdx = arr.length;
        
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            
            // since the end of current job and start of next job be on the same time.
            if(arr[mid] >= target){
                nextIdx = mid;
                end = mid - 1;
            }
            else{
                beg = mid + 1;
            }
        }
        
        return nextIdx;
    }
}
