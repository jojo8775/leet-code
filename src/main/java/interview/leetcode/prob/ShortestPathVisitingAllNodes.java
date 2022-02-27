package interview.leetcode.prob;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.

Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.

 

Example 1:


Input: graph = [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]
Example 2:


Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]
 

Constraints:

n == graph.length
1 <= n <= 12
0 <= graph[i].length < n
graph[i] does not contain i.
If graph[a] contains b, then graph[b] contains a.
The input graph is always connected.
Accepted
37,352
Submissions
64,709
 * @author jojo
 * Feb 26, 2022 12:34:59 AM
 */
public class ShortestPathVisitingAllNodes {
    public int shortestPathLength(int[][] graph) {
        int len = graph.length, mask = 1, dist = 0;
        
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        for(int i=0; i<len; i++){
            mask |= (1 << i);
            
            int[] entry = new int[]{1 << i, i};
            visited.add(entry[0] + " : " + entry[1]);
            queue.offer(entry);
        }
        
        while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int i=0; i<size; i++){
                int[] top = queue.poll();
                int curPath = top[0], curNode = top[1];
                
                if(curPath == mask){
                    return dist;
                }
                
                for(int nei : graph[curNode]){
                    int nextPath = curPath | (1 << nei);
                    
                    if(visited.add(nextPath + " : " + nei)){
                        queue.offer(new int[]{nextPath, nei});
                    }
                }
            }
            
            dist++;
        }
        
        return -1;
    }
	
	public int shortestPathLength_old(int[][] graph) {
        if(graph == null || graph.length <= 1) {
        	return 0;
        }
        
        int init = 0, N = graph.length;
        for(int i = 0; i < N; i++) {
            init |= (1 << i);
        }
        
        boolean[][] visited = new boolean[N][init + 1];
        Queue<int[]> queue = new LinkedList<>();
        
        for(int i = 0; i < N; i++) {
            int state = init & ~(1 << i);
            queue.offer(new int[]{i, state});
            visited[i][state] = true;
        }
        
        int steps = 0;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            for(int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int[] neighbors = graph[cur[0]];
                for(int neighbor : neighbors) {
                    int nextState = cur[1] & ~(1 << neighbor);
                    
                    if(nextState == 0) {
                    	return steps;
                    }
                    
                    if(!visited[neighbor][nextState]) {
                        visited[neighbor][nextState] = true;
                        queue.offer(new int[]{neighbor, nextState});
                    }
                }
            }
        }
        
		return steps;
	}
}
