package interview.leetcode.interview.amazon;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Is the two given tree a part of subtree.
 * @author jojo
 * May 4, 2020  11:22:40 PM
 */
public class SubtreeOfAnotherTree {

	public boolean subTree(TreeNode n1, TreeNode n2) {
		if(n1 == null || n2 == null) {
			return false;
		}
		
		Queue<TreeNode> q1 = new LinkedList<>();
		q1.offer(n1);
		
		while(!q1.isEmpty()) {
			TreeNode top = q1.poll();
			if(top == n2) {
				if(isSubtree(top, n2)) {
					return true;
				}
			}
			
			if(top.left != null) {
				q1.offer(top.left);
			}
			
			if(top.right != null) {
				q1.offer(top.right);
			}
		}
		
		return false;
	}
	
	private boolean isSubtree(TreeNode n1, TreeNode n2) {
		if(n1 != n2) {
			return false;
		}
		
		if(n1 == null) {
			return true;
		}
		
		return isSubtree(n1.left, n2.left) && isSubtree(n1.right, n2.right);
	}
	
	private static class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
		}
	}
}
