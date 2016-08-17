package interview.leetcode.prob;

/**
 * Given a singly linked list, group all odd nodes together followed by the even
 * nodes. Please note here we are talking about the node number and not the
 * value in the nodes.
 * 
 * You should try to do it in place. The program should run in O(1) space
 * complexity and O(nodes) time complexity.
 * 
 * Example: Given 1->2->3->4->5->NULL, return 1->3->5->2->4->NULL.
 * 
 * Note: The relative order inside both the even and odd groups should remain as
 * it was in the input. The first node is considered odd, the second node even
 * and so on ...
 */
public class OddEvenLinkedList {
	public ListNode oddEvenList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode oddRunner = new ListNode(0), oddHead = oddRunner, evenRunner = new ListNode(0), evenHead = evenRunner,
				cur = head;

		while (cur != null) {
			oddRunner.next = cur;
			oddRunner = oddRunner.next;
			cur = cur.next;
			oddRunner.next = null;

			if (cur != null) {
				evenRunner.next = cur;
				evenRunner = evenRunner.next;
				cur = cur.next;
				evenRunner.next = null;
			}
		}

		oddRunner.next = evenHead.next;

		return oddHead.next;
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
