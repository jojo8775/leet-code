package interview.leetcode.prob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.

Each minute, a node becomes infected if:

The node is currently uninfected.
The node is adjacent to an infected node.
Return the number of minutes needed for the entire tree to be infected.

 

Example 1:


Input: root = [1,5,3,null,4,10,6,9,2], start = 3
Output: 4
Explanation: The following nodes are infected during:
- Minute 0: Node 3
- Minute 1: Nodes 1, 10 and 6
- Minute 2: Node 5
- Minute 3: Node 4
- Minute 4: Nodes 9 and 2
It takes 4 minutes for the whole tree to be infected so we return 4.
Example 2:


Input: root = [1], start = 1
Output: 0
Explanation: At minute 0, the only node in the tree is infected so we return 0.
 

Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 105
Each node has a unique value.
A node with a value of start exists in the tree.
Accepted
18,910
Submissions
33,685
 * @author jojo
 * Nov 13, 2022 11:46:28 PM
 */
public class AmountOfTimeForABinaryTreeToBeInfected {
	public int amountOfTime(TreeNode root, int start) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        TreeNode node = findInfectedNode(root, start, map);
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        
        Set<Integer> visited = new HashSet<>();
        visited.add(node.val);
        
        int count = 0;
        
        while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int i=0; i<size; i++){
                TreeNode top = queue.poll();
                if(top.left != null && visited.add(top.left.val)){
                    queue.offer(top.left);
                }
                
                if(top.right != null && visited.add(top.right.val)){
                    queue.offer(top.right);
                }
                
                if(map.containsKey(top) && visited.add(map.get(top).val)){
                    queue.offer(map.get(top));
                }
            }
            
            count++;
        }
        
        return count == 0 ? 0 : count - 1;
    }
    
    private TreeNode findInfectedNode(TreeNode node, int target, Map<TreeNode, TreeNode> map){
        if(node == null){
           return null;
        } 
        
        if(node.val == target){
            return node;
        }
        
        if(node.left != null){
            map.put(node.left, node);
            TreeNode infectedNode = findInfectedNode(node.left, target, map);
            
            if(infectedNode != null){
                return infectedNode;
            }
        }
        
        if(node.right != null){
            map.put(node.right, node);
            TreeNode infectedNode = findInfectedNode(node.right, target, map);
            
            if(infectedNode != null){
                return infectedNode;
            }
        }
        
        return null;
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left, right;
    }
}
