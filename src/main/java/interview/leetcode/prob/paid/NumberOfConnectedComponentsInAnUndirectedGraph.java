package interview.leetcode.prob.paid;

public class NumberOfConnectedComponentsInAnUndirectedGraph {
	public int countComponents(int n, int[][] edges){
		int count = n;
		
		int[] root = new int[n];
		for(int i=0; i<n; i++){
			root[i] = i;
		}
		
		for(int[] edge : edges){
			int xRoot = getRoot(root, edge[0]);
			int yRoot = getRoot(root, edge[1]);
			
			if(xRoot != yRoot){
				count --;
				root[xRoot] = root[yRoot];
			}
		}
		
		return count;
	}
	
	private int getRoot(int[] root, int i){
		while(root[i] != i){
			root[i] = root[root[i]];
			i = root[i];
		}
		
		return i;
	}
	
	public static void main(String[] args){
		NumberOfConnectedComponentsInAnUndirectedGraph prob = new NumberOfConnectedComponentsInAnUndirectedGraph();
		
		int result = prob.countComponents(6, new int[][] {{0,1}, {5,4}, {2,3}, {1,2}, {3,4}, {4,0}});
		System.out.println(result);
	}
}
