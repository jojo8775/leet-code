package interview.leetcode.prob;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
 * @author jojo
 *
 */
public class RemoveNthNode
{
	public ListNode removeNthFromEnd(ListNode head, int n)
	{
		if (n == 1)
		{
			if (head.next == null)
			{
				return head.next;
			}

			ListNode prev = null, cur = head;
			while (cur.next != null)
			{
				prev = cur;
				cur = cur.next;
			}

			prev.next = null;
			return head;
		}

		ListNode cur = head, prev = head;
		while (n-- > 0)
		{
			cur = cur.next;
		}

		if (cur == null)
		{
			return prev.next;
		}

		while (cur.next != null)
		{
			prev = prev.next;
			cur = cur.next;
		}

		prev.next = prev.next.next;

		return head;
	}

	private static class ListNode
	{
		int val;
		ListNode next;

		ListNode(int x)
		{
			val = x;
		}
	}
}
