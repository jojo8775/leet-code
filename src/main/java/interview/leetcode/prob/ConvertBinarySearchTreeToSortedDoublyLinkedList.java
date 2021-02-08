package interview.leetcode.prob;

import java.util.Stack;

/**
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.

 

Example 1:



Input: root = [4,2,5,1,3]


Output: [1,2,3,4,5]

Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

Example 2:

Input: root = [2,1,3]
Output: [1,2,3]
Example 3:

Input: root = []
Output: []
Explanation: Input is an empty tree. Output is also an empty Linked List.
Example 4:

Input: root = [1]
Output: [1]
 

Constraints:

-1000 <= Node.val <= 1000
Node.left.val < Node.val < Node.right.val
All values of Node.val are unique.
0 <= Number of Nodes <= 2000
Accepted
104,915
Submissions
171,883
 * @author jojo
 * Feb 4, 2021  11:01:28 PM
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    public Node treeToDoublyList(Node root) {
        if(root == null){
            return root;
        }
        
        Node ref = new Node(0), cur = ref, head = cur;
        
        Stack<Node> stack = new Stack<>();
         
        while(!stack.isEmpty() || root != null){
            if(root != null){
                stack.push(root);
                root = root.left;
            }
            else{
                root = stack.pop();
                cur.right = root;
                root.left = cur;
                cur = cur.right;
                
                root = root.right;
            }
        }
        
        
        head = ref.right;
        cur.right = head;
        head.left = cur;
        
        return head;
    }
    
//	private Node ref = null, cur = null, head = null;
//
//	public Node treeToDoublyList_rec(Node root) {
//		if (root == null) {
//			return root;
//		}
//
//		ref = new Node(0);
//		cur = ref;
//		head = cur;
//
//		inOrder(root);
//
//		head = ref.right;
//		cur.right = head;
//		head.left = cur;
//
//		return head;
//	}
//
//	private void inOrder(Node node) {
//		if (node == null) {
//			return;
//		}
//
//		inOrder(node.left);
//
//		cur.right = node;
//		node.left = cur;
//		cur = cur.right;
//		// System.out.print(cur.val + ", ");
//
//		inOrder(node.right);
//	}

	private static class Node {
		Node left, right;
		int val;

		Node(int val) {

		}
	}
}
