package interview.leetcode.prob;

/**
 * Given a binary tree where each path going from the root to any leaf form a valid sequence, check if a given string is a valid sequence in such binary tree. 

We get the given string from the concatenation of an array of integers arr and the concatenation of all values of the nodes along a path results in a sequence in the given binary tree.

 

Example 1:



Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
Output: true
Explanation: 
The path 0 -> 1 -> 0 -> 1 is a valid sequence (green color in the figure). 
Other valid sequences are: 
0 -> 1 -> 1 -> 0 
0 -> 0 -> 0
Example 2:



Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
Output: false 
Explanation: The path 0 -> 0 -> 1 does not exist, therefore it is not even a sequence.
Example 3:



Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
Output: false
Explanation: The path 0 -> 1 -> 1 is a sequence, but it is not a valid sequence.
 

Constraints:

1 <= arr.length <= 5000
0 <= arr[i] <= 9
Each node's value is between [0 - 9].
Accepted
34,515
Submissions
76,902
 * @author jojo
 * Sep 3, 2020  4:22:24 PM
 */
public class ValidSequeceFromRootToLeaf {
	public boolean isValidSequence(TreeNode root, int[] arr) {
        return parse(root, arr, 0);
    }
    
    private boolean parse(TreeNode node, int[] arr, int idx){
        if(node == null || idx == arr.length || node.val != arr[idx] ){
            return false;
        }
        
        if(node.left == null && node.right == null && idx == arr.length - 1){
            return true;
        }
        
        if(parse(node.left, arr, idx + 1) || parse(node.right, arr, idx + 1)){
            return true;
        }
        
        return false;
    }
    
    private static class TreeNode{
    	TreeNode left, right;
    	int val;
    }
}
