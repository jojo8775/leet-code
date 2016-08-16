package interview.leetcode.prob;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * @author jojo
 *
 */
public class ReOrderList {
	public void reorderList(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}

		ListNode fastNode = head, slowNode = head;

		// finding the middle node
		while (fastNode.next != null) {
			fastNode = fastNode.next;
			if (fastNode.next != null) {
				fastNode = fastNode.next;
				slowNode = slowNode.next;
			}
		}

		// splitting list in to half
		ListNode head2 = slowNode.next;
		slowNode.next = null;

		// reversing the second part
		ListNode prev = null;
		ListNode cur = head2;
		while (cur != null) {
			ListNode temp = cur.next;
			cur.next = prev;
			prev = cur;
			cur = temp;
		}
		head2 = prev;

		// merging the list
		cur = head;
		while (cur != null && head2 != null) {
			ListNode temp = cur.next;
			cur.next = head2;
			cur = cur.next;
			head2 = head2.next;
			cur.next = temp;
			cur = cur.next;
		}
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
