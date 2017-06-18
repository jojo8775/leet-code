package interview.leetcode.practice.round3.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFrequentSubtree {
    public int[] findFrequentTreeSum(TreeNode root) {
        int[] maxCount = new int[1];
        parseTree(root, new HashMap<Integer, Integer>(), maxCount, null);
        
        List<Integer> list = new ArrayList<>();
        parseTree(root, new HashMap<Integer, Integer>(), maxCount, list);
        
        int[] result = new int[list.size()];
        for(int i=0; i<result.length; i++){
            result[i] = list.get(i);
        }
        
        return result;
    }
    
    private int parseTree(TreeNode node, Map<Integer, Integer> map, int[] arr, List<Integer> result){
        if(node == null){
            return 0;
        }
        
        int left = parseTree(node.left, map, arr, result);
        int right = parseTree(node.right, map, arr, result);
        
        int sum = left + right + node.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        
        if(result != null){
            if(map.get(sum) == arr[0]){
                result.add(node.val);
            }
        }
        else{
            arr[0] = Math.max(arr[0], map.get(sum));
        }
        
        return sum;
    }
    
    private static class TreeNode{
        TreeNode left = null, right = null;
        int val;
        public TreeNode(int val){
            this.val = val;
        }
    }
}
