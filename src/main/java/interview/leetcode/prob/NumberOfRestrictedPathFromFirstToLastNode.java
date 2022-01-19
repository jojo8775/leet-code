package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * There is an undirected weighted connected graph. You are given a positive integer n which denotes that the graph has n nodes labeled from 1 to n, and an array edges where each edges[i] = [ui, vi, weighti] denotes that there is an edge between nodes ui and vi with weight equal to weighti.

A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start and zk = end and there is an edge between zi and zi+1 where 0 <= i <= k-1.

The distance of a path is the sum of the weights on the edges of the path. Let distanceToLastNode(x) denote the shortest distance of a path between node n and node x. A restricted path is a path that also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.

Return the number of restricted paths from node 1 to node n. Since that number may be too large, return it modulo 109 + 7.

 

Example 1:


Input: n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
Output: 3
Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The three restricted paths are:
1) 1 --> 2 --> 5
2) 1 --> 2 --> 3 --> 5
3) 1 --> 3 --> 5
Example 2:


Input: n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
Output: 1
Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The only restricted path is 1 --> 3 --> 7.
 

Constraints:

1 <= n <= 2 * 104
n - 1 <= edges.length <= 4 * 104
edges[i].length == 3
1 <= ui, vi <= n
ui != vi
1 <= weighti <= 105
There is at most one edge between any two nodes.
There is at least one path between any two nodes.
Accepted
11K
Submissions
29K
 * @author jojo
 * Jan 15, 2022 10:18:52 PM
 */
public class NumberOfRestrictedPathFromFirstToLastNode {
	private int mod = 1000000007;
    
    public int countRestrictedPaths(int n, int[][] edges) {
        // creating graph
        List<List<int[]>> graph = new ArrayList<>();
        
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] e : edges){
            graph.get(e[0]).add(new int[]{e[1], e[2]});
            graph.get(e[1]).add(new int[]{e[0], e[2]});
        }
        
        int[] minDist = new int[n+1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[n] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.offer(new int[]{n, 0});
        
        // finding djkstras algo
        while(!pq.isEmpty()){
            int[] top = pq.poll();
            int curNode = top[0], curWeight = top[1];
            
            for(int[] nei : graph.get(curNode)){
                int nextNode = nei[0], tw = nei[1] + curWeight;
                
                if(minDist[nextNode] > tw){
                    minDist[nextNode] = tw;
                    pq.offer(new int[]{nextNode, tw});
                }
            }
        }
        
        // dfs the restricted path 
        Integer[] memo = new Integer[n+1];
        return dfs(1, n, memo, graph, minDist);
    }
    
    // number of ways to reach from point a to point b
    private int dfs(int cur, int end, Integer[] memo, List<List<int[]>> graph, int[] minDist){
        if(cur == end){
            return 1;
        }
        
        if(memo[cur] != null){
            return memo[cur];
        }
        
        long result = 0;
        
        for(int[] nei : graph.get(cur)){
            int nextNode = nei[0];
            
            if(minDist[nextNode] < minDist[cur]){
                result = (result + dfs(nextNode, end, memo, graph, minDist)) % mod;
            }
        }
        
        result = result % mod;
        memo[cur] = (int) result;
        return memo[cur];
    }
}
