package interview.leetcode.prob;

/**
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 * @author jojo
 *May 14, 201712:23:25 AM
 */
public class BinaryTreeLongestConsecutiveSequenceII {
    public int longestConsecutive(TreeNode root) {
        int[] maxPath = new int[1];
        findPath(root, maxPath);
        return maxPath[0];
    }
    
    private Node findPath(TreeNode node, int[] maxPath){
        if(node == null){
            return null;
        }
        
        Node left = findPath(node.left, maxPath);
        Node right = findPath(node.right, maxPath);
        
        Node curNode = new Node(node);
        if(left != null){
            if(left.node.val - node.val == 1){
                curNode.increasingPath = 1 + left.increasingPath; 
            }
            else if(left.node.val - node.val == -1){
                curNode.decreasingPath = 1 + left.decreasingPath; 
            }
        }
        
        if(right != null){
            if(right.node.val - node.val == 1){
                curNode.increasingPath = Math.max(curNode.increasingPath, 1 + right.increasingPath); 
            }
            else if(right.node.val - node.val == -1){
                curNode.decreasingPath = Math.max(curNode.decreasingPath, 1 + right.decreasingPath); 
            }
        }
        
        maxPath[0] = Math.max(maxPath[0], curNode.decreasingPath + curNode.increasingPath - 1);
        return curNode;
    }
    
    private static class Node{
        TreeNode node; 
        int increasingPath = 1, decreasingPath = 1;
        
        public Node(TreeNode node){
            this.node = node;
        }
    }
    
    private static class TreeNode{
        TreeNode left = null, right = null;
        int val;
        public TreeNode(int val){
            this.val = val;
        }
    }
}
