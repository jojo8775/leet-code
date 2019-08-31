package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * In an infinite binary tree where every node has two children, the nodes are labelled in row order.

In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.



Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.

 

Example 1:

Input: label = 14
Output: [1,3,4,14]
Example 2:

Input: label = 26
Output: [1,2,6,10,26]
 

Constraints:

1 <= label <= 10^6
Accepted
7,195
Submissions
10,252
 * @author jojo
 * Aug 31, 2019 2:48:03 AM
 */
public class PathInZigzagLableBinaryTree {
	public List<Integer> pathInZigZagTree(int label) {
        int level = 0;
        
        while(Math.pow(2, level) - 1 < label){
            level++;
        }
        
        level --; // making adjustments for 0 index;
        
        List<Integer> result = new ArrayList<>();
        
        while(level != 0){
            result.add(0, label);
            
            int offsetAdjustment = label - (int) Math.pow(2, level);
            
            // System.out.println("pos: " + offsetAdjustment + "   level: " + level);
            
            label = label - (offsetAdjustment + 1); // bringing label to the left or right edge based on the offsetAdjustment +/- (since root is 1, need to add 1 to the offset)
            label = label - offsetAdjustment/2; // bringing one level up
            
            level--;
        }
        
        result.add(0,1); // adding root;
        
        return result;
    }
}
