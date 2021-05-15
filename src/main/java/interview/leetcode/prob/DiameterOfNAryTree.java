package interview.leetcode.prob;

import java.util.List;

/**
 * Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.

The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree. This path may or may not pass through the root.

(Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)

 

Example 1:



Input: root = [1,null,3,2,4,null,5,6]
Output: 3
Explanation: Diameter is shown in red color.
Example 2:



Input: root = [1,null,2,null,3,4,null,5,null,6]
Output: 4
Example 3:



Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: 7
 

Constraints:

The depth of the n-ary tree is less than or equal to 1000.
The total number of nodes is between [1, 104].
Accepted
7,927
Submissions
11,400
 * @author jojo
 * May 13, 2021  10:35:03 PM
 */
public class DiameterOfNAryTree {
    public int diameter(Node root) {
        int[] result = {0};
        findDiameter(root, result);
        
        return result[0];
    }
    
    private int findDiameter(Node node, int[] result){
        if(node == null){
            return 0;
        }
        
        int max1 = 0, max2 = 0;
        
        for(Node n : node.children){
            int val = findDiameter(n, result);
            
            if(val > max1){
                max2 = max1;
                max1 = val;
            }
            else if(val > max2){
                max2 = val;
            }
        }
        
        result[0] = Math.max(result[0], max1 + max2);
        
        return Math.max(max1, max2) + 1;
    }
    
    private static class Node{
    	int val;
    	List<Node> children;
    }
}
