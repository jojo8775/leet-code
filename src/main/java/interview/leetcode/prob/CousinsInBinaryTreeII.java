package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Oct 22, 2024 - 9:24:07 PM
 * Jojo 
 */
public class CousinsInBinaryTreeII {
	public TreeNode replaceValueInTree(TreeNode root) {
        List<TreeNode> list1 = new ArrayList<>();
        
        root.val = 0;
        list1.add(root);
        
        while(list1.size() != 0){
            int totalSum = 0;
            
            for(TreeNode entry : list1){
                if(entry.left != null){
                    totalSum += entry.left.val;
                }
                
                if(entry.right != null){
                    totalSum += entry.right.val;
                }
            }
            
            List<TreeNode> list2 = new ArrayList<>();
            
            for(TreeNode entry : list1){
                int sum = 0;
                
                if(entry.left != null){
                    sum += entry.left.val;
                }
                
                if(entry.right != null){
                    sum += entry.right.val;
                }
                
                if(entry.left != null){
                    entry.left.val = totalSum - sum;
                    list2.add(entry.left);
                }
                
                if(entry.right != null){
                    entry.right.val = totalSum - sum;
                    list2.add(entry.right);
                }
            }
            
            list1 = list2;
        }
        
        return root;
    }
	
	private static class TreeNode{
		TreeNode left = null, right = null;
		int val = 0;
	}
}
