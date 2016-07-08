package interview.leetcode.prob;

/**
 * Reverse a singly linked list.
 * @author jojo
 *
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        ListNode p = null;
        while(head != null){
            ListNode t = head.next;
            head.next = p;
            p = head;
            head = t;
        }
        
        return p;
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
