package interview.leetcode.prob;

/**
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a directed edge connecting nodes ui and vi, where ui is a parent of child vi.

Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

 

Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
Output: [4,1]
 

Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ui, vi <= n
 * @author jojo
 * Jan 13, 2021  10:53:40 PM
 */
public class RedundantConnectionII {
	public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] arr = new int[edges.length + 1];
        
        for(int i=0; i<arr.length; i++){
            arr[i] = i;
        }
        
        int[] lastEdgeWithMoreThan1Parent = null, cycleEdge = null;
        
        for(int[] e : edges){
            int p1 = findParent(arr, e[0]), p2 = findParent(arr, e[1]);
            
            if(p2 != e[1]){
                lastEdgeWithMoreThan1Parent = e;
            }
            else if (p1 == p2){
                cycleEdge = e;
            }
            else{
                arr[p2] = p1;
            }
        }
        
        if(lastEdgeWithMoreThan1Parent == null){
            return cycleEdge;
        }
        
        if(cycleEdge == null){
            return lastEdgeWithMoreThan1Parent;
        }
        
        // if both are present then need to return which caused a node to have more than 1 parent.
        int[] result = null;
        for(int[] e : edges){
            if(e[1] == lastEdgeWithMoreThan1Parent[1]){
                result = e;
                break;
            }
        }
        
        return result;
    }
    
    private int findParent(int[] arr, int idx){
        while(arr[idx] != idx){
            arr[idx] = arr[arr[idx]];
            idx = arr[idx];
        }
        
        return idx;
    }
}
