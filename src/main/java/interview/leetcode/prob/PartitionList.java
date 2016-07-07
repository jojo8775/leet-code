package interview.leetcode.prob;

/**
 * Given a linked list and a value x, partition it such that all nodes less than
 * x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the
 * two partitions.
 * 
 * For example, Given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
 * 
 * @author jojo
 *
 */
public class PartitionList {
	public ListNode partition(ListNode head, int x) {
		ListNode l1 = new ListNode(1), r1 = l1, l2 = new ListNode(1), r2 = l2;

		while (head != null) {
			if (head.val < x) {
				l1.next = head;
				l1 = l1.next;
			} else {
				l2.next = head;
				l2 = l2.next;
			}

			head = head.next;
			l1.next = null;
			l2.next = null;
		}

		l1.next = r2.next;

		return r1.next;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
