package interview.leetcode.practice.round3.tree;

import java.util.HashMap;
import java.util.Map;

public class PathSumIII {
    public int pathSum(TreeNode node, int target){ 
        Map<Integer, Integer> sumCache = new HashMap<>();
        sumCache.put(0,1);
        
        return dfs(node, target, 0, sumCache);
    }

    private int dfs(TreeNode node, int target, int sumSoFar, Map<Integer, Integer> sumCache){
        if(node == null){
            return 0;
        }
        
        sumSoFar += node.val; 
        int count = sumCache.getOrDefault(sumSoFar - target, 0);
        sumCache.put(sumSoFar, sumCache.getOrDefault(sumSoFar, 0) + 1);
        
        count += dfs(node.left, target, sumSoFar, sumCache);
        count += dfs(node.right, target, sumSoFar, sumCache);
        
        sumCache.put(sumSoFar, sumCache.get(sumSoFar) - 1);
        
        return count;
    }

    private static class TreeNode{
        TreeNode left=null, right=null;
        int val;
        public TreeNode(int val){
            this.val = val;
        }
    }
    
    public static void main(String[] args){
        TreeNode node = createTree(new int[] {1,2,3,4,5,6,7,8,9,10}, 0, 9);
        System.out.println(new PathSumIII().pathSum(node, 12));
    }
    
    private static TreeNode createTree(int[] arr, int beg, int end){
        if(beg > end){
            return null;
        }
        
        int mid = beg + (end-beg)/2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = createTree(arr, beg, mid - 1);
        node.right = createTree(arr, mid + 1, end);
        
        return node;
    }
}
