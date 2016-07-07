package interview.leetcode.prob;

/**
 * Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5
 * @author jojo
 *
 */
public class RemoveListElement {
	public class Solution {
		public ListNode removeElements(ListNode head, int val) {
			ListNode r = new ListNode(10), p = r, c = head;

			while (c != null) {
				if (c.val != val) {
					p.next = c;
					p = p.next;
				}

				c = c.next;
			}

			p.next = null;

			return r.next;
		}
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
