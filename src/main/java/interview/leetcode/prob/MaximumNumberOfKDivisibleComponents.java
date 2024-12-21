package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * There is an undirected tree with n nodes labeled from 0 to n - 1. You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

You are also given a 0-indexed integer array values of length n, where values[i] is the value associated with the ith node, and an integer k.

A valid split of the tree is obtained by removing any set of edges, possibly empty, from the tree such that the resulting components all have values that are divisible by k, where the value of a connected component is the sum of the values of its nodes.

Return the maximum number of components in any valid split.

 

Example 1:


Input: n = 5, edges = [[0,2],[1,2],[1,3],[2,4]], values = [1,8,1,4,4], k = 6
Output: 2
Explanation: We remove the edge connecting node 1 with 2. The resulting split is valid because:
- The value of the component containing nodes 1 and 3 is values[1] + values[3] = 12.
- The value of the component containing nodes 0, 2, and 4 is values[0] + values[2] + values[4] = 6.
It can be shown that no other valid split has more than 2 connected components.
Example 2:


Input: n = 7, edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [3,0,6,1,5,2,1], k = 3
Output: 3
Explanation: We remove the edge connecting node 0 with 2, and the edge connecting node 0 with 1. The resulting split is valid because:
- The value of the component containing node 0 is values[0] = 3.
- The value of the component containing nodes 2, 5, and 6 is values[2] + values[5] + values[6] = 9.
- The value of the component containing nodes 1, 3, and 4 is values[1] + values[3] + values[4] = 6.
It can be shown that no other valid split has more than 3 connected components.
 

Constraints:

1 <= n <= 3 * 104
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
values.length == n
0 <= values[i] <= 109
1 <= k <= 109
Sum of values is divisible by k.
The input is generated such that edges represents a valid tree.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
20.7K
Submissions
33.2K
Acceptance Rate
62.4%
 * 
 * 
 * Dec 20, 2024 - 9:14:57 PM
 * Jojo 
 */
public class MaximumNumberOfKDivisibleComponents {
	public int maxKDivisibleComponents_dfs(int n, int[][] edges, int[] values, int k) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // creating the graph 
        for(int[] e : edges){
            graph.computeIfAbsent(e[0], v -> new ArrayList<>()).add(e[1]);
            graph.computeIfAbsent(e[1], v -> new ArrayList<>()).add(e[0]);
        }

        int[] count = {0};
        dfs(graph, -1, 0, values, k, count);

        return count[0];
    }

    private int dfs(Map<Integer, List<Integer>> graph, int parent, int cur, int[] values, int k, int[] count){
        int sum = 0;

        for(int nei : graph.getOrDefault(cur, new ArrayList<>())){
            if(nei == parent){
                continue;
            }

            sum += dfs(graph, cur, nei, values, k, count);

            sum %= k;
        }

        sum += values[cur];
        sum %= k;

        // the idea is if the leaf sum % k == 0 then a split can be formed 
        if(sum == 0){
            count[0]++;
        }

        // since sum % k == 0, it is safe to bubble up the value to the parent without 
        // putting the sum as 0 when a split is performed.
        return sum;
    }

    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        if(n < 2){
            return n;
        }

        int[] indegree = new int[n];
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // creating the graph 
        for(int[] e : edges){
            graph.computeIfAbsent(e[0], v -> new ArrayList<>()).add(e[1]);
            graph.computeIfAbsent(e[1], v -> new ArrayList<>()).add(e[0]);

            indegree[e[0]]++;
            indegree[e[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        // all leaf will have indegree as 1 
        for(int i=0; i<indegree.length; i++){
            if(indegree[i] == 1){
                queue.offer(i);
            }
        }

        // coping over the value as long to prevent overflow.
        long[] nodeVal = new long[n];
        for(int i=0; i<n; i++){
            nodeVal[i] = values[i];
        }

        int splitCount = 0;

        // idea is to do a BFS from the leaf to root, and when the sum % k == 0 increment the split count;
        while(!queue.isEmpty()){
            int leaf = queue.poll();

            indegree[leaf]--;

            long curVal = nodeVal[leaf];

            if(curVal % k == 0){
                splitCount++;

                // resetting the curVal to zero because when we split the child (leaf here) doenst contribute 
                // to the parent sum.
                curVal = 0;
            }

            // bubbling up the the leaf node value to the parent node.
            for(int nei : graph.get(leaf)){
                if(indegree[nei] == 0){
                    continue;
                }

                indegree[nei]--;
                nodeVal[nei] += curVal;

                if(indegree[nei] == 1){
                    queue.offer(nei);
                }
            }
        }

        return splitCount;
    }
}
