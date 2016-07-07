package interview.leetcode.prob;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 * @author jojo
 *
 */
public class SwapPairNodes
{
	public ListNode swapPairs(ListNode head)
	{
		if (head == null || head.next == null)
		{
			return head;
		}

		ListNode root = new ListNode(0), buff = root, prev = head, cur = prev.next;

		while (cur != null)
		{
			buff.next = cur;
			prev.next = cur.next;
			cur.next = prev;

			buff = prev;
			prev = prev.next;
			if (prev == null)
			{
				cur = null;
			} else
			{
				cur = prev.next;
			}
		}

		return root.next;
	}

	public static class ListNode
	{
		int val;
		ListNode next;

		ListNode(int x)
		{
			val = x;
		}
	}
}
