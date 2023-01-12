package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.

The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.

 

Example 1:


Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
Output: 8 
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  
Example 2:


Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
Output: 6
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  
Example 3:

Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
Output: 0
 

Constraints:

1 <= n <= 105
edges.length == n - 1
edges[i].length == 2
0 <= ai < bi <= n - 1
fromi < toi
hasApple.length == n
Accepted
40,319
Submissions
70,173
 * @author jojo
 * Jan 10, 2023 9:08:17 PM
 */
public class MinimumTimeToCollectAllApplesInATree {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<List<Integer>> graph = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            graph.add(new ArrayList<Integer>());
        }
        
        for(int[] e : edges){
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        
        boolean[] visited = new boolean[n];
        visited[0] = true;
        
        int val = findCost(visited, graph, 0, hasApple);
        return val > 0 ? val - 1 : 0;
    }
    
    private int findCost(boolean[] visited, List<List<Integer>> graph, int idx, List<Boolean> hasApple){
        int val = 0;
        
        for(int child : graph.get(idx)){
            //System.out.println("a:" + idx + " - " + "b:" + child);
            if(!visited[child]){
                visited[child] = true;
                int val2 = findCost(visited, graph, child, hasApple);
                if(val2 > 0){
                    val += (val2 + 1);
                    //System.out.println("idx:" + idx + "  child:" + child + "  val2:" + val2 + "  val:" + val);
                }
            }
        }
        
        if(hasApple.get(idx) || val > 0){
            //System.out.println("idx:" + idx + "  hasApple   val:" + (val + 1));
            return val + 1;
        }
        
        return 0;
    }
}
