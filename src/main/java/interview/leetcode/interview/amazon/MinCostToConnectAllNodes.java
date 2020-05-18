package interview.leetcode.interview.amazon;

import java.util.Arrays;
import java.util.Stack;

public class MinCostToConnectAllNodes {
	public int findMinCost(int nodes, int[][] edges, int[][] newEdges) {
		int[] parent = new int[nodes + 1];

		for(int i=0; i<=nodes; i++) {
			parent[i] = i;
		}
		
		int components = nodes;
		for(int[] edge : edges) {
			if(merge(parent, edge[0], edge[1])) {
				components--;
			}
		}
		
		int minCost = 0;
		Arrays.sort(newEdges, (a,b) -> a[2] - b[2]);
		
		for(int i=0; i<newEdges.length && components > 1; i++) {
			if(merge(parent, newEdges[i][0], newEdges[i][1])) {
				components--;
				minCost += newEdges[i][2];
			}
		}
		
		return components == 1 ? minCost : -1;
	}
	
	private int findParent(int[] parent, int n) {
		int cur = n;
		
		Stack<Integer> stack = new Stack<>();
		while(parent[cur] != cur) {
			stack.push(cur);
			cur = parent[cur];
		}
		
		while(!stack.isEmpty()) {
			parent[stack.pop()] = cur;
		}
		
		return cur;
	}
	
	private boolean merge(int[] parent, int n1, int n2) {
		int r1 = findParent(parent, n1);
		int r2 = findParent(parent, n2);
		
		if(r1 == r2) {
			return false;
		}
		
		parent[r1] = r2;
		return true;
	}
	
	public static void main(String[] args) {
		int result = new MinCostToConnectAllNodes().findMinCost(
				6, 
				new int[][] {{1,4}, {4,5}, {2,3}}, 
				new int[][] {{1,2,5}, {1,3,10}, {1,6,2}, {5,6,5}});
		
		result = new MinCostToConnectAllNodes().findMinCost(
				6, 
				new int[][] {{1,2}, {2,3}, {4,5}, {3,5}, {1,6}, {2,4}}, 
				new int[][] {{1,6,410}, {2,4,800}});
		
		System.out.println(result);
	}
}
