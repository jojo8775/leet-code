package interview.leetcode.practice.round3.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostOrderIterative {
    public List<Integer> postOrder(TreeNode node){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> result = new ArrayList<>();
        TreeNode lastVisited = null;
        while(!stack.isEmpty() || node != null){
            if(node != null){
               stack.push(node);
               node = node.left;
            }
            else{
                node = stack.peek();
                if(node.right != null && !node.right.equals(lastVisited)){
                    node = node.right;
                }
                else{
                    result.add(node.val);
                    lastVisited = stack.pop();
                    node = null;
                }
            }
        }
        
        return result;
    }
    
    private static class TreeNode{
        TreeNode left = null, right = null;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }
    
    public TreeNode createTreeNode(int[] arr, int beg, int end){
        if(beg > end){
            return null;
        }
        
        int mid = beg + (end - beg)/2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = createTreeNode(arr, beg, mid - 1);
        node.right = createTreeNode(arr, mid + 1, end);
        
        return node;
    }
    
    public static void main(String[] args){
        PostOrderIterative pi = new PostOrderIterative();
        TreeNode root = pi.createTreeNode(new int[]{1,2,3,4,5,6,7,8,9}, 0, 8);
        
        List<Integer> result = pi.postOrder(root);
        for(int n : result){
            System.out.print(n + ", ");
        }
    }
}
