package interview.leetcode.prob;

/**
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * 
 * Subscribe to see which companies asked this question
 * 
 * Show Tags Show Similar Problems
 * 
 * @author jojo
 *
 */
public class MergeTwoSortedList
{
	public ListNode mergeTwoLists(ListNode l1, ListNode l2)
	{
        ListNode head = new ListNode(0), cur = head;

        while(l1 != null){
            if(l2 != null){
                if(l1.val < l2.val){
                    cur.next = l1;
                    l1 = l1.next;
                }
                else{
                    cur.next = l2;
                    l2 = l2.next;
                }
            }
            else{
                cur.next = l1;
                l1 = null;
            }
    
            cur = cur.next;
        }
    
        if(l2 != null){
            cur.next = l2;
        }    
    
        return head.next;
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
