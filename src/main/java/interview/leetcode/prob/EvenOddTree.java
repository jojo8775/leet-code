package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A binary tree is named Even-Odd if it meets the following conditions:

The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.

 

Example 1:


Input: root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
Output: true
Explanation: The node values on each level are:
Level 0: [1]
Level 1: [10,4]
Level 2: [3,7,9]
Level 3: [12,8,6,2]
Since levels 0 and 2 are all odd and increasing and levels 1 and 3 are all even and decreasing, the tree is Even-Odd.
Example 2:


Input: root = [5,4,2,3,3,7]
Output: false
Explanation: The node values on each level are:
Level 0: [5]
Level 1: [4,2]
Level 2: [3,3,7]
Node values in level 2 must be in strictly increasing order, so the tree is not Even-Odd.
Example 3:


Input: root = [5,9,1,3,5,7]
Output: false
Explanation: Node values in the level 1 should be even integers.
 

Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 106
Accepted
27,204
Submissions
51,789
 * @author jojo
 * Dec 30, 2021 10:43:02 PM
 */
public class EvenOddTree {
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int level = 0;
        
        while(!queue.isEmpty()){
            TreeNode prev = null;
            int size = queue.size();
            
            while(size-- > 0){
                TreeNode cur = queue.poll();
                if(level % 2 == 0 ? (cur.val % 2 != 1) : (cur.val % 2 != 0)){
                    return false;
                }
                
                //System.out.println("prev:" + (prev == null));
                
                if(prev != null){
                    if(level % 2 == 0 ? prev.val >= cur.val : prev.val <= cur.val){
                        return false;
                    }
                }
                
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                
                if(cur.right != null){
                    queue.offer(cur.right);
                }
                
                prev = cur;
            }
            
            level++;
        }
        
        return true;
    }
	
	public boolean isEvenOddTree_old(TreeNode root) {
        List<TreeNode> l1 = new ArrayList<>();
        l1.add(root);
        
        int level = 0;
        
        while(l1.size() != 0){
            if(level % 2 == 0 ? !isOddIncresing(l1) : !isEvenDecreasing(l1)){
                return false;
            }
            
            List<TreeNode> l2 = new ArrayList<>();
            for(TreeNode n : l1){
                if(n.left != null){
                    l2.add(n.left);
                }
                
                if(n.right != null){
                    l2.add(n.right);
                }
            }
            
            level++;
            l1 = l2;
        }
        
        return true;
    }
    
    private boolean isEvenDecreasing(List<TreeNode> list){
        for(int i=1; i<list.size(); i++){
            if(list.get(i-1).val <= list.get(i).val || list.get(i).val % 2 != 0){
                return false;
            }
        }
        
        return list.get(0).val % 2 == 0;
    }
    
    private boolean isOddIncresing(List<TreeNode> list){
        for(int i=1; i<list.size(); i++){
            if(list.get(i-1).val >= list.get(i).val || list.get(i).val % 2 != 1){
                return false;
            }
        }
        
        return list.get(0).val % 2 == 1;
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left = null, right = null;
    }
}
