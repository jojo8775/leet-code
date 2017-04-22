package interview.leetcode.practice.round3.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreOrder {
    public List<Integer> preOrder(TreeNode node){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        List<Integer> result = new ArrayList<>();
        while(node != null || !stack.isEmpty()){
            if(node != null){
                result.add(node.val);
                stack.push(node);
                node = node.left;
            }
            else{
                node = stack.pop();
                node = node.right;
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
    
    public void traverse(TreeNode node){
        if(node == null){
            return;
        }
        
        System.out.print(node.val + ", ");
        traverse(node.left);
        traverse(node.right);
    }
    
    public static void main(String[] args){
        PreOrder pi = new PreOrder();
        TreeNode root = pi.createTreeNode(new int[]{1,2,3,4,5,6,7,8,9}, 0, 8);
        
        List<Integer> result = pi.preOrder(root);
        for(int n : result){
            System.out.print(n + ", ");
        }
        
        System.out.println("\nrecursive =============");
        pi.traverse(root);
    }
}
