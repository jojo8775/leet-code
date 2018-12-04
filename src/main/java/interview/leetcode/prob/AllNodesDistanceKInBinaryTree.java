package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.



Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
 

Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.

 * @author jojo
 * Dec 3, 2018 9:16:27 PM
 */
public class AllNodesDistanceKInBinaryTree {
	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
		List<Integer> result = new ArrayList<>();
		if (root == null || target == null) {
			return result;
		}

		// creating a parent and child relation
		Map<TreeNode, TreeNode> nodeParentMap = new HashMap<>();
		buildMap(root, null, nodeParentMap);

		if (!nodeParentMap.containsKey(target)) {
			return result;
		}

		// used for BFS
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(target);

		// used to prevent revisiting child nodes from parent nodes.
		Set<TreeNode> visited = new HashSet<>();
		visited.add(target);

		while (!queue.isEmpty()) {
			if (K == 0) {
				while (!queue.isEmpty()) {
					result.add(queue.poll().val);
				}
			}

			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode top = queue.poll();
				if (top.left != null && visited.add(top.left)) {
					queue.offer(top.left);
				}
				if (top.right != null && visited.add(top.right)) {
					queue.offer(top.right);
				}

				TreeNode parent = nodeParentMap.get(top);
				if (parent != null && visited.add(parent)) {
					queue.offer(parent);
				}
			}

			K--;
		}

		return result;
	}

	private void buildMap(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> nodeMap) {
		if (node == null) {
			return;
		}

		nodeMap.put(node, parent);
		buildMap(node.left, node, nodeMap);
		buildMap(node.right, node, nodeMap);
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
