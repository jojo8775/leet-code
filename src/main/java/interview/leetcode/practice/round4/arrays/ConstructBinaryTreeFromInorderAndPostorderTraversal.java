package interview.leetcode.practice.round4.arrays;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return createTreeNode(inorder, postorder, new int[]{postorder.length - 1}, 0, postorder.length - 1);
    }
    
    private TreeNode createTreeNode(int[] inorder, int[] postorder, int[] idx, int beg, int end){
        if(idx[0] < 0){
            return null;
        }
        
        int index = findIndex(inorder, beg, end, postorder[idx[0]]);
        if(index == -1){
            return null;
        }
        
        TreeNode node = new TreeNode(postorder[idx[0]]);
        idx[0]--;
        node.right = createTreeNode(inorder, postorder, idx, index + 1, end);
        node.left = createTreeNode(inorder, postorder, idx, beg, index - 1);
        
        return node;
    }
    
    private int findIndex(int[] inorder, int beg, int end, int target){
        for(int i=beg; i<=end; i++){
            if(inorder[i] == target){
                return i;
            }
        }
        
        return -1;
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
