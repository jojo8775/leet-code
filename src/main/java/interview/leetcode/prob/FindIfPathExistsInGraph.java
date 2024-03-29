package interview.leetcode.prob;

/**
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex start to vertex end.

Given edges and the integers n, start, and end, return true if there is a valid path from start to end, or false otherwise.

 

Example 1:


Input: n = 3, edges = [[0,1],[1,2],[2,0]], start = 0, end = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2
Example 2:


Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], start = 0, end = 5
Output: false
Explanation: There is no path from vertex 0 to vertex 5.
 

Constraints:

1 <= n <= 2 * 105
0 <= edges.length <= 2 * 105
edges[i].length == 2
0 <= ui, vi <= n - 1
ui != vi
0 <= start, end <= n - 1
There are no duplicate edges.
There are no self edges.
Accepted
38,113
Submissions
76,959
 * @author jojo
 * Dec 14, 2021 9:10:02 PM
 */
public class FindIfPathExistsInGraph {
    public boolean validPath(int n, int[][] edges, int start, int end) {
        int[] nodes = new int[n];
        
        for(int i=0; i<n; i++){
            nodes[i] = i;
        }
        
        for(int[] e : edges){
            int p1 = findParent(nodes, e[0]);
            int p2 = findParent(nodes, e[1]);
            
            if(p1 != p2){
                nodes[p1] = p2;
            }
        }
        
        int p1 = findParent(nodes, start);
        int p2 = findParent(nodes, end);
        
        return p1 == p2;
    }
    
    private int findParent(int[] arr, int n){
        while(arr[n] != n){
            arr[n] = arr[arr[n]];
            n = arr[n];
        }
        
        return n;
    }
}
