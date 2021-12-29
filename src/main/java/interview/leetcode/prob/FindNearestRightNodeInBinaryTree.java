package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary tree and a node u in the tree, return the nearest node on the same level that is to the right of u, or return null if u is the rightmost node in its level.

 

Example 1:


Input: root = [1,2,3,null,4,5,6], u = 4
Output: 5
Explanation: The nearest node on the same level to the right of node 4 is node 5.
Example 2:


Input: root = [3,null,4,2], u = 2
Output: null
Explanation: There are no nodes to the right of 2.
 

Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 105
All values in the tree are distinct.
u is a node in the binary tree rooted at root.
Accepted
12,303
Submissions
16,672
 * @author jojo
 * Dec 29, 2021 2:26:31 PM
 */
public class FindNearestRightNodeInBinaryTree {
	// using pre-order traversal.
    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        Stack<Pair> stack = new Stack<>();
        int targetLevel = -1;
        TreeNode result = null;
        
        stack.push(new Pair(root, 0));
        
        while(!stack.isEmpty()){
            Pair top = stack.pop();
            
            if(targetLevel != -1 && top.level == targetLevel){
                result = top.node;
                break;
            }
            
            if(targetLevel != -1 && targetLevel < top.level){
                continue;
            }
            
            if(top.node.val == u.val){
                targetLevel = top.level;
            }
            
            if(top.node.right != null){
                stack.push(new Pair(top.node.right, top.level + 1));
            }
            
            if(top.node.left != null){
                stack.push(new Pair(top.node.left, top.level + 1));
            }
        }
        
        
        return result;
    }
    
    private static class Pair{
        TreeNode node;
        int level;
        public Pair(TreeNode node, int level){
            this.node = node;
            this.level = level;
        }
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left, right;
    }
    
    public TreeNode findNearestRightNode_2(TreeNode root, TreeNode u) {
        int depth = getDepth(root, u);
        
        //System.out.println("Depth: " + depth);
        
        List<TreeNode> nodes = new ArrayList<>();
        getLevelNodes(root, depth, nodes);
        
        TreeNode result = null;
        for(int i=1; i<nodes.size(); i++){
            if(nodes.get(i-1).val == u.val){
                result = nodes.get(i);
                break;
            }
        }
        
        return result;
    }
    
    private int getDepth(TreeNode node, TreeNode target){
        if(node == null){
            return 0;
        }
        
        if(node.val == target.val){
            return 1;
        }
        
        int left = getDepth(node.left, target);
        if(left > 0){
            return left + 1;
        }
        
        int right = getDepth(node.right, target);
        return  right > 0 ? right + 1 : 0;
    }
    
    private void getLevelNodes(TreeNode node, int depth, List<TreeNode> nodes){
        if(node == null){
            return;
        }
        
        if(depth == 1){
            nodes.add(node);
            return;
        }
        
        getLevelNodes(node.left, depth - 1, nodes);
        getLevelNodes(node.right, depth - 1, nodes);
    }
    
    
}
