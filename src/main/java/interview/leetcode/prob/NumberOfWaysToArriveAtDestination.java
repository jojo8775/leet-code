package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NumberOfWaysToArriveAtDestination {
	private int mod = 1000000007;
    public int countPaths(int n, int[][] roads) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        
        for(int[] r : roads){
        	List<int[]> val = graph.get(r[0]);
        	if(val == null) {
        		val = new ArrayList<>();
        		graph.put(r[0], val);
        	}
        	
        	val.add(new int[] {r[1], r[2]});
        	
        	val = graph.get(r[1]);
        	if(val == null) {
        		val = new ArrayList<>();
        		graph.put(r[1], val);
        	}
        	
        	val.add(new int[] {r[0], r[2]});
        }
        
        // distance of each node from 0
        int[] distArr = new int[n];
        Arrays.fill(distArr, Integer.MAX_VALUE);
        distArr[0] = 0;
        
        // stores the number of ways a node can be reached.
        // since the number of ways can be quite high, using long to avoid overflow.
        long[] dp = new long[n];
        dp[0] = 1;
        
        // taking the lowest weight first. 
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.offer(new int[] {0,0});
        
        while(!pq.isEmpty()){
            int[] top = pq.poll();
            
            int curDist = top[1], curNode = top[0];
            
            // terminating all the ways where a shorted route has been found.
            if(distArr[curNode] < curDist){
                continue;
            }
            
            for(int[] nei : graph.getOrDefault(curNode, new ArrayList<>())){
                if(distArr[nei[0]] > curDist + nei[1]) {
                	distArr[nei[0]] = curDist + nei[1];
                	dp[nei[0]] = dp[curNode];
                    
                    // since the accumulated sum can be quite high we need to store the module opration. 
                    dp[nei[0]] %= mod;
                	
                	pq.offer(new int[] {nei[0], distArr[nei[0]]});
                }
                else if(distArr[nei[0]] == curDist + nei[1]){
                	dp[nei[0]] += dp[curNode];
                	
                	// since the accumulated sum can be quite high we need to store the module opration. 
                	dp[nei[0]] %= mod;
                }
            }
        }
        
        return (int) (dp[n-1]);
    }
}
