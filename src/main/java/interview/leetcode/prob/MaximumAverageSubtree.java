package interview.leetcode.prob;

/**
 * Given the root of a binary tree, find the maximum average value of any subtree of that tree.

(A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the sum of its values, divided by the number of nodes.)

 

Example 1:



Input: [5,6,1]
Output: 6.00000
Explanation: 
For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
For the node with value = 6 we have an average of 6 / 1 = 6.
For the node with value = 1 we have an average of 1 / 1 = 1.
So the answer is 6 which is the maximum.
 

Note:

The number of nodes in the tree is between 1 and 5000.
Each node will have a value between 0 and 100000.
Answers will be accepted as correct if they are within 10^-5 of the correct answer.

 * @author jojo
 * Aug 31, 2019 1:53:00 AM
 */
public class MaximumAverageSubtree {
	public double maximumAverageSubtree(TreeNode root) {
        return find(root)[2];
    }
    
    private double[] find(TreeNode node){
        if(node == null){
            return new double[] {0, 0, 0}; // [0] = node sum; [1] = node count; [2] = max average sofar 
        }
        
        double[] left = find(node.left), right = find(node.right);
        double childMaxAvg = Math.max(left[2], right[2]);
        double nodeCount = left[1] + right[1] + 1, nodeSum = left[0] + right[0] + node.val;
        
        return new double[] {nodeSum, nodeCount, Math.max(childMaxAvg, nodeSum / nodeCount)};
    }
    
    private static class TreeNode{
    	int val; 
    	TreeNode left, right;
    }
}
