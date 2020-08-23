package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.

The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  Each node has labels in the set {0, 1, ..., edges.length}.

 

Example 1:



Input: edges = [[0,1],[0,2]]
Output: 2
Explanation: 
A longest path of the tree is the path 1 - 0 - 2.
Example 2:



Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
Output: 4
Explanation: 
A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
 

Constraints:

0 <= edges.length < 10^4
edges[i][0] != edges[i][1]
0 <= edges[i][j] <= edges.length
The given edges form an undirected tree.
Accepted
12,562
Submissions
20,882
 * @author jojo
 * Aug 22, 2020  9:00:15 PM
 */
public class TreeDiameter {
	public int treeDiameter(int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        
        for(int i=0; i<=edges.length; i++){
            graph.add(new ArrayList<Integer>());
        }
        
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        int[] maxDiameter = new int[]{0};
        depth(graph, -1, 0, maxDiameter);
        
        return maxDiameter[0];
    }
    
    private int depth(List<List<Integer>> graph, int parentNode, int currentNode, int[] maxDiameter){
        int leftMax = 0, rightMax = 0;
        
        List<Integer> nodes = graph.get(currentNode);
        
        for(int node : nodes){
            if(node == parentNode){
                continue;
            }
            
            int childDepth = depth(graph, currentNode, node, maxDiameter);
            if(childDepth > leftMax){
                rightMax = leftMax;
                leftMax = childDepth;
            }
            else if(childDepth > rightMax){
                rightMax = childDepth;
            }
        }
        
        maxDiameter[0] = Math.max(maxDiameter[0], leftMax + rightMax); // diameter is left child max path + right child max path
        
        return leftMax + 1;
    }
}
