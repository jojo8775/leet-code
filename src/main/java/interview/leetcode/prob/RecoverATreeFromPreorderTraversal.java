package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * We run a preorder depth first search on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  (If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node is 0.)

If a node has only one child, that child is guaranteed to be the left child.

Given the output S of this traversal, recover the tree and return its root.

 

Example 1:



Input: "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]
Example 2:



Input: "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]
 

Example 3:



Input: "1-401--349---90--88"
Output: [1,401,null,349,88,90]
 

Note:

The number of nodes in the original tree is between 1 and 1000.
Each node will have a value between 1 and 10^9.
 * @author jojo
 * Sep 1, 2019 3:33:46 AM
 */
public class RecoverATreeFromPreorderTraversal {
	public TreeNode recoverFromPreorder_adv(String S) {
        if(S.isEmpty()){
            return null;
        }
        
        List<Stack<TreeNode>> nodes = new ArrayList<>();
        
        int i = 0, len = S.length();
        while(i < len){
            int[] tuple = parseStr(S, i);
            
            TreeNode cn = new TreeNode(tuple[1]);
            
            // if the current node is not root, then link current node with parent node.
            if(tuple[0] != -1){
                TreeNode pn = nodes.get(tuple[0]).peek();
            
                if(pn.left == null){
                    pn.left = cn;
                }
                else{
                    pn.right = cn;
                }    
            }
            
            if(tuple[0] + 1 == nodes.size()){
                nodes.add(new Stack<TreeNode>());
            }

            nodes.get(tuple[0] + 1).push(cn);
            
            i = tuple[2];
        }
        
        return nodes.get(0).peek();        
    }
    
    private int[] parseStr(String s, int i){
        int depth = -1, num = 0, idx = i;
        
        boolean numFound = false;
        
        while(idx < s.length()){
            char ch = s.charAt(idx);
            
            if(ch == '-'){
                if(numFound){
                    break;
                }
                
                depth++;
            }
            else{
                int val = (int) (ch - '0');
                num *= 10;
                num += val;
                numFound = true;
            }
            
            idx++;
        }
        
         // System.out.println("S: " + i + "  d: " + depth + "  N: " + num + "  E: " + idx);
        
        return new int[]{depth, num, idx};
    }
	
	
    public TreeNode recoverFromPreorder(String S) {
        int idx = 0, len = S.length();
        
        Stack<TreeNode> stack = new Stack<>();
        while(idx < len){
            int level = 0;
            
            char ch = S.charAt(idx);
            while(idx < len && ch == '-'){
                idx++;
                level++;
                ch = S.charAt(idx);
            }
            
            int num = 0;
            while(idx < len && ch >= '0' && ch <= '9'){
                num *= 10;
                num += (int)(ch - '0');
                idx ++;
                if(idx < len){
                    ch = S.charAt(idx);
                }
            }
            
            while(!stack.isEmpty() && stack.size() > level){
                stack.pop();
            }
            
            TreeNode node = new TreeNode(num);
            if(!stack.isEmpty() && stack.peek().left == null){
                stack.peek().left = node;
            }
            else if(!stack.isEmpty() && stack.peek().right == null){
                stack.peek().right = node;
            }
            
            stack.push(node);
        }
        
        // when the tree is unbalanced.
        while(stack.size() > 1){
            stack.pop();
        }
        
        return stack.peek();
    }
    
    private static class TreeNode{
    	int val;
    	TreeNode left, right;
    	
    	public TreeNode(int val) {
    		this.val = val;
    	}
    }
}
