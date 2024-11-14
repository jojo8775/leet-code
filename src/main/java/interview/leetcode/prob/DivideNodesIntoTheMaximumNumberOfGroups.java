package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * You are given a positive integer n representing the number of nodes in an undirected graph. The nodes are labeled from 1 to n.

You are also given a 2D integer array edges, where edges[i] = [ai, bi] indicates that there is a bidirectional edge between nodes ai and bi. Notice that the given graph may be disconnected.

Divide the nodes of the graph into m groups (1-indexed) such that:

Each node in the graph belongs to exactly one group.
For every pair of nodes in the graph that are connected by an edge [ai, bi], if ai belongs to the group with index x, and bi belongs to the group with index y, then |y - x| = 1.
Return the maximum number of groups (i.e., maximum m) into which you can divide the nodes. Return -1 if it is impossible to group the nodes with the given conditions.

 

Example 1:


Input: n = 6, edges = [[1,2],[1,4],[1,5],[2,6],[2,3],[4,6]]
Output: 4
Explanation: As shown in the image we:
- Add node 5 to the first group.
- Add node 1 to the second group.
- Add nodes 2 and 4 to the third group.
- Add nodes 3 and 6 to the fourth group.
We can see that every edge is satisfied.
It can be shown that that if we create a fifth group and move any node from the third or fourth group to it, at least on of the edges will not be satisfied.
Example 2:

Input: n = 3, edges = [[1,2],[2,3],[3,1]]
Output: -1
Explanation: If we add node 1 to the first group, node 2 to the second group, and node 3 to the third group to satisfy the first two edges, we can see that the third edge will not be satisfied.
It can be shown that no grouping is possible.
 

Constraints:

1 <= n <= 500
1 <= edges.length <= 104
edges[i].length == 2
1 <= ai, bi <= n
ai != bi
There is at most one edge between any pair of vertices.
Accepted
9,959
Submissions
24,715
 * 
 * Nov 10, 2024 - 11:57:02 AM
 * Jojo 
 */
public class DivideNodesIntoTheMaximumNumberOfGroups {
	public int magnificentSets(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for(int[] e : edges){
            map.computeIfAbsent(e[0], v -> new ArrayList<>()).add(e[1]);
            map.computeIfAbsent(e[1], v -> new ArrayList<>()).add(e[0]);
        }
        
        int totalGroup = 0;
        
        boolean[] visited = new boolean[n + 1];
        
        // the graph can be disjoint for this reason the for loop is needed.
        for(int i=1; i<=n; i++){
            
            if(visited[i] == false){
                int val = bfs(i, map, visited);
                
                if(val == -1){
                    return -1;
                }
                
                totalGroup += val;
            }
        }
        
        return totalGroup;
    }
    
    private int bfs(int rootNode, Map<Integer, List<Integer>> graph, boolean[] visited){
        // when calculating max group orientation within a graph this 
        // prevents from going into infinite loop.
        if(visited[rootNode] == true){
            return 0;
        }
        
        visited[rootNode] = true;
        
        Map<Integer, Integer> groupMap = new HashMap<>();
        groupMap.put(rootNode, 1);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(rootNode);
        
        int maxGroupCount = 1;
        
        while(!queue.isEmpty()){
            int top = queue.poll();
            int curGroup = groupMap.get(top);
            
            maxGroupCount = Math.max(maxGroupCount, curGroup);
            
            for(int nei : graph.getOrDefault(top, new ArrayList<>())){
                if(!groupMap.containsKey(nei)){
                    groupMap.put(nei, curGroup + 1);
                    queue.offer(nei);
                }
                // based on the problem statement if the abs diff is not 1 then it 
                // cannot be grouped. 
                else if (Math.abs(curGroup - groupMap.get(nei)) != 1){
                    return -1;
                }
            }
        }
        
        // since each node within a graph can be grouped differently we need to call the bfs for each node 
        // and take the max for each group.
        for(int node : groupMap.keySet()){
            maxGroupCount = Math.max(maxGroupCount, bfs(node, graph, visited));
        }
        
        return maxGroupCount;
    }
}
