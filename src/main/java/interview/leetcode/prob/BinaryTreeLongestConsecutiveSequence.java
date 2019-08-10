package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

Example 1:

Input:

   1
    \
     3
    / \
   2   4
        \
         5

Output: 3

Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
Example 2:

Input:

   2
    \
     3
    / 
   2    
  / 
 1

Output: 2 

Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 * @author jojo
 * Jun 4, 2019 9:16:44 PM
 */
public class BinaryTreeLongestConsecutiveSequence {
    public int longestConsecutive(TreeNode root) {
		if(root == null) {
			return 0;
		}
		
		Queue<Pair> queue = new LinkedList<>();
		queue.offer(new Pair(root, 1));
		
		int max = 1;
		while(!queue.isEmpty()) {
			Pair top = queue.poll();
			
			if(top.node.left != null) {
				int val = top.node.val + 1 == top.node.left.val ? top.val + 1 : 1;
				queue.offer(new Pair(top.node.left, val));
				max = Math.max(max, val);
			}
			
			if(top.node.right != null) {
				int val = top.node.val + 1 == top.node.right.val ? top.val + 1 : 1;
				queue.offer(new Pair(top.node.right, val));
				max = Math.max(max, val);
			}
		}
		
		return max;
	}
	
	private static class Pair {
		TreeNode node;
		int val;
		
		public Pair(TreeNode node, int val) {
			this.node = node;
			this.val = val;
		}
	}
	
	private static class TreeNode{
		int val;
		TreeNode left, right;
	}
}
