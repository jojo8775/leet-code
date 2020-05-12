package interview.leetcode.interview.amazon;

import java.util.Stack;

/**
 * Given a matrix with r rows and c columns, find the maximum score of a path starting at [0, 0] and ending at [r-1, c-1]. The score of a path is the minimum value in that path. For example, the score of the path 8 → 4 → 5 → 9 is 4.

Don't include the first or final entry. You can only move either down or right at any point in time.

Example 1:

Input:
[[5, 1],
 [4, 5]]

Output: 4
Explanation:
Possible paths:
5 → 1 → 5 => min value is 1
5 → 4 → 5 => min value is 4
Return the max value among minimum values => max(4, 1) = 4.
Example 2:

Input:
[[1, 2, 3]
 [4, 5, 1]]

Output: 4
Explanation:
Possible paths:
1-> 2 -> 3 -> 1
1-> 2 -> 5 -> 1
1-> 4 -> 5 -> 1
So min of all the paths = [2, 2, 4]. Note that we don't include the first and final entry.
Return the max of that, so 4.
 * @author jojo
 * May 11, 2020  11:18:42 PM
 */
public class MaxOfMinAltitudes {
	public int findMinMax(int[][] grid){
	    Stack<Node> stack = new Stack<>();
	    stack.push(new Node(grid[0][0], null, 0, 0));

	    int m = grid.length - 1, n = grid[0].length - 1;
	    int result = Integer.MIN_VALUE;

	    while(!stack.isEmpty()){
	        Node top = stack.pop();
	        if(top.i == m && top.j == n){
	            int curMin = findMin(top);
	            result = Math.max(result, curMin);
	        }
	        else{
	            if(top.i + 1 <= m){
	                stack.push(new Node(grid[top.i + 1][top.j], top, top.i+1, top.j));
	            }

	            if(top.j + 1 <= n){
	                stack.push(new Node(grid[top.i][top.j + 1], top, top.i, top.j + 1));
	            }
	        }
	    }

	    return result;
	}

	private int findMin(Node node){
	    node = node.prev;
	    int val = Integer.MAX_VALUE;

	    while(node.prev != null){
	        val = Math.min(val, node.val);
	        node = node.prev;
	    }

	    return val;
	}

	private static class Node{
	    int val, i, j;
	    Node prev;
	    public Node(int val, Node prev, int i, int j){
	        this.prev = prev;
	        this.val = val;
	        this.i = i;
	        this.j = j;
	    }
	}
	
	public static void main(String[] args) {
		//int[][] grid = {{1,2,3}, {4,5,1}};
		int[][] grid = { { 5, 7, 6, 8 }, { 3, 4, 2, 1 }, { 9, 8, 4, 6 } };
		int result = new MaxOfMinAltitudes().findMinMax(grid);
		System.out.println(result);
	}
}
