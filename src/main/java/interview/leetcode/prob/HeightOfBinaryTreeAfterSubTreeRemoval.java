package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.

You have to perform m independent queries on the tree where in the ith query you do the following:

Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.

Note:

The queries are independent, so the tree returns to its initial state after each query.
The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
 

Example 1:


Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
Output: [2]
Explanation: The diagram above shows the tree after removing the subtree rooted at node with value 4.
The height of the tree is 2 (The path 1 -> 3 -> 2).
Example 2:


Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
Output: [3,2,3,2]
Explanation: We have the following queries:
- Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 4).
- Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -> 8 -> 1).
- Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 6).
- Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -> 9 -> 3).
 

Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
m == queries.length
1 <= m <= min(n, 104)
1 <= queries[i] <= n
queries[i] != root.val
Accepted
32,695
Submissions
75,602
 * 
 * Oct 26, 2024 - 2:00:33 AM
 * Jojo 
 */
public class HeightOfBinaryTreeAfterSubTreeRemoval {
	public int[] treeQueries(TreeNode root, int[] queries) {
        Map<Integer, Integer> hMap = new HashMap<>();
        
        // first traverse left child and then right child. This way the right child
        // will have the max height at a given point
        traverseLeftToRight(root, new int[]{0}, 0, hMap);
        
        // traverse right to left to do the same treatment to the left child node.
        // this every node at any given point of time will have the heights depth
        traverseRightToLeft(root, new int[]{0}, 0, hMap);
        
        int[] result = new int[queries.length];
        
        for(int i=0; i<queries.length; i++){
            result[i] = hMap.get(queries[i]);
        }
        
        return result;
    }
    
    private void traverseLeftToRight(TreeNode node, int[] maxHeight, int curHeight, Map<Integer, Integer> hMap){
        if(node == null){
            return;
        }
        
        // height is zero indexed so need to store the node height before incrementing it.
        hMap.put(node.val, maxHeight[0]);
        maxHeight[0] = Math.max(maxHeight[0], curHeight);
        
        traverseLeftToRight(node.left, maxHeight, curHeight + 1, hMap);
        traverseLeftToRight(node.right, maxHeight, curHeight + 1, hMap);
    }
    
    private void traverseRightToLeft(TreeNode node, int[] maxHeight, int curHeight, Map<Integer, Integer> hMap){
        if(node == null){
            return;
        }
        
        // height is zero indexed so need to store the node height before incrementing it
        // at this point all the nodes in the tree will have the max possible height in the tree
        // will contain the left and right max height. 
        hMap.put(node.val, Math.max(hMap.get(node.val), maxHeight[0]));
        maxHeight[0] = Math.max(maxHeight[0], curHeight);
        
        traverseRightToLeft(node.right, maxHeight, curHeight + 1, hMap);
        traverseRightToLeft(node.left, maxHeight, curHeight + 1, hMap);
    }
    
    private static class TreeNode{
    	TreeNode left = null, right = null;
    	int val = 0;
    }
}
