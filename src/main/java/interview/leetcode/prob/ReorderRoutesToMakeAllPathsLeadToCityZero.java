package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach city 0 after reorder.

 

Example 1:


Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 2:


Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 3:

Input: n = 3, connections = [[1,0],[2,0]]
Output: 0
 

Constraints:

2 <= n <= 5 * 104
connections.length == n - 1
connections[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
Accepted
228,647
Submissions
354,163
 * 
 * Dec 10, 2024 - 7:22:55 PM
 * Jojo 
 */
public class ReorderRoutesToMakeAllPathsLeadToCityZero {
	public int minReorder(int n, int[][] connections) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        
        for(int[] c : connections){
            graph.computeIfAbsent(c[0], v -> new ArrayList<>()).add(new int[]{c[1], 1}); // 1: represents road is present.
            graph.computeIfAbsent(c[1], v -> new ArrayList<>()).add(new int[]{c[0], 0}); // 0: road is not present.
        }
        
        // queue to perform BFS --- DFS will also work here.
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        
        int cost = 0;
        
        while(!queue.isEmpty()){
            int top = queue.poll();
            
            // we are parsing from o <-> n. Which is opposite to what the problem is asking. 
            // for this reason the cost is being calculated from the path present indicator
            // if there is a path from a -> b then there is no path for b -> a which we want.
            for(int[] nei : graph.getOrDefault(top, new ArrayList<>())){
                if(visited.add(nei[0])){
                    queue.offer(nei[0]);
                    cost += nei[1];
                }
            }
        }
        
        return cost;
    }
}
