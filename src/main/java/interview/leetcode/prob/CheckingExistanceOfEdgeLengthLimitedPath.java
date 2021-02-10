package interview.leetcode.prob;

import java.util.Arrays;

/**
 * An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.

Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j] whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .

Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.

 

Example 1:


Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
Output: [false,true]
Explanation: The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
For the second query, there is a path (0 -> 1 -> 2) of two edges with distances less than 5, thus we return true for this query.
Example 2:


Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
Output: [true,false]
Exaplanation: The above figure shows the given graph.
 

Constraints:

2 <= n <= 105
1 <= edgeList.length, queries.length <= 105
edgeList[i].length == 3
queries[j].length == 3
0 <= ui, vi, pj, qj <= n - 1
ui != vi
pj != qj
1 <= disi, limitj <= 109
There may be multiple edges between two nodes.
Accepted
4,662
Submissions
8,374
 * @author jojo
 * Feb 10, 2021  1:09:27 AM
 */
public class CheckingExistanceOfEdgeLengthLimitedPath {
	public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int ql = queries.length, el = edgeList.length;
        
        int[][] qArr = new int[ql][4];
        for(int i=0; i<ql; i++){
            qArr[i] = new int[]{queries[i][0], queries[i][1], queries[i][2], i}; // this is done to preserve the order of result.
        }
        
        // sorting query entries by the limit in asc order.
        Arrays.sort(qArr, (a, b) -> a[2] - b[2]); 
        
        // sorting edge entries by the distance in asc order.
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]); 
        
        boolean[] result = new boolean[ql];
        
        int[] parentArr = new int[n];
        for(int i=0; i<n; i++){
            parentArr[i] = i;
        }
        
        // 'j' is initialized here, because we can re-use the computed find-union of edges for the previous queries.
        for(int i=0, j=0; i<ql; i++){
            int[] query = qArr[i];
            
            while(j < el && edgeList[j][2] < query[2]){
                int p1 = findParent(parentArr, edgeList[j][0]);
                int p2 = findParent(parentArr, edgeList[j][1]);
                j++;
                
                if(p1 != p2){
                    parentArr[p1] = p2;
                }
            }
            
            // this is where the extra addition to queries comes to use. 
            if(findParent(parentArr, query[0]) == findParent(parentArr, query[1])){
                result[query[3]] = true;
            }
            else{
                result[query[3]] = false;
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
