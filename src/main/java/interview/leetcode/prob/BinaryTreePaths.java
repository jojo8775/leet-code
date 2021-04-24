package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
 * @author jojo
 *
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        
        find(root, new StringBuilder(), result);
        
        return result;
    }
    
    private void find(TreeNode node, StringBuilder sb, List<String> result){
        if(node == null){
            return;
        }
        
        int len = sb.length();
        sb.append(node.val);
        if(node.left == null && node.right == null){
            result.add(sb.toString());
        }
        
        sb.append("->");
        
        find(node.left, sb, result);
        find(node.right, sb, result);
        
        sb.setLength(len);
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
