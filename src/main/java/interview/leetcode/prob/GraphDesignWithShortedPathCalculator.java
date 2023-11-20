package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * There is a directed weighted graph that consists of n nodes numbered from 0 to n - 1. The edges of the graph are initially represented by the given array edges where edges[i] = [fromi, toi, edgeCosti] meaning that there is an edge from fromi to toi with the cost edgeCosti.

Implement the Graph class:

Graph(int n, int[][] edges) initializes the object with n nodes and the given edges.
addEdge(int[] edge) adds an edge to the list of edges where edge = [from, to, edgeCost]. It is guaranteed that there is no edge between the two nodes before adding this one.
int shortestPath(int node1, int node2) returns the minimum cost of a path from node1 to node2. If no path exists, return -1. The cost of a path is the sum of the costs of the edges in the path.
 

Example 1:


Input
["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
[[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]
Output
[null, 6, -1, null, 6]

Explanation
Graph g = new Graph(4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]);
g.shortestPath(3, 2); // return 6. The shortest path from 3 to 2 in the first diagram above is 3 -> 0 -> 1 -> 2 with a total cost of 3 + 2 + 1 = 6.
g.shortestPath(0, 3); // return -1. There is no path from 0 to 3.
g.addEdge([1, 3, 4]); // We add an edge from node 1 to node 3, and we get the second diagram above.
g.shortestPath(0, 3); // return 6. The shortest path from 0 to 3 now is 0 -> 1 -> 3 with a total cost of 2 + 4 = 6.
 

Constraints:

1 <= n <= 100
0 <= edges.length <= n * (n - 1)
edges[i].length == edge.length == 3
0 <= fromi, toi, from, to, node1, node2 <= n - 1
1 <= edgeCosti, edgeCost <= 106
There are no repeated edges and no self-loops in the graph at any point.
At most 100 calls will be made for addEdge.
At most 100 calls will be made for shortestPath.
Accepted
50,291
Submissions
70,249
 * @author jojo
 * Nov 11, 2023 7:57:46 PM
 */
public class GraphDesignWithShortedPathCalculator {
	class Graph {
	    Map<Integer, List<int[]>> graphMap = new HashMap<>();
	    
	    Integer[][] graph2D = null;
	    
	    public Graph(int n, int[][] edges) {
	        // below is for DJ
	        // for(int[] e : edges){
	        //     graphMap.computeIfAbsent(e[0], v -> new ArrayList<int[]>()).add(new int[]{e[1], e[2]});
	        // }
	        
	        // below is for floyed
	        graph2D = new Integer[n][n];
	        populateGraph2D(edges);
	    }
	    
	    private void populateGraph2D(int[][] edges){
	        int len = graph2D.length;
	        
	        for(int[] e : edges){
	            graph2D[e[0]][e[1]] = e[2];
	        }
	        
	        for(int i=0; i<len; i++){
	            graph2D[i][i] = 0;
	        }
	        
	        // intermediate nodes
	        for(int i=0; i<len; i++){
	            // source nodes
	            for(int j=0; j<len; j++){
	                // destination nodes
	                for(int k=0; k<len; k++){
	                    Integer curVal = graph2D[j][k];
	                    
	                    Integer nextVal = null;
	                    if(graph2D[j][i] == null || graph2D[i][k] == null){
	                        continue;
	                    }
	                    
	                    if(curVal == null){
	                        graph2D[j][k] = graph2D[j][i] + graph2D[i][k];
	                    }
	                    else{
	                        graph2D[j][k] = Math.min(graph2D[j][k], graph2D[j][i] + graph2D[i][k]);
	                    }
	                }
	            }
	        }
	    }
	    
	    public void addEdge(int[] edge) {
	        // below if for DJ
	        // graphMap.computeIfAbsent(edge[0], v -> new ArrayList<int[]>()).add(new int[]{edge[1], edge[2]});
	        
	        // below is for floyed
	        int len = graph2D.length;
	        // source node
	        for(int i=0; i<len; i++){
	            // destination node 
	            for(int j=0; j<len; j++){
	                Integer curVal = graph2D[i][j];

	                if(graph2D[i][edge[0]] == null || graph2D[edge[1]][j] == null){
	                    continue;
	                }

	                // checking the connectivity of the current edge for each combination and updating the min 
	                if(curVal == null){
	                    graph2D[i][j] = graph2D[i][edge[0]] + graph2D[edge[1]][j] + edge[2];
	                }
	                else{
	                    graph2D[i][j] = Math.min(graph2D[i][j], graph2D[i][edge[0]] + graph2D[edge[1]][j] + edge[2]);
	                }
	            }
	        }
	    }
	    
	    public int shortestPath(int node1, int node2) {
	        //return useDijkstrasAlgo(node1, node2);
	        return useFloyedAlgo(node1, node2);
	    }
	    
	    private int useFloyedAlgo(int node1, int node2){
	        if(graph2D[node1][node2] == null){
	            return -1;
	        }
	        
	        return graph2D[node1][node2];
	    }
	    
	    private int useDijkstrasAlgo(int node1, int node2){
	        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
	        pq.offer(new int[]{node1, 0});
	        
	        Map<Integer, Integer> costMap = new HashMap<>();
	        
	        while(!pq.isEmpty()){
	            int[] top = pq.poll();
	            int curNode = top[0], curCost = top[1];
	            
	            if(curNode == node2){
	                return curCost;
	            }
	            
	            costMap.put(curNode, curCost);
	            
	            for(int[] nei : graphMap.getOrDefault(curNode, new ArrayList<int[]>())){
	                int neiCost = curCost + nei[1];
	                
	                if(neiCost > costMap.getOrDefault(nei[0], Integer.MAX_VALUE)){
	                    continue;
	                }
	                
	                pq.offer(new int[]{nei[0], neiCost});
	            }
	        }
	        
	        return -1;
	    }
	}

	/**
	 * Your Graph object will be instantiated and called as such:
	 * Graph obj = new Graph(n, edges);
	 * obj.addEdge(edge);
	 * int param_2 = obj.shortestPath(node1,node2);
	 */
}
