package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * You are given two integers, n and threshold, as well as a directed weighted graph of n nodes numbered from 0 to n - 1. The graph is represented by a 2D integer array edges, where edges[i] = [Ai, Bi, Wi] indicates that there is an edge going from node Ai to node Bi with weight Wi.

You have to remove some edges from this graph (possibly none), so that it satisfies the following conditions:

Node 0 must be reachable from all other nodes.
The maximum edge weight in the resulting graph is minimized.
Each node has at most threshold outgoing edges.
Return the minimum possible value of the maximum edge weight after removing the necessary edges. If it is impossible for all conditions to be satisfied, return -1.

 

Example 1:

Input: n = 5, edges = [[1,0,1],[2,0,2],[3,0,1],[4,3,1],[2,1,1]], threshold = 2

Output: 1

Explanation:



Remove the edge 2 -> 0. The maximum weight among the remaining edges is 1.

Example 2:

Input: n = 5, edges = [[0,1,1],[0,2,2],[0,3,1],[0,4,1],[1,2,1],[1,4,1]], threshold = 1

Output: -1

Explanation: 

It is impossible to reach node 0 from node 2.

Example 3:

Input: n = 5, edges = [[1,2,1],[1,3,3],[1,4,5],[2,3,2],[3,4,2],[4,0,1]], threshold = 1

Output: 2

Explanation: 



Remove the edges 1 -> 3 and 1 -> 4. The maximum weight among the remaining edges is 2.

Example 4:

Input: n = 5, edges = [[1,2,1],[1,3,3],[1,4,5],[2,3,2],[4,0,1]], threshold = 1

Output: -1

 

Constraints:

2 <= n <= 105
1 <= threshold <= n - 1
1 <= edges.length <= min(105, n * (n - 1) / 2).
edges[i].length == 3
0 <= Ai, Bi < n
Ai != Bi
1 <= Wi <= 106
There may be multiple edges between a pair of nodes, but they must have unique weights.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
2.5K
Submissions
9.4K
Acceptance Rate
26.2%
 * @author jojo
 * Jan. 11, 2025 11:09:35 p.m.
 */
public class MinimizeTheMaximumEdgeWeightOfGraph {
	public int minMaxWeight(int n, int[][] edges, int threshold) {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for(int[] e : edges){
            // the trick is to reverse the lookup so that every nodes are leaving the 0 -> n
            graph.computeIfAbsent(e[1], v -> new ArrayList<>()).add(new int[]{e[0], e[2]});
        }

        boolean canReach = bfs(graph, 0, n);

        if(!canReach){
            return -1;
        }

        // the threshold doesnt matter because for finding the min cost we just need
        // to find the min cost to connect the graph.
        return maxEdge(graph, 0);
    }

    // finds if all nodes can be reached from node 0
    private boolean bfs(Map<Integer,List<int[]>> map, int start, int n){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        n--;

        while(!queue.isEmpty()){
            int top = queue.poll();

            for(int[] nei : map.getOrDefault(top, new ArrayList<>())){
                if(visited.add(nei[0])){
                    n--;
                    queue.offer(nei[0]);
                }
            }
        }

        return n == 0;
    }

    // this is same as dijkstra algorithm to find the smallest path from 0 to all the nodes.
    private int maxEdge(Map<Integer, List<int[]>> graph, int start){
        // contains the node and the weight
        // 1: weight 0: node
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});

        Set<Integer> visited = new HashSet<>();
        
        int max = 0;

        while(!pq.isEmpty()){
            int[] top = pq.poll();
            int curCost = top[1];
            int curNode = top[0];

            if(visited.contains(curNode)){
                continue;
            }

            visited.add(curNode);

            for(int nei[] : graph.getOrDefault(curNode, new ArrayList<>())){
                int nextCost = nei[1], nextNode = nei[0];
                
                pq.offer(new int[]{nextNode, nextCost});
            }

             max = Math.max(max, curCost);
        }

        return max;
    }
}
