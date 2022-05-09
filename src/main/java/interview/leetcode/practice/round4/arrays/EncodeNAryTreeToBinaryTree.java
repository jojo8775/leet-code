package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Design an algorithm to encode an N-ary tree into a binary tree and decode the binary tree to get the original N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. Similarly, a binary tree is a rooted tree in which each node has no more than 2 children. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that an N-ary tree can be encoded to a binary tree and this binary tree can be decoded to the original N-nary tree structure.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See following example).

For example, you may encode the following 3-ary tree to a binary tree in this way:



Input: root = [1,null,3,2,4,null,5,6]
Note that the above is just an example which might or might not work. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 

Example 1:

Input: root = [1,null,3,2,4,null,5,6]
Output: [1,null,3,2,4,null,5,6]
Example 2:

Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 104].
0 <= Node.val <= 104
The height of the n-ary tree is less than or equal to 1000
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Accepted
16,891
Submissions
21,662
 * @author jojo
 * May 8, 2022 4:57:17 PM
 */
public class EncodeNAryTreeToBinaryTree {
	class Codec {
	    // Encodes an n-ary tree to a binary tree.
	    public TreeNode encode(Node root) {
	        return encode_adv(root);
	        // return createTreeNode(root);
	    }
	    
	    private TreeNode encode_adv(Node root){
	        if(root == null){
	            return null;
	        }
	        
	        TreeNode tNode = new TreeNode(root.val);
	        if(root.children != null && root.children.size() > 0){
	            tNode.left = encode_adv(root.children.get(0));
	        }
	        
	        TreeNode child = tNode.left;
	        
	        for(int i=1; i<root.children.size(); i++){
	            child.right = encode_adv(root.children.get(i));
	            child = child.right;
	        }
	        
	        return tNode;
	    }
	    
	    private TreeNode createTreeNode(Node root){
	        if(root == null){
	            return null;
	        }
	        
	        List<TreeNode> tNodes = new LinkedList<>();
	        for(Node child : root.children){
	            tNodes.add(createTreeNode(child));
	        }
	        
	        TreeNode tNode = new TreeNode(root.val);
	        if(tNodes.size() > 0){
	            tNode.left = tNodes.get(0);
	            
	            TreeNode temp = tNode.left;
	            for(int i=1; i<tNodes.size(); i++){
	                temp.right = tNodes.get(i);
	                temp = temp.right;
	            }
	        }
	        
	        return tNode;
	    }
		
	    // Decodes your binary tree to an n-ary tree.
	    public Node decode(TreeNode root) {
	        return decode_adv(root);
	        // return createNode(root);
	    }
	    
	    private Node decode_adv(TreeNode root){
	        if(root == null){
	            return null;
	        }
	        
	        List<Node> children = new ArrayList<>();
	        if(root.left != null){
	            TreeNode child = root.left;
	            while(child != null){
	                children.add(decode_adv(child));
	                child = child.right;
	            }
	        }
	        
	        Node node = new Node(root.val);
	        node.children = children;
	        return node;
	    }
	    
	    private Node createNode(TreeNode root){
	        if(root == null){
	            return null;
	        }
	        
	        List<Node> children = getChildren(root);
	        
	        Node node = new Node(root.val);
	        node.children = children;
	        
	        return node;
	    }
	    
	    private List<Node> getChildren(TreeNode node){
	        List<Node> children = new ArrayList<>();
	        
	        if(node.left == null){
	            return children;
	        }
	        
	        node = node.left;
	        children.add(createNode(node));
	        
	        while(node.right != null){
	            children.add(createNode(node.right));
	            node = node.right;
	        }
	        
	        return children;
	    }
	}
	
	private static class TreeNode{
		int val;
		TreeNode left, right;
		
		public TreeNode(int val) {
			this.val = val;
		}
	}
	
	private static class Node{
		int val;
		List<Node> children;
		
		public Node(int val) {
			this.val = val;
		}
	}
}
