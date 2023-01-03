package interview.leetcode.prob;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You have n bags numbered from 0 to n - 1. You are given two 0-indexed integer arrays capacity and rocks. The ith bag can hold a maximum of capacity[i] rocks and currently contains rocks[i] rocks. You are also given an integer additionalRocks, the number of additional rocks you can place in any of the bags.

Return the maximum number of bags that could have full capacity after placing the additional rocks in some bags.

 

Example 1:

Input: capacity = [2,3,4,5], rocks = [1,2,4,4], additionalRocks = 2
Output: 3
Explanation:
Place 1 rock in bag 0 and 1 rock in bag 1.
The number of rocks in each bag are now [2,3,4,4].
Bags 0, 1, and 2 have full capacity.
There are 3 bags at full capacity, so we return 3.
It can be shown that it is not possible to have more than 3 bags at full capacity.
Note that there may be other ways of placing the rocks that result in an answer of 3.
Example 2:

Input: capacity = [10,2,2], rocks = [2,2,0], additionalRocks = 100
Output: 3
Explanation:
Place 8 rocks in bag 0 and 2 rocks in bag 2.
The number of rocks in each bag are now [10,2,2].
Bags 0, 1, and 2 have full capacity.
There are 3 bags at full capacity, so we return 3.
It can be shown that it is not possible to have more than 3 bags at full capacity.
Note that we did not use all of the additional rocks.
 

Constraints:

n == capacity.length == rocks.length
1 <= n <= 5 * 104
1 <= capacity[i] <= 109
0 <= rocks[i] <= capacity[i]
1 <= additionalRocks <= 109
Accepted
39,430
Submissions
60,526
 * @author jojo
 * Dec 26, 2022 8:48:47 PM
 */
public class MaximumBagsWithFullCapacityOfRocks {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int len = rocks.length;
        
        int[] arr = new int[len];
        
        for(int i=0; i<len; i++){
            arr[i] = capacity[i] - rocks[i];
        }
        
        int count = 0;
        
        Arrays.sort(arr);
        
        for(int i=0; i<len && additionalRocks >= 0; i++){
            if(additionalRocks - arr[i] >= 0){
                count++;
            }
            
            additionalRocks -= arr[i];
        }
        
        return count;
    }
    
    
    public int maximumBags_1(int[] capacity, int[] rocks, int additionalRocks) {
        PriorityQueue<int[]> pQueue = new PriorityQueue<>((a,b) -> (a[0]-a[1]) - (b[0]-b[1]));
        
        int len = rocks.length;
        
        for(int i=0; i<len; i++){
            pQueue.offer(new int[]{capacity[i], rocks[i]});
        }
        
        int result = 0;
        
        while(additionalRocks > 0 && !pQueue.isEmpty()){
            int[] top = pQueue.poll();
            
            int diff = top[0] - top[1];
            
            if(additionalRocks - diff >= 0){
                result++;
            }
            
            additionalRocks -=  diff;
        }
        
        return result;
    }
}
