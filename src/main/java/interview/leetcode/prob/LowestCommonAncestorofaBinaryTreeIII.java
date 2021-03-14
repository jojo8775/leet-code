package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

Each node will have a reference to its parent node. The definition for Node is below:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."

 

Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q exist in the tree.
Accepted
10,109
Submissions
13,240
 * @author jojo
 * Mar 6, 2021  6:27:36 PM
 */
public class LowestCommonAncestorofaBinaryTreeIII {
	public Node lowestCommonAncestor(Node p, Node q) {
		Set<Node> visited = new HashSet<>();
		visited.add(p);

		while (p.parent != null) {
			visited.add(p.parent);
			p = p.parent;
		}

		if (visited.contains(q)) {
			return q;
		}

		while (q.parent != null) {
			if (visited.contains(q.parent)) {
				return q.parent;
			}

			q = q.parent;
		}

		return null;
	}
	
	public Node lowestCommonAncestor_adv(Node p, Node q) {
		Node a = p, b = q;
		
		while(a != b) {
			a = a == null ? q : a.parent;
			b = b == null ? p : b.parent;
		}
		
		return a;
	}

	private static class Node {
		Node parent;
	}
}
