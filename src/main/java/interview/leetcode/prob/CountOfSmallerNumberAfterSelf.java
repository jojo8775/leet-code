package interview.leetcode.prob;

import java.util.Arrays;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].
 * @author jojo
 *
 */
public class CountOfSmallerNumberAfterSelf {
	public List<Integer> countSmaller(int[] nums) {
        Integer[] result = new Integer[nums.length];
        
        Node root = new Node(nums[nums.length - 1], 0);
        root.dup = 1;
        
        result[result.length - 1] = 0;
        
        for(int i=nums.length-2; i>=0; i--){
            root = insert(root, result, i, nums, 0);
        }
        
        return Arrays.asList(result);
    }
    
    private Node insert(Node node, Integer[] result, int idx, int[] nums, int sum){
        if(node == null){
            node = new Node(nums[idx], 0);
            node.dup = 1;
            result[idx] = sum;
        }
        
        else if(node.val > nums[idx]){
            node.sum +=1;
            node.left = insert(node.left, result, idx, nums, sum);
        }
        
        else if(node.val < nums[idx]){
            node.right = insert(node.right, result, idx, nums, sum + node.dup + node.sum);
        }
        
        else{
            node.dup += 1;
            result[idx] = node.sum + sum;
        }
        
        return node;
    }
    
    private static class Node{
        public Node right;
        public Node left;
        public int val;
        public int sum;
        public int dup;
        
        public Node(int val, int sum){
            this.val = val;
            this.sum = sum;
        }
    }
    
    public static void main(String[] args){
    	int[] nums = {3,2,2,6,1};
    	List<Integer> result = new CountOfSmallerNumberAfterSelf().countSmaller(nums);
    	
    	for(int i : result){
    		System.out.print(i + ", ");
    	}
    }
}
