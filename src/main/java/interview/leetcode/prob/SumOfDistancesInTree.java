package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.

You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.

 

Example 1:


Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation: The tree is shown above.
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.
Hence, answer[0] = 8, and so on.
Example 2:


Input: n = 1, edges = []
Output: [0]
Example 3:


Input: n = 2, edges = [[1,0]]
Output: [1,1]
 

Constraints:

1 <= n <= 3 * 104
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
ai != bi
The given input represents a valid tree.
Accepted
52,634
Submissions
95,171
 * @author jojo
 * Dec 21, 2022 11:43:05 PM
 */
public class SumOfDistancesInTree {
	/**
	 *   # First DFS
         [TREE] |      [COUNT]     [RET]
            0   |       10          [ ] = (1+0) + (7+10) +(1+0)  = 19      
          / | \ |      / | \       / | \ 
         1  2  3|     1  7  1     0  10 0       <--- ret[root] = sum(count[child])+sum(count[child])
           /|\  |       /|\         /|\                        sum(count[child]) = travel again 「count[child]」 many times of path root->child 
          4 5 6 |      4 1 1       4 0 0                       sum(count[child]) = prev traveled paths sum
         /|     |     /|          /|   
        7 8     |    1 2         0 1    
         /      |     /           /         
        9       |    1           0           
        
   # Second DFS           
         [RET]  |                  |                  
            19  |           19     |       19             
          / | \ |          / | \   |      / | \ 
         0  10 0| [19-1+10-1] 10 0 |  28 [19-7+10-7] 0       <---  = parent.ret - root.count 
           /|\  |           /|\    |       /|\                      + (N - root.count)*1 
          4 0 0 |          4 0 0   |      4 0 0                       Eveny node other than it's subtree node: become 1 step more far away
         /|     |         /|       |     /|   
        0 1     |        0 1       |    0 1    
         /      |         /        |     /         
        0       |        0         |    0         
        
    # Ans = [19,27,15,27,17,23,23,25,23,31] 
    --------------Example--------------------------------------------    
        
        Count         Ret         Dfs update ret...
          6            8            8              8     
         / \          / \          /  \           / \
        1   4        0   3    [8-1+N-1] 3       12   6
           /|\          /|\          /|\            /|\
          1 1 1        0 0 0        0 0 0        10 10 [6-1+N-1]   N=6
	 * @param N
	 * @param edges
	 * @return
	 */
	public int[] sumOfDistancesInTree(int N, int[][] edges) {
		List<Set<Integer>> adj = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			adj.add(new HashSet<Integer>());
		}
		
		for(int[] e : edges) {
			adj.get(e[0]).add(e[1]);
			adj.get(e[1]).add(e[0]);
		}
		
		int[] nodeCount = new int[N];
		int[] nodeDist = new int[N];
		
		postOrder(adj, nodeCount, nodeDist, 0, -1);
		preOrder(adj, nodeCount, nodeDist, 0, -1);
		
		return nodeDist;
	}
	
	private void postOrder(List<Set<Integer>> adj, int[] nodeCount, int[] nodeDist, int curNode, int prevNode) {
		for(int childNode : adj.get(curNode)) {
			if(childNode == prevNode) {
				continue;
			}
			
			postOrder(adj, nodeCount, nodeDist, childNode, curNode);
			
			nodeCount[curNode] += nodeCount[childNode];
			nodeDist[curNode] += nodeCount[childNode] + nodeDist[childNode];
		}
		
		nodeCount[curNode]++;
	}
	
	private void preOrder(List<Set<Integer>> adj, int[] nodeCount, int[] nodeDist, int curNode, int prevNode) {
		for(int childNode : adj.get(curNode)) {
			if(childNode == prevNode) {
				continue;
			}
			
			nodeDist[childNode] = nodeDist[curNode] - nodeCount[childNode] + nodeCount.length - nodeCount[childNode];
			preOrder(adj, nodeCount, nodeDist, childNode, curNode);
		}
	}
}
