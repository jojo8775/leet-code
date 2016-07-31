package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
 * @author jojo
 *
 */
public class SumRootToLeaf {
	public int sumNumbers(TreeNode root) {
        int result = 0;
        if(root == null){
            return result;
        }
        
        List<Integer> pathSum = new ArrayList<Integer>();
        dfs(root, new ArrayList<Integer>(), pathSum);
        
        for(int i : pathSum){
            result += i;
        }
        
        return result;
    }
    
    private void dfs(TreeNode node, List<Integer> path, List<Integer> pathSum){
        if(node == null){
            return;
        }
        
        path.add(node.val);
        if(node.left == null && node.right == null){
            int num = 0, idx = 0;
            while(idx < path.size()){
                num = 10*num + path.get(idx++);
            }
            
            pathSum.add(num);
            path.remove(path.size() - 1);
            return;
        }
        
        dfs(node.left, path, pathSum);
        dfs(node.right, path, pathSum);
        path.remove(path.size() - 1);
    }
    
	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
