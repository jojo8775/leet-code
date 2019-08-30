package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.

Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.

Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).

 

Example 1:

Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
Output: [0,1,-1]
Example 2:

Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
Output: [0,1,-1]
Example 3:

Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
Output: [0,-1,-1]
Example 4:

Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
Output: [0,1,2]
Example 5:

Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
Output: [0,1,1]
 

Constraints:

1 <= n <= 100
red_edges.length <= 400
blue_edges.length <= 400
red_edges[i].length == blue_edges[i].length == 2
0 <= red_edges[i][j], blue_edges[i][j] < n
Accepted
4,555
Submissions
12,620
 * @author jojo
 * Aug 30, 2019 1:12:32 AM
 */
public class ShortestPathWithAlternateColor {
	public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        List<Integer>[] reds = new ArrayList[n], blues = new ArrayList[n];
        
        // creating the graph or adjacent matrix for red edges
        for(int[] e : red_edges){
            if(reds[e[0]] == null){
                reds[e[0]] = new ArrayList<Integer>();
            }
            
            reds[e[0]].add(e[1]);
        }
        
        // creating the graph or adjacent matrix for blue edges
        for(int[] e : blue_edges){
            if(blues[e[0]] == null){
                blues[e[0]] = new ArrayList<Integer>();
            }
            
            blues[e[0]].add(e[1]);
        }
        
        Queue<int[]> queue = new LinkedList<>();
        // states = {0, 1, 2} where 0 = root, 1 = red, 2 = blue
        queue.offer(new int[]{0,0}); // 1st = index, 2nd = state
        
        Set<String> seen = new HashSet<>();
        
        int moves = 0;
        int[] res = new int[n];
        Arrays.fill(res, -1);   // making all nodes unreachable by default
        
        while(!queue.isEmpty()){
            int len = queue.size();
            
            for(int i=0; i<len; i++){
                int[] top = queue.poll();
                
                // if already seen then continue
                if(!seen.add(top[0] + "-" + top[1])){
                    continue;
                }
                
                if(res[top[0]] == -1){
                    res[top[0]] = moves;
                }
                
                // if state is root or red, explore all blues
                if(top[1] == 0 || top[1] == 1){
                    if(blues[top[0]] != null){
                        for(int e : blues[top[0]]){
                            queue.offer(new int[]{e, 2});
                        }
                    }
                }
                
                // if state is root or blue, explore all reds
                if(top[1] == 0 || top[1] == 2){
                    if(reds[top[0]] != null){
                        for(int e : reds[top[0]]){
                            queue.offer(new int[]{e, 1});
                        }
                    }
                }
            }
            
            moves++;
        }
        
        return res;
    }
}
