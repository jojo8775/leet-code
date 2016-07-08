package interview.leetcode.prob;

/**
 * Given a sorted linked list, delete all duplicates such that each element
 * appear only once.
 * 
 * For example, Given 1->1->2, return 1->2. Given 1->1->2->3->3, return 1->2->3.
 * 
 * @author jojo
 *
 */
public class RemoveDuplicateFromSortedList {
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode p = head, c = head.next;

		while (c != null) {
			if (c.val != p.val) {
				p.next = c;
				p = p.next;
			}

			c = c.next;
		}

		p.next = null;
		return head;
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
