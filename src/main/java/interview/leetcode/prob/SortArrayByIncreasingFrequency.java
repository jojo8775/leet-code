package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author jojo
 * Jul 8, 2021  12:11:26 AM
 */
public class SortArrayByIncreasingFrequency {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a,b) -> {
            if(a.getValue() == b.getValue()){
                return b.getKey() - a.getKey();
            }
            
            return a.getValue() - b.getValue();
        });
        
        map.entrySet().forEach(e -> pq.offer(e));
        
        int[] result = new int[nums.length];
        int idx = 0;
        
        while(!pq.isEmpty()){
            Map.Entry<Integer, Integer> e = pq.poll();
            
            int count = e.getValue(), key = e.getKey();
            while(count > 0){
                count--;
                result[idx++] = key;    
            }
        }
        
        return result;
    }
}
