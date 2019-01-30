package interview.leetcode.prob;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
 

Note:

graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].

 * @author jojo
 * Jan 16, 2019 12:17:02 AM
 */
public class IsGraphBipartite {
	// the idea it to start with 1 and make all the neighbors a ^1 till the end. If there is a conflict then it cannot 
	// separated into two sets of nodes.
	public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        int[] colors = new int[len];
        // -1 represents no color.
        Arrays.fill(colors, -1);
        
        for(int i=0; i<len; i++){
            if(colors[i] == -1){
                colors[i] = 0; // 0 represents red and ^0 represents blue
                
                Stack<Integer> stack = new Stack<>();
                stack.push(i);
                
                while(!stack.isEmpty()){
                    int top = stack.pop();
                    
                    // for each neighbors we need to flip color if it is uncolored.
                    for(int nei : graph[top]){
                        if(colors[nei] == -1){
                            colors[nei] = colors[top] ^ 1;
                            stack.push(nei);
                        }
                        // if there is a conflict return false
                        else if(colors[nei] == colors[top]){
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
    }
}
