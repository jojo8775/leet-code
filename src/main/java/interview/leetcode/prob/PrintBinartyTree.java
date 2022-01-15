package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, construct a 0-indexed m x n string matrix res that represents a formatted layout of the tree. The formatted layout matrix should be constructed using the following rules:

The height of the tree is height and the number of rows m should be equal to height + 1.
The number of columns n should be equal to 2height+1 - 1.
Place the root node in the middle of the top row (more formally, at location res[0][(n-1)/2]).
For each node that has been placed in the matrix at position res[r][c], place its left child at res[r+1][c-2height-r-1] and its right child at res[r+1][c+2height-r-1].
Continue this process until all the nodes in the tree have been placed.
Any empty cells should contain the empty string "".
Return the constructed matrix res.

 

Example 1:


Input: root = [1,2]
Output: 
[["","1",""],
 ["2","",""]]
Example 2:


Input: root = [1,2,3,null,4]
Output: 
[["","","","1","","",""],
 ["","2","","","","3",""],
 ["","","4","","","",""]]
 

Constraints:

The number of nodes in the tree is in the range [1, 210].
-99 <= Node.val <= 99
The depth of the tree will be in the range [1, 10].
Accepted
46,381
Submissions
79,234
 * @author jojo
 * Jan 7, 2022 11:11:38 PM
 */
public class PrintBinartyTree {
    public List<List<String>> printTree(TreeNode root) {
        int h = findHeight(root);
        int n = (int)Math.pow(2,h) - 1;
        
        List<String> row = new ArrayList<>();
        for(int i=0; i<n; i++){
            row.add("");
        }
        
        List<List<String>> result = new ArrayList<>();
        
        for(int i=0; i<h; i++){
            result.add(new ArrayList<>(row));
        }
        
        populate(result, root, 0, n-1, 0, h);
        
        return result;
    }
    
    private void populate(List<List<String>> result, TreeNode node, int i, int j, int rowIdx, int maxRow){
        if(rowIdx == maxRow || node == null){
            return;
        }
        
        // System.out.println("i:" + i + "  j:" + j + "  rIdx:" + rowIdx + "  nVal:" + node.val);
        
        int mid = i + ((j - i) / 2);
        result.get(rowIdx).set(mid, String.valueOf(node.val));
        populate(result, node.left, i, mid - 1, rowIdx + 1, maxRow);
        populate(result, node.right, mid + 1, j, rowIdx + 1, maxRow);
    }
    
    private int findHeight(TreeNode node){
        if(node == null){
            return 0;
        }
        
        int left = findHeight(node.left);
        int right = findHeight(node.right);
        
        return Math.max(left, right) + 1;
    }
    
    private static class TreeNode {
    	TreeNode left, right;
    	int val;
    }
}
