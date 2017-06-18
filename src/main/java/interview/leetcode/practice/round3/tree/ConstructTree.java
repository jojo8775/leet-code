package interview.leetcode.practice.round3.tree;

public class ConstructTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return createTree(inorder, 0, inorder.length - 1, postorder, new int[]{inorder.length - 1});
    }
    
    private TreeNode createTree(int[] inorder, int beg, int end, int[] postorder, int[] idx){
        if(idx[0] < 0){
            return null;
        }
        
        int pos = binarySearch(inorder, beg, end, postorder[idx[0]]);
        if(pos == -1){
            return null;
        }
        
        TreeNode node = new TreeNode(postorder[idx[0]--]);
        node.right = createTree(inorder, pos + 1, end, postorder, idx);
        node.left = createTree(inorder, beg, pos - 1, postorder, idx);
        
        return node;
    }
    
    private int binarySearch(int[] arr, int beg, int end, int target){
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            if(arr[mid] == target){
                return mid;
            }
            
            if(arr[mid] < target){
                beg = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        
        return -1;
    }
    
    private static class TreeNode{
        int val;
        TreeNode left=null, right=null;
        TreeNode(int val){
            this.val = val;
        }
    }
    
    public static void main(String[] args){
        TreeNode node = new ConstructTree().buildTree(new int[]{2,1}, new int[]{2,1});
        
        System.out.println("result");
    }
}
