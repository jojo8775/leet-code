package interview.leetcode.prob;

/**
 * Sort a linked list using insertion sort.
 * @author jojo
 *
 */
public class InsertionSortList {
	public ListNode insertionSortList(ListNode head) {
		if (head == null) {
			return head;
		}

		ListNode dummy = new ListNode(0), cur = head, next = null;

		while (cur != null) {
			next = cur.next;
			cur.next = null;

			insert(cur, dummy);
			cur = next;
		}

		return dummy.next;
	}

	private void insert(ListNode cur, ListNode dummy) {
		ListNode prev = dummy, next = prev.next;

		// finding correct spot for cur between prev and end
		while (next != null) {
			if (cur.val < next.val) {
				break;
			}

			prev = prev.next;
			next = next.next;
		}

		// insert curr between prev and next
		prev.next = cur;
		cur.next = next;
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
