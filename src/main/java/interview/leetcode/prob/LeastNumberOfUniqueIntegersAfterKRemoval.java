package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.

 

Example 1:

Input: arr = [5,5,4], k = 1
Output: 1
Explanation: Remove the single 4, only 5 is left.
Example 2:
Input: arr = [4,3,1,1,3,3,2], k = 3
Output: 2
Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
 

Constraints:

1 <= arr.length <= 10^5
1 <= arr[i] <= 10^9
0 <= k <= arr.length
Accepted
126,339
Submissions
218,450
 * @author jojo
 * Feb. 15, 2024 7:56:04 p.m.
 */
public class LeastNumberOfUniqueIntegersAfterKRemoval {
	public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int max = 0;
        for(int n : arr){
            map.put(n, map.getOrDefault(n, 0) + 1);
            max = Math.max(max, map.get(n));
        }
        
        int[] freqCount = new int[max + 1];
        
        for(int key : map.keySet()){
            freqCount[map.get(key)]++;
        }
        
        for(int i=1; i<=max && k > 0; i++){
            int val = freqCount[i] * i;
            
            if(val != 0){
                freqCount[i] -= (k / i);
                k -= val;
            }                      
        }
        
        
        int count = 0;
        
        for(int i=0; i<=max; i++){
            if(freqCount[i] > 0){
                count += freqCount[i];
            }
        }
        
        return count;
    }
    
    
    
    public int findLeastNumOfUniqueInts_1(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int n : arr){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a - b);
        
        for(int key : map.keySet()){
            pq.offer(map.get(key));
        }
        
        while(k > 0 && !pq.isEmpty()){
            if(pq.peek() <= k){
                k -= pq.poll();
            }
            else{
                break;
            }
        }
        
        return pq.size();
    }
}
