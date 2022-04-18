package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,

If isLefti == 1, then childi is the left child of parenti.
If isLefti == 0, then childi is the right child of parenti.
Construct the binary tree described by descriptions and return its root.

The test cases will be generated such that the binary tree is valid.

 

Example 1:


Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
Output: [50,20,80,15,17,19]
Explanation: The root node is the node with value 50 since it has no parent.
The resulting binary tree is shown in the diagram.
Example 2:


Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
Output: [1,2,null,null,3,4]
Explanation: The root node is the node with value 1 since it has no parent.
The resulting binary tree is shown in the diagram.
 

Constraints:

1 <= descriptions.length <= 104
descriptions[i].length == 3
1 <= parenti, childi <= 105
0 <= isLefti <= 1
The binary tree described by descriptions is valid.
Accepted
8,459
Submissions
12,345
 * @author jojo
 * Mar 7, 2022 12:47:37 AM
 */
public class CreateBinaryFromDescription {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        Map<Integer, Integer> childToParentMap = new HashMap<>();
        
        for(int[] d : descriptions){
            int parent = d[0];
            int child = d[1];
            int leg = d[2];
                
            childToParentMap.put(child, parent);
            
            TreeNode pNode = nodeMap.get(parent);
            if(pNode == null){
                pNode = new TreeNode(parent);
                nodeMap.put(parent, pNode);
            }
            
            TreeNode cNode = nodeMap.get(child);
            if(cNode == null){
                cNode = new TreeNode(child);
                nodeMap.put(child, cNode);
            }
            
            if(leg == 1){
                pNode.left = cNode;
            }
            else{
                pNode.right = cNode;
            }
        }

        int assumedParent = descriptions[0][0];
        while(childToParentMap.containsKey(assumedParent)){
            assumedParent = childToParentMap.get(assumedParent);
        }
        
        return nodeMap.get(assumedParent);
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left, right;
    	public TreeNode(int val) {
    		this.val = val;
    	}
    }
}
