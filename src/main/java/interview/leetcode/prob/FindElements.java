package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a binary tree with the following rules:

root.val == 0
For any treeNode:
If treeNode.val has a value x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
If treeNode.val has a value x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.

Implement the FindElements class:

FindElements(TreeNode* root) Initializes the object with a contaminated binary tree and recovers it.
bool find(int target) Returns true if the target value exists in the recovered binary tree.
 

Example 1:


Input
["FindElements","find","find"]
[[[-1,null,-1]],[1],[2]]
Output
[null,false,true]
Explanation
FindElements findElements = new FindElements([-1,null,-1]); 
findElements.find(1); // return False 
findElements.find(2); // return True 
Example 2:


Input
["FindElements","find","find","find"]
[[[-1,-1,-1,-1,-1]],[1],[3],[5]]
Output
[null,true,true,false]
Explanation
FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
findElements.find(1); // return True
findElements.find(3); // return True
findElements.find(5); // return False
Example 3:


Input
["FindElements","find","find","find","find"]
[[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
Output
[null,true,false,false,true]
Explanation
FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
findElements.find(2); // return True
findElements.find(3); // return False
findElements.find(4); // return False
findElements.find(5); // return True
 

Constraints:

TreeNode.val == -1
The height of the binary tree is less than or equal to 20
The total number of nodes is between [1, 104]
Total calls of find() is between [1, 104]
0 <= target <= 106
Seen this question in a real interview before?
1/5
Yes
No
Accepted
93.1K
Submissions
115.7K
Acceptance Rate
80.5%

 * 
 * Feb 20, 2025 - 8:44:04 PM
 * Jojo 
 */
public class FindElements {
	Set<Integer> list = new HashSet<>();

    public FindElements(TreeNode root) {
        root.val = 0;
        fixTree(root, list);
    }

    private void fixTree(TreeNode node, Set<Integer> list){
        if(node == null){
            return;
        }

        list.add(node.val);
        if(node.left != null){
            node.left.val = (2 * node.val) + 1;
            fixTree(node.left, list);
        }

        if(node.right != null){
            node.right.val = (2 * node.val) + 2;
            fixTree(node.right, list);
        }
    }
    
    public boolean find(int target) {
        return list.contains(target);
    }
    
    private static class TreeNode{
    	TreeNode left = null, right = null;
    	int val = 0;
    }
}
