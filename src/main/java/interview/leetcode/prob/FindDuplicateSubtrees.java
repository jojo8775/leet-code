package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1: 
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:
      2
     /
    4
and
    4
Therefore, you need to return above trees' root in the form of a list.

 * @author jojo
 *
 */
public class FindDuplicateSubtrees {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        postOrder(new HashMap<String, Integer>(), result, root);
        return result;
    }
    
    private String postOrder(Map<String, Integer> pathIndex, List<TreeNode> result, TreeNode node){
        if(node == null){
            return "#,";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(postOrder(pathIndex, result, node.left)).append(",");
        sb.append(postOrder(pathIndex, result, node.right)).append(",");
        sb.append(node.val);
        
        String path = sb.toString();
        
        int prevCount = pathIndex.getOrDefault(path, 0);
        pathIndex.put(path, prevCount + 1);
        
        if(prevCount + 1 == 2){
            result.add(node);
        }
        
        return path;
    }
    
    private static class TreeNode{
        int val;
        TreeNode left, right;
        
        public TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
