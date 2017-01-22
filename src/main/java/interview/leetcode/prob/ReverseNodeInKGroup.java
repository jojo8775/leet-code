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
    public ListNode reverseKGroup(ListNode head, int k){
        if(k == 1){
            return head;
        }
    
        ListNode root = new ListNode(0), cur = head, prev = root;
        root.next = head;
        int count = k;
    
        while(cur != null){
            while(count-- > 1 && cur != null){
                cur = cur.next;
            }
    
            if(cur == null){
                break;
            }
    
            ListNode next = cur.next;
    
            cur.next = null;
            prev.next = reverse(prev.next);
    
            while(prev.next != null){
                prev = prev.next;
            }
    
            prev.next = next;
            cur = next;
            
            count = k;
        }
    
        return root.next;
    }   

    private ListNode reverse(ListNode node){
        ListNode prev = null, next = null;
    
        while(node != null){
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
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
