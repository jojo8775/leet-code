package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, we have endi-1 == starti.

Return any valid arrangement of pairs.

Note: The inputs will be generated such that there exists a valid arrangement of pairs.

 

Example 1:

Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
Output: [[11,9],[9,4],[4,5],[5,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 9 == 9 = start1 
end1 = 4 == 4 = start2
end2 = 5 == 5 = start3
Example 2:

Input: pairs = [[1,3],[3,2],[2,1]]
Output: [[1,3],[3,2],[2,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 3 == 3 = start1
end1 = 2 == 2 = start2
The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.
Example 3:

Input: pairs = [[1,2],[1,3],[2,1]]
Output: [[1,2],[2,1],[1,3]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 2 == 2 = start1
end1 = 1 == 1 = start2
 

Constraints:

1 <= pairs.length <= 105
pairs[i].length == 2
0 <= starti, endi <= 109
starti != endi
No two pairs are exactly the same.
There exists a valid arrangement of pairs.
Accepted
18,351
Submissions
35,899

 * Nov 29, 2024 - 8:55:59 PM
 * Jojo
 */
public class ValidArrangementOfPairs {
	Map<Integer, List<Integer>> adj = new HashMap<>();
	List<List<Integer>> ans = new ArrayList<>();

	public int[][] validArrangement_rec(int[][] pairs) {
		Map<Integer, Integer> indegree = new HashMap<>();
		for (int[] pair : pairs) {
			adj.putIfAbsent(pair[0], new ArrayList<>());
			adj.get(pair[0]).add(pair[1]);
			indegree.put(pair[0], indegree.getOrDefault(pair[0], 0) + 1);
			indegree.put(pair[1], indegree.getOrDefault(pair[1], 0) - 1);

		}

		int start = -1;
		for (Integer key : indegree.keySet()) {
			if (indegree.get(key) > 0) {
				start = key;
			}
		}

		if (start == -1) {
			start = pairs[0][0];
		}

		dfs(start);

		int[][] result = new int[pairs.length][pairs[0].length];

		for (int i = 0; i < ans.size(); i++) {
			result[i][0] = ans.get(ans.size() - i - 1).get(0);
			result[i][1] = ans.get(ans.size() - i - 1).get(1);
		}
		return result;
	}

	private void dfs(int node) {
		while (adj.get(node) != null && adj.get(node).size() > 0) {
			List<Integer> nodes = adj.get(node);
			int next = nodes.get(nodes.size() - 1);
			adj.get(node).remove(nodes.size() - 1);
			dfs(next);
			ans.add(Arrays.asList(node, next));
		}
	}
	
	public int[][] validArrangement(int[][] pairs) {
        Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
        
        Map<Integer, Integer> indegree = new HashMap<>();
        
        for(int[] p : pairs){
            graph.computeIfAbsent(p[0], v -> new LinkedList<>()).offer(p[1]);
            
            // need this to identify the start node. The entry with 1 will be the start node.
            indegree.put(p[0], indegree.getOrDefault(p[0], 0) + 1);
            indegree.put(p[1], indegree.getOrDefault(p[1], 0) - 1);
        }
        
        int start = -1;
        
        for(int key : indegree.keySet()){
            if(indegree.get(key) == 1){
                start = key;
                break;
            }
        }
        
        // start will be -1 when the pairs form a circule.
        if(start == -1){
            start = pairs[0][0];
        }
        
        int[][] result = new int[pairs.length][2];
        int idx = result.length - 1;
        
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        
        while(!stack.isEmpty()){
            while(graph.containsKey(stack.peek()) && !graph.get(stack.peek()).isEmpty()){
                stack.push(graph.get(stack.peek()).poll());
            }
            
            int top = stack.pop();
            
            if(!stack.isEmpty()){
                result[idx--] = new int[]{stack.peek(), top};
            }
        }
        
        return result;
    }
}
