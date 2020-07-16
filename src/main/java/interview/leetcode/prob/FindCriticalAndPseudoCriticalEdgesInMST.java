package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a weighted undirected connected graph with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between nodes fromi and toi. A minimum spanning tree (MST) is a subset of the edges of the graph that connects all vertices without cycles and with the minimum possible total edge weight.

Find all the critical and pseudo-critical edges in the minimum spanning tree (MST) of the given graph. An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. A pseudo-critical edge, on the other hand, is that which can appear in some MSTs but not all.

Note that you can return the indices of the edges in any order.

 

Example 1:



Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
Output: [[0,1],[2,3,4,5]]
Explanation: The figure above describes the graph.
The following figure shows all the possible MSTs:

Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.
Example 2:



Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
Output: [[],[0,1,2,3]]
Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.
 

Constraints:

2 <= n <= 100
1 <= edges.length <= min(200, n * (n - 1) / 2)
edges[i].length == 3
0 <= fromi < toi < n
1 <= weighti <= 1000
All pairs (fromi, toi) are distinct.
Accepted
2,236
Submissions
4,491
 * @author jojo
 * Jul 16, 2020  7:40:24 AM
 */
public class FindCriticalAndPseudoCriticalEdgesInMST {
	public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<Integer> criticals = new ArrayList<>(), pCriticals = new ArrayList<>();
        Map<int[], Integer> edgeMap = new HashMap<>();
        
        for(int i=0; i<n; i++){
            edgeMap.put(edges[i], i);
        }
        
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        
        int minCost = findMinCost(n, edges, null, null);
        
        for(int[] edge : edges){
            int curCost = findMinCost(n, edges, edge, null);
            int edgeIndex = edgeMap.get(edge);
            
            if(curCost > minCost){
                criticals.add(edgeIndex);
            }
            else{
                curCost = findMinCost(n, edges, null, edge);
                
                if(curCost == minCost){
                    pCriticals.add(edgeIndex);
                }
            }
        }
        
        return Arrays.asList(criticals, pCriticals);
    }
    
    private int findMinCost(int n, int[][] edges, int[] skip, int[] pick){
        int cost = 0;
        
        UnionFind u = new UnionFind(n);
        
        if(pick != null){
            cost += pick[2];
            u.union(pick[0], pick[1]);
        }
        
        for(int[] edge : edges){
            if(edge == skip){
                continue;
            }
            
            if(u.union(edge[0], edge[1])){
                cost += edge[2];
            }
        }
        
        return u.count == 1 ? cost : Integer.MAX_VALUE;
    }
    
    private static class UnionFind{
        private int[] parent;
        public int count;
        
        public UnionFind(int n){
            parent = new int[n];
            for(int i=0;i<n; i++) {
            	parent[i] = i;
            }
            count = n;
        }
        
        public int findParent(int n){
            while(parent[n] != n){
                n = parent[n];
            }
            
            return n;
        }
        
        public boolean union(int n1, int n2){
            int p1 = findParent(n1), p2 = findParent(n2);
            
            if(p1 != p2){
                parent[p1] = p2;
                count--;
                return true;
            }
            
            return false;
        }
    }
}
