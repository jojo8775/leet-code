package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is a tree (i.e., a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. Each node has a value associated with it, and the root of the tree is node 0.

To represent this tree, you are given an integer array nums and a 2D array edges. Each nums[i] represents the ith node's value, and each edges[j] = [uj, vj] represents an edge between nodes uj and vj in the tree.

Two values x and y are coprime if gcd(x, y) == 1 where gcd(x, y) is the greatest common divisor of x and y.

An ancestor of a node i is any other node on the shortest path from node i to the root. A node is not considered an ancestor of itself.

Return an array ans of size n, where ans[i] is the closest ancestor to node i such that nums[i] and nums[ans[i]] are coprime, or -1 if there is no such ancestor.

 

Example 1:



Input: nums = [2,3,3,2], edges = [[0,1],[1,2],[1,3]]
Output: [-1,0,0,1]
Explanation: In the above figure, each node's value is in parentheses.
- Node 0 has no coprime ancestors.
- Node 1 has only one ancestor, node 0. Their values are coprime (gcd(2,3) == 1).
- Node 2 has two ancestors, nodes 1 and 0. Node 1's value is not coprime (gcd(3,3) == 3), but node 0's
  value is (gcd(2,3) == 1), so node 0 is the closest valid ancestor.
- Node 3 has two ancestors, nodes 1 and 0. It is coprime with node 1 (gcd(3,2) == 1), so node 1 is its
  closest valid ancestor.
Example 2:



Input: nums = [5,6,10,2,3,6,15], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
Output: [-1,0,-1,0,0,0,-1]
 

Constraints:

nums.length == n
1 <= nums[i] <= 50
1 <= n <= 105
edges.length == n - 1
edges[j].length == 2
0 <= uj, vj < n
uj != vj
Accepted
4,862
Submissions
12,843
 * @author jojo
 * Dec 29, 2021 10:59:40 AM
 */
public class TreesOfCoprime {
    public int[] getCoprimes(int[] nums, int[][] edges) {
		// since there are 50 possible entries.
		boolean[][] coPrimes = new boolean[51][51];
		for (int i = 1; i < 51; i++) {
			for (int j = 1; j < 51; j++) {
				if (gcd(i, j) == 1) {
					coPrimes[i][j] = true;
					coPrimes[j][i] = true;
				}
			}
		}
		
		int len = nums.length;
		
		// creating the graph
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int edge[] : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}

		int[] result = new int[len];
		Arrays.fill(result, -1);
		
		// since the root has no ancestors.
		result[0] = -1;
        
        // key: node weight val: [0] node depth, [1] node index 
		// this map contains the latest / deepest node representing a given weight. 
		Map<Integer, int[]> parentMap = new HashMap<>();

		boolean[] visited = new boolean[len];
		
		dfs(nums, graph, 0, 0, visited, result, parentMap, coPrimes);
		
		return result;
	}
	
	public void dfs(int[] nums, List<List<Integer>> tree, int depth, int idx, boolean[] visited, int[] result, Map<Integer, int[]> parentMap, boolean[][] coPrimes) {
		if (visited[idx]) {
			return;
		}
		
		visited[idx] = true;
		int curAncestor = -1;
		int curDepth = Integer.MAX_VALUE;
		
		for (int i = 1; i < 51; i++) {
			// getting the most recent entry from the parent map which represents the given weight from coPrime. (coPrimes[nums[idx]][i])  
			if (coPrimes[nums[idx]][i] && parentMap.containsKey(i)) {
				if (depth - parentMap.get(i)[0] <= curDepth) {
					curDepth = depth - parentMap.get(i)[0];
					curAncestor = parentMap.get(i)[1];
				}
			}
		}
		
		result[idx] = curAncestor;
		
		// if there was a higher representation of the given weight (coPrimes[nums[idx]][i]) then we need to preserve it for other DFS path. 
		int[] previousParent = (parentMap.containsKey(nums[idx])) ? parentMap.get(nums[idx]) : new int[] { -1, -1 };
		
		// putting the most recent computation of the parent map.
		parentMap.put(nums[idx], new int[] { depth, idx });
		
		for (int child : tree.get(idx)) {
			if (visited[child]) {
				continue;
			}
			
			dfs(nums, tree, depth + 1, child, visited, result, parentMap, coPrimes);
		}
		
		// if there was a previous number present then need to put it back otherwise remove the current entry. 
		// NOTE: the key of this map is the node weight which will be common across many nodes. For this reason
		// this logic is needed.
		if (previousParent[0] != -1) {
			parentMap.put(nums[idx], previousParent);
		}
		else {
			parentMap.remove(nums[idx]);
		}

		return;
	}
	
	public int gcd(int n1, int n2) {
		if (n2 == 0) {
			return n1;
		}
		return gcd(n2, n1 % n2);
	} 
}
