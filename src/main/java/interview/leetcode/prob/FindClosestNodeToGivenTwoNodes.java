package interview.leetcode.prob;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.

The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from i, then edges[i] == -1.

You are also given two integers node1 and node2.

Return the index of the node that can be reached from both node1 and node2, such that the maximum between the distance from node1 to that node, and from node2 to that node is minimized. If there are multiple answers, return the node with the smallest index, and if no possible answer exists, return -1.

Note that edges may contain cycles.

 

Example 1:


Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
Output: 2
Explanation: The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
The maximum of those two distances is 1. It can be proven that we cannot get a node with a smaller maximum distance than 1, so we return node 2.
Example 2:


Input: edges = [1,2,-1], node1 = 0, node2 = 2
Output: 2
Explanation: The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
The maximum of those two distances is 2. It can be proven that we cannot get a node with a smaller maximum distance than 2, so we return node 2.
 

Constraints:

n == edges.length
2 <= n <= 105
-1 <= edges[i] < n
edges[i] != i
0 <= node1, node2 < n
Accepted
71,850
Submissions
157,334
 * @author jojo
 * Oct. 19, 2023 12:22:14 p.m.
 */
public class FindClosestNodeToGivenTwoNodes {
	public int closestMeetingNode(int[] edges, int node1, int node2) {
        int nodeCount = edges.length;
        int[] dist1 = new int[nodeCount], dist2 = new int[nodeCount];

        Arrays.fill(dist1, -1);
        Arrays.fill(dist2, -1);

        bfs(edges, node1, dist1);
        bfs(edges, node2, dist2);
        
        // System.out.println("dist1:");
        // print(dist1);
        
        // System.out.println("dist2:");
        // print(dist2);

        int minDistNode = -1, minDist = Integer.MAX_VALUE;

        for(int i=0; i<nodeCount; i++){
            if(dist1[i] != -1 && dist2[i] != -1){
                int curMax = Math.max(dist1[i], dist2[i]);

                if(minDist > curMax){
                    minDist = curMax;
                    minDistNode = i;
                }
            }
        }

        return minDistNode;
    }

    private void bfs(int[] edges, int idx, int[] distArr){
        int len = edges.length;
        boolean[] visited = new boolean[len];
        visited[idx] = true;
        distArr[idx] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(idx);

        while(!queue.isEmpty()){
            int top = queue.poll();
            
            int next = edges[top];

            if(next == -1 || visited[next]){
                continue;
            }

            visited[next] = true;
            distArr[next] = distArr[top] + 1;
            queue.offer(next);
        }
    }
}
