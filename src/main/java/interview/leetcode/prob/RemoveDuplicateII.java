package interview.leetcode.prob;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
 * @author jojo
 *
 */
public class RemoveDuplicateII
{
	public ListNode deleteDuplicates(ListNode head)
	{
		ListNode prev = new ListNode(-1), root = prev, cur = head;
		prev.next = cur;

		while (cur != null)
		{
			while(cur.next != null && cur.val == cur.next.val){
				cur = cur.next;
			}
			
			if(!cur.equals(prev.next)){
				prev.next = cur.next;
			}
			else{
			    prev = prev.next;
			}
			
			if(cur!=null){
			    cur = cur.next;
			}
		}

		return root.next;
	}

	public static void main(String[] args)
	{
		ListNode r = new RemoveDuplicateII().deleteDuplicates(creatNode(1, 1, 1, 2, 3));
		while (r != null)
		{
			System.out.print(r.val + ", ");
			r = r.next;
		}
	}

	private static ListNode creatNode(int... i)
	{
		ListNode r = new ListNode(1), c = r;
		for (int a : i)
		{
			c.next = new ListNode(a);
			c = c.next;
		}

		return r.next;
	}
	
	private static class ListNode
	{
		int val;
		ListNode next;

		ListNode(int x)
		{
			val = x;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((next == null) ? 0 : next.hashCode());
			result = prime * result + val;
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ListNode other = (ListNode) obj;
			if (next == null)
			{
				if (other.next != null)
					return false;
			} else if (!next.equals(other.next))
				return false;
			if (val != other.val)
				return false;
			return true;
		}
	}
}
