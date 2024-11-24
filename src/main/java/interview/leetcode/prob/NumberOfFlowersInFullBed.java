package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means the ith flower will be in full bloom from starti to endi (inclusive). You are also given a 0-indexed integer array people of size n, where people[i] is the time that the ith person will arrive to see the flowers.

Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives.

 

Example 1:


Input: flowers = [[1,6],[3,7],[9,12],[4,13]], people = [2,3,7,11]
Output: [1,2,2,2]
Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
For each person, we return the number of flowers in full bloom during their arrival.
Example 2:


Input: flowers = [[1,10],[3,3]], people = [3,3,2]
Output: [2,2,1]
Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
For each person, we return the number of flowers in full bloom during their arrival.
 

Constraints:

1 <= flowers.length <= 5 * 104
flowers[i].length == 2
1 <= starti <= endi <= 109
1 <= people.length <= 5 * 104
1 <= people[i] <= 109
Accepted
87,238
Submissions
153,446
 * 
 * Nov 23, 2024 - 10:34:53 AM
 * Jojo 
 */
public class NumberOfFlowersInFullBed {
	// this is same as meeting room II
    // this is using PQ
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        Arrays.sort(flowers, (a,b) -> a[0] - b[0]);
        
        // this is needed because the people arrival time is not sorted 
        int[][] p = new int[people.length][2];
        for(int i=0; i<people.length; i++){
            p[i][0] = i;
            p[i][1] = people[i];
        }
        
        Arrays.sort(p, (a,b) -> a[1] - b[1]);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a - b);
        
        int[] result = new int[people.length];
        
        for(int i=0, j=0; i< p.length; i++){
            int time = p[i][1];
            
            while(j < flowers.length && flowers[j][0] <= time){
                pq.offer(flowers[j][1]);
                j++;
            }
            
            while(!pq.isEmpty() && pq.peek() < time){
                pq.poll();
            }
            
            result[p[i][0]] = pq.size();
        }
        
        return result;
    }
    
    public int[] fullBloomFlowers_usingDifferenceArray(int[][] flowers, int[] people) {
        TreeMap<Integer, Integer> events = new TreeMap<>();
        
        // at the intiial time there was no event.
        events.put(0,0);
        
        for(int[] f : flowers){
            events.put(f[0], events.getOrDefault(f[0], 0) + 1);
            events.put(f[1] + 1, events.getOrDefault(f[1] + 1, 0) - 1);
        }
        
        int sum = 0;
        
        List<Integer> eventPositions = new ArrayList<>();
        List<Integer> prefixSum = new ArrayList<>();
        
        for(int key : events.keySet()){
            sum += events.get(key);
            
            eventPositions.add(key);
            prefixSum.add(sum);
        }
        
        int[] result = new int[people.length];
        
        for(int i=0; i<people.length; i++){
            int idx = findPos(eventPositions, people[i]);
            
            //System.out.println("idx: " + idx + "  p: " + people[i]);
            
            // idx will not be -1 because event (0,0) is being added. 
            result[i] = prefixSum.get(idx);
        }
    
        return result;
    }
    
    private int findPos(List<Integer> list, int target){
        int beg = 0, end = list.size();
        
        while(beg < end){
            int mid = beg + (end - beg) / 2;
            
            if(list.get(mid) <= target){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
        }
        
        return beg - 1;
    }
}
