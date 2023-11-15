package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.

If the tree has more than one mode, return them in any order.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:


Input: root = [1,null,2,2]
Output: [2]
Example 2:

Input: root = [0]
Output: [0]
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105
 

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
Accepted
213,193
Submissions
419,301
 * @author jojo
 * Oct. 31, 2023 8:11:12 p.m.
 */
public class FindModeInBinarySearchTree {
	public int[] findMode(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        
        while(root != null || !stack.isEmpty()){
            if(root != null){
                stack.push(root);
                root = root.left;
            }
            else{
                root = stack.pop();
                map.put(root.val, map.getOrDefault(root.val, 0) + 1);
                max = Math.max(max, map.get(root.val));
                
                root = root.right;
            }
        }
        
        List<Integer> list = new ArrayList<>();
        
        for(int key : map.keySet()){
            if(map.get(key) == max){
                list.add(key);
            }
        }
        
        int[] result = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            result[i] = list.get(i);
        }
        
        return result;
    }
	
	private static class TreeNode{
		public int val;
		public TreeNode left = null, right = null;
	}
}
