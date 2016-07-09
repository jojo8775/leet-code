package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.


 * @author jojo
 *
 */
public class SortedListToBST {
	public TreeNode sortedListToBST(ListNode head) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();

		while (head != null) {
			treeNodes.add(new TreeNode(head.val));
			head = head.next;
		}

		return getRoot(treeNodes, 0, treeNodes.size() - 1);
	}

	private TreeNode getRoot(List<TreeNode> nodes, int beg, int end) {
		if (beg > end) {
			return null;
		}

		if (beg == end) {
			return nodes.get(beg);
		}

		int mid = (beg + end) / 2;
		TreeNode root = nodes.get(mid);
		root.left = getRoot(nodes, beg, mid - 1);
		root.right = getRoot(nodes, mid + 1, end);

		return root;
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
