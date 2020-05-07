package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * some server unable to reach some other server.

Return all critical connections in the network in any order.

 

Example 1:



Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
 

Constraints:

1 <= n <= 10^5
n-1 <= connections.length <= 10^5
connections[i][0] != connections[i][1]
There are no repeated connections.
 * @author jojo
 * May 7, 2020  12:32:11 AM
 */
public class CriticalConnectionsInANetwork {
	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] adjacent = new ArrayList[n];
        for(int i=0; i<n; i++) {
        	adjacent[i] = new ArrayList<Integer>();
        }
        
        for(List<Integer> connection : connections) {
        	adjacent[connection.get(0)].add(connection.get(1));
        	adjacent[connection.get(1)].add(connection.get(0));
        }
        
        List<List<Integer>> result = new ArrayList<>();
        int[] visited = new int[n];
        int[] lowRank = new int[n];
        
        dfs(adjacent, visited, lowRank, 0, -1, new int[] {0}, result);
        
        return result;
    }
	
	private int dfs(List<Integer>[] adjacent, int[] visited, int[] lowRank, int curNode, int parentNode, int[] counter, List<List<Integer>> result) {
		if(visited[curNode] == 1) {
			return lowRank[curNode];
		}
		
		visited[curNode] = 1;
		lowRank[curNode] = counter[0]++;
		int curVal = lowRank[curNode];
		
		for(int nei : adjacent[curNode]) {
			if(nei != parentNode) {
				int neiMin = dfs(adjacent, visited, lowRank, nei, curNode, counter, result);
				if(curVal < neiMin) {
					result.add(Arrays.asList(curNode, nei));
				}
				
				lowRank[curNode] = Math.min(lowRank[curNode], neiMin);
			}
		}
		
		return lowRank[curNode];
	}
}
