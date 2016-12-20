package interview.leetcode.prob.paid;

import java.util.Stack;

/**
 * Given a non-negative number represented as a singly linked list of digits,
 * plus one to the number.
 * 
 * The digits are stored such that the most significant digit is at the head of
 * the list.
 * 
 * Example: Input: 1->2->3
 * 
 * Output: 1->2->4 Show Company Tags Show Tags Show Similar Problems
 * 
 * @author jojo
 *
 */
public class PlusOneLinkedList {
	public ListNode plusOne(ListNode head) {
		if (head == null) {
			return head;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;
		head = dummy;

		Stack<ListNode> stack = new Stack<ListNode>();
		while (head != null) {
			stack.push(head);
			head = head.next;
		}

		ListNode prev = stack.pop();
		prev.val += 1;

		int carry = prev.val / 10;
		prev.val %= 10;

		while (!stack.isEmpty()) {
			if (carry == 0) {
				break;
			}

			ListNode top = stack.pop();
			top.next = prev;
			prev = top;
			prev.val += carry;

			carry = prev.val / 10;
			prev.val %= 10;
		}

		return dummy.val == 0 ? dummy.next : dummy;
	}
	
	public ListNode recursive(ListNode head){
		if(head == null){
			return head;
		}
		
		int carry = solve(head);
		
		if(carry == 0){
			return head;
		}
		
		ListNode dummy = new ListNode(carry);
		dummy.next = head;
		return dummy;
	}
	
	private int solve(ListNode node){
		if(node == null){
			return 1;
		}
		
		int carry = solve(node.next);
		node.val += carry;
		carry = node.val/10;
		node.val %= 10;
		return carry;
	}
	
	// Definition for singly-linked list.
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
