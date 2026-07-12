package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1. You are given an integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree. You are also given an array coins of size n where coins[i] can be either 0 or 1, where 1 indicates the presence of a coin in the vertex i.

Initially, you choose to start at any vertex in the tree. Then, you can perform the following operations any number of times: 

Collect all the coins that are at a distance of at most 2 from the current vertex, or
Move to any adjacent vertex in the tree.
Find the minimum number of edges you need to go through to collect all the coins and go back to the initial vertex.

Note that if you pass an edge several times, you need to count it into the answer several times.

 

Example 1:


Input: coins = [1,0,0,0,0,1], edges = [[0,1],[1,2],[2,3],[3,4],[4,5]]
Output: 2
Explanation: Start at vertex 2, collect the coin at vertex 0, move to vertex 3, collect the coin at vertex 5 then move back to vertex 2.
Example 2:


Input: coins = [0,0,0,1,1,0,0,1], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]
Output: 2
Explanation: Start at vertex 0, collect the coins at vertices 4 and 3, move to vertex 2,  collect the coin at vertex 7, then move back to vertex 0.
 

Constraints:

n == coins.length
1 <= n <= 3 * 104
0 <= coins[i] <= 1
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
ai != bi
edges represents a valid tree.
 
Seen this question in a real interview before?
1/6
Yes
No
Accepted
18,347/46.4K
Acceptance Rate
39.5%
 * @param coins
 * @param edges
 * @return
 */
public class CollectCoinsInATree {
	ublic int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        
        // Step 1: Build the graph using a Map of Sets
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(i, new HashSet<>());
        }
        
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        
        // Step 2: Find and prune initial leaves that DO NOT have a coin
        for (int i = 0; i < n; i++) {
            // starting of with the leaves which has no coins 
            if (adj.get(i).size() == 1 && coins[i] == 0) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                adj.get(v).remove(u); // Remove connection
                if (adj.get(v).size() == 1 && coins[v] == 0) {
                    queue.offer(v);
                }
            }

            // this has no coins so clear it. 
            adj.get(u).clear(); // Completely isolate the pruned node
        }
        
        // Step 3: Now all remaining leaves have a coin.
        // Prune exactly 2 layers of leaves.
        Map<Integer, Integer> distance = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // taking only the leaves which has coins --> at these point these will only be the leaf 
            //if (adj.get(i).size() == 1 && coins[i] == 1) {
            if (adj.get(i).size() == 1) {
                queue.offer(i);
                distance.put(i, 0); // Layer 0
            }
        }
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (distance.get(u) >= 2) continue; // Stop after cutting 2 layers
            
            for (int v : adj.get(u)) {
                adj.get(v).remove(u);
                if (adj.get(v).size() == 1) {
                    distance.put(v, distance.get(u) + 1);
                    queue.offer(v);
                }
            }
            adj.get(u).clear();
        }
        
        // Step 4: Count remaining edges in the trimmed graph
        int remainingEdges = 0;
        for (int[] edge : edges) {
            if (adj.get(edge[0]).size() > 0 && adj.get(edge[1]).size() > 0) {
                remainingEdges++;
            }
        }
        
        return remainingEdges * 2;
    }
}
