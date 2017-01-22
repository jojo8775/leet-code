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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prev = new ListNode(0), cur = prev, ref = prev;
        prev.next = head;
        
        while(n-->0){
            cur = cur.next;
        }
        
        while(cur.next != null){
            cur = cur.next;
            prev = prev.next;
        }
        
        prev.next = prev.next.next;
        return ref.next;
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
