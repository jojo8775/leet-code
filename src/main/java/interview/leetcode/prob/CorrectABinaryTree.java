package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * You have a binary tree with a small defect. There is exactly one invalid node where its right child incorrectly points to another node at the same depth but to the invalid node's right.

Given the root of the binary tree with this defect, root, return the root of the binary tree after removing this invalid node and every node underneath it (minus the node it incorrectly points to).

Custom testing:

The test input is read as 3 lines:

TreeNode root
int fromNode (not available to correctBinaryTree)
int toNode (not available to correctBinaryTree)
After the binary tree rooted at root is parsed, the TreeNode with value of fromNode will have its right child pointer pointing to the TreeNode with a value of toNode. Then, root is passed to correctBinaryTree.

 

Example 1:



Input: root = [1,2,3], fromNode = 2, toNode = 3
Output: [1,null,3]
Explanation: The node with value 2 is invalid, so remove it.
Example 2:



Input: root = [8,3,1,7,null,9,4,2,null,null,null,5,6], fromNode = 7, toNode = 4
Output: [8,3,1,null,null,9,4,null,null,5,6]
Explanation: The node with value 7 is invalid, so remove it and the node underneath it, node 2.
 

Constraints:

The number of nodes in the tree is in the range [3, 104].
-109 <= Node.val <= 109
All Node.val are unique.
fromNode != toNode
fromNode and toNode will exist in the tree and will be on the same depth.
toNode is to the right of fromNode.
fromNode.right is null in the initial tree from the test data.
Accepted
8,452
Submissions
11,568
 * @author jojo
 * Jan 1, 2022 4:19:19 PM
 */
public class CorrectABinaryTree {
    public TreeNode correctBinaryTree(TreeNode root) {
        Map<TreeNode, TreeNode> nodeMap = new HashMap<>();
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, null, 0));
        
        while(!queue.isEmpty()){
            Node top = queue.poll();
            TreeNode cur = top.cur;
            
            if(cur.left != null){
                nodeMap.put(cur.left, cur);
                queue.offer(new Node(cur.left, cur, -1));
            }
            
            if(cur.right != null){
                if(nodeMap.containsKey(cur.right)){
                    if(top.child  == -1){
                        top.parent.left = null;
                    }
                    else{
                        top.parent.right = null;
                    }
                    
                    break;
                }
                
                nodeMap.put(cur.right, cur);
                queue.offer(new Node(cur.right, cur, 1));
            }
        }
        
        return root;
    }
    
    private static class Node{
        TreeNode cur, parent;
        int child;
        
        public Node(TreeNode cur, TreeNode parent, int child){
            this.cur = cur;
            this.parent = parent;
            this.child = child;
        }
    }
    
    
    public TreeNode correctBinaryTree_tle(TreeNode root) {
        Set<TreeNode> s1 = new HashSet<>();
        s1.add(root);
        
        TreeNode defectiveNode = null;
        
        while(s1.size() != 0 && defectiveNode == null){
            Set<TreeNode> s2 = new HashSet<>();
            
            for(TreeNode n : s1){
                if(n.left != null){
                    s2.add(n.left);
                }
                
                if(n.right != null){
                    s2.add(n.right);
                }
            }
            
            for(TreeNode n : s2){
                if(s2.contains(n.right)){
                    defectiveNode = n;
                    break;
                }
            }
        }
        
        return removeDefectiveNode(root, defectiveNode);
    }
    
    private TreeNode removeDefectiveNode(TreeNode node, TreeNode defectiveNode){
        if(node == null){
            return null;
        }
        
        if(node.left != null && node.left.equals(defectiveNode)){
            node.left = null;
            return node;
        }
        
        if(node.right != null && node.left.equals(defectiveNode)){
            node.right = null;
            return node;
        }
        
        TreeNode left = removeDefectiveNode(node.left, defectiveNode);
        if(left == null){
            removeDefectiveNode(node.right, defectiveNode);
        }
        
        return node;
    }
    
    private static class TreeNode{
    	TreeNode left, right;
    	int val;
    }
}
