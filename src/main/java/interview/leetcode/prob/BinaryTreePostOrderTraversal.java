package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/*-
 * Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?


 */
public class BinaryTreePostOrderTraversal {
	public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null){
            return new ArrayList<Integer>();
        }   
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode lastVisitedNode = null;
        
        List<Integer> result = new ArrayList<Integer>();
        
        while(!stack.isEmpty() || root != null){
            if(root != null){
                stack.push(root);
                root = root.left;
            }
            else{
                TreeNode peekNode = stack.peek();
                
                if(peekNode.right != null && !peekNode.right.equals(lastVisitedNode)){
                    root = peekNode.right;
                }
                else{
                    lastVisitedNode = stack.pop();
                    result.add(lastVisitedNode.val);
                }
            }
        }
        
        return result;
//
//		Stack<TreeNode> stack = new Stack<TreeNode>();
//		stack.push(root);
//		Set<TreeNode> visited = new HashSet<TreeNode>();
//		visited.add(null);
//
//		while (!stack.isEmpty()) {
//			TreeNode node = stack.peek();
//			while (node != null) {
//				if (!visited.contains(node.right)) {
//					stack.push(node.right);
//				}
//
//				if (!visited.contains(node.left)) {
//					stack.push(node.left);
//				}
//
//				node = node.left;
//			}
//
//			node = stack.peek();
//			if (visited.contains(node.left) && visited.contains(node.right)) {
//				visited.add(node);
//				result.add(node.val);
//				stack.pop();
//			}
//		}
//
//		return result;
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
