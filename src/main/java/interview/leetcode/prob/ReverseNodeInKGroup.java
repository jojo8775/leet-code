package interview.leetcode.prob;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
 * @author jojo
 *
 */
public class ReverseNodeInKGroup
{
	public ListNode reverseKGroup(ListNode head, int k)
	{
		if (head == null || head.next == null)
		{
			return head;
		}

		ListNode root = new ListNode(0), buf = root, prev = head, cur = prev;
		int count = k;

		while (cur != null)
		{
			while (count-- > 1 && cur != null)
			{
				cur = cur.next;
			}

			if (cur == null)
			{
				break;
			}

			ListNode temp = cur.next;
			cur.next = null;
			buf.next = reverse(prev, cur);

			while (buf.next != null)
			{
				buf = buf.next;
			}

			buf.next = temp;
			cur = temp;
			prev = temp;

			count = k;
		}

		if (root.next == null)
		{
			return head;
		}

		return root.next;

	}

	private ListNode reverse(ListNode s, ListNode e)
	{
		ListNode buf = null, prev = s, cur = prev;

		while (cur != null)
		{
			cur = prev.next;
			prev.next = buf;
			buf = prev;
			if (cur != null)
			{
				prev = cur;
			}
		}

		return prev;
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

	public static void main(String[] args)
	{
		ListNode l = new ReverseNodeInKGroup().reverseKGroup(create(1, 2, 3, 4), 3);
		while (l != null)
		{
			System.out.print(l.val + ", ");
			l = l.next;
		}
	}

	private static ListNode create(int... i)
	{
		ListNode r = new ListNode(0), c = r;

		for (int e : i)
		{
			c.next = new ListNode(e);
			c = c.next;
		}

		return r.next;
	}
}
