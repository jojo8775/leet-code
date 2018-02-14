package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.

Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.

Example 1:

Input:
root = [1, 3, 2], k = 1
Diagram of binary tree:
          1
         / \
        3   2

Output: 2 (or 3)

Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
Example 2:

Input:
root = [1], k = 1
Output: 1

Explanation: The nearest leaf node is the root node itself.
Example 3:

Input:
root = [1,2,3,4,null,null,null,5,null,6], k = 2
Diagram of binary tree:
             1
            / \
           2   3
          /
         4
        /
       5
      /
     6

Output: 3
Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
Note:
root represents a binary tree with at least 1 node and at most 1000 nodes.
Every node has a unique node.val in range [1, 1000].
There exists some node in the given binary tree for which node.val == k.
 * @author jojo
 *Feb 14, 20181:40:44 PM
 */
public class ClosestLeafInABinaryTree {
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        
        // first dfs the node
        TreeNode node = findNode(root, k, map);
        
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(node);
        // needed to handel parent
        visited.add(node);
        
        // if input is invalid
        if(root == null || node == null){
            return -1;
        }
        
        // doing bfs on the found node till encounter a leaf
        while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int i=0; i<size; i++){
                TreeNode cur = queue.poll();
                
                if(cur.left == null && cur.right == null){
                    return cur.val;
                }
                
                TreeNode parent = map.get(cur);
                if(parent != null && visited.add(parent)){
                    queue.offer(parent);
                }
                
                if(cur.left != null && visited.add(cur.left)){
                    queue.offer(cur.left);
                }
                
                if(cur.right != null && visited.add(cur.right)){
                    queue.offer(cur.right);
                }
            }
        }
        
        return -1; // unreachable code;
    }
    
    // dfs to find the node corresponding to given target
    private TreeNode findNode(TreeNode node, int target, Map<TreeNode, TreeNode> map){
        if(node == null){
            return null;
        }
        
        if(node.val == target){
            return node;
        }
        
        TreeNode left = null, right = null;
        if(node.left != null){
            map.put(node.left, node);
            left = findNode(node.left, target, map);
        }
        
        if(node.right != null){
            map.put(node.right, node);
            right = findNode(node.right, target, map);
        }
        
        return left == null ? right : left;
    }
    
    private static class TreeNode{
        int val; 
        TreeNode left = null, right = null;
        
        public TreeNode(int val){
            this.val = val;
        }
    }
}
