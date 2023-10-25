package interview.leetcode.prob;

/**
 * Alice and Bob have an undirected graph of n nodes and three types of edges:

Type 1: Can be traversed by Alice only.
Type 2: Can be traversed by Bob only.
Type 3: Can be traversed by both Alice and Bob.
Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi, find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.

Return the maximum number of edges you can remove, or return -1 if Alice and Bob cannot fully traverse the graph.

 

Example 1:



Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
Output: 2
Explanation: If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob. Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.
Example 2:



Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
Output: 0
Explanation: Notice that removing any edge will not make the graph fully traversable by Alice and Bob.
Example 3:



Input: n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
Output: -1
Explanation: In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1. Therefore it's impossible to make the graph fully traversable.
 

 

Constraints:

1 <= n <= 105
1 <= edges.length <= min(105, 3 * n * (n - 1) / 2)
edges[i].length == 3
1 <= typei <= 3
1 <= ui < vi <= n
All tuples (typei, ui, vi) are distinct.
Accepted
55,485
Submissions
85,975
 * @author jojo
 * Oct. 23, 2023 9:18:49 p.m.
 */
public class RemoveMaxNoOfEdgesToKeepGraphFullyTraversable {
	public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind acliceGraph = new UnionFind(n), bobGraph = new UnionFind(n);
        
        int neededEdges = 0;
        
        // need to do the edge 3 first because it contributes to both Alice and Bob 
        for(int[] e : edges){
            if(e[0] == 3){
                // System.out.println("B: " + neededEdges);
                
                //neededEdges += (acliceGraph.union(e[1], e[2]) | bobGraph.union(e[1], e[2]));
                
                int au = acliceGraph.union(e[1], e[2]), bu = bobGraph.union(e[1], e[2]);
                if(au  == 1 || bu == 1){
                    neededEdges++;
                }
                // System.out.println("A: " + neededEdges);
            }
        }
        
        for(int[] e : edges){
            if(e[0] == 1){
                neededEdges += acliceGraph.union(e[1], e[2]);
            }
            
            else if(e[0] == 2){
                neededEdges += bobGraph.union(e[1], e[2]);
            }
        }
        
        if(acliceGraph.isConnected() && bobGraph.isConnected()){
            return edges.length - neededEdges;
        }
        
        return -1;
    }
    
    private static class UnionFind{
        int[] parentArr, rankArr;
        int disjointNodes;
        
        public UnionFind(int n){
            disjointNodes = n;
            
            parentArr = new int[n+1];
            rankArr = new int[n+1];
            
            for(int i=0; i<=n; i++){
                parentArr[i] = i;
                rankArr[i] = 1;
            }
        }
        
        public int union(int a, int b){
            int p1 = findParent(a), p2 = findParent(b);
            
            if(p1 == p2){
                return 0;
            }
            
            if(rankArr[p1] > rankArr[p2]){
                rankArr[p1] += rankArr[p2];
                parentArr[p2] = p1;
            }
            else if(rankArr[p1] < rankArr[p2]){
                rankArr[p2] += rankArr[p1];
                parentArr[p1] = p2;
            }
            else{
                rankArr[p1]++;
                parentArr[p2] = p1;
            }
            
            disjointNodes--;
            
            return 1;
        }
        
        private int findParent(int idx){
            while(idx != parentArr[idx]){
                int next = parentArr[idx];
                parentArr[idx] = parentArr[next];
                idx = next;
            }
            
            return idx;
        }
        
        public boolean isConnected(){
            return disjointNodes == 1;
        }
    }
}
