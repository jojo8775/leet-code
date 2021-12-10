package interview.leetcode.prob;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.

 

Example 1:


Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
Example 2:


Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.
 

Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
1 <= startValue, destValue <= n
startValue != destValue
Accepted
5,575
Submissions
13,302
 * @author jojo
 * Dec 6, 2021 11:55:53 PM
 */
public class StepByStepDirectionFromABinaryTreeNodeToAnother {
	public String getDirections(TreeNode root, int startValue, int destValue) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        TreeNode startNode = null;
        while(!queue.isEmpty()){
            TreeNode top = queue.poll();
            if(top.val == startValue){
                startNode = top;
                break;
            }
                        
            if(top.left != null){
                parentMap.put(top.left, top);
                queue.offer(top.left);
            }
            
            if(top.right != null){
                parentMap.put(top.right, top);
                queue.offer(top.right);
            }
        }
        
        return dfs(startNode, parentMap, destValue);
    }
    
    private String dfs(TreeNode node, Map<TreeNode, TreeNode> parentMap, int target){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(node, "", null));
        
        while(!queue.isEmpty()){
            Node top = queue.poll();
            
            TreeNode tNode = top.tNode;
            
            if(tNode.val == target){
                return top.path;
            }
            
            if(tNode.left != null && top.child != tNode.left){
                queue.offer(new Node(tNode.left, top.path + "L", tNode));
            }
            
            if(tNode.right != null && top.child != tNode.right){
                queue.offer(new Node(tNode.right, top.path + "R", tNode));
            }
            
            if(parentMap.get(tNode) != null && top.child != parentMap.get(tNode)){
                queue.offer(new Node(parentMap.get(tNode), top.path + "U", tNode));
            }
        }
        
        return "";
    }
    
    private static class Node{
        TreeNode tNode;
        String path;
        TreeNode child;
        
        public Node(TreeNode tNode, String path, TreeNode child){
            this.tNode = tNode;
            this.path = path;
            this.child = child;
        }
    }
    
    private static class TreeNode{
    	TreeNode left;
    	TreeNode right;
    	int val;
    }
}
