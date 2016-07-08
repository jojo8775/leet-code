package interview.leetcode.prob;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * @author jojo
 *
 */
public class PalindromLinkedList {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        
        //if there are only two elements
        if(head.next.next == null){
            return head.val == head.next.val;
        }
        
        ListNode p1 = head, p2 = p1, p3=null;
        
        while(p2.next != null && p2.next.next != null){
            p2 = p2.next.next;
            
            //reversing the first half
            ListNode temp = p1.next;
            p1.next = p3;
            p3 = p1;
            p1 = temp;
        }
        
        //starting point for the second half
        ListNode p4 = p1.next;
                
        //connecting the first node
        p1.next = p3;
        
        //checking if its an odd palindrom
        if(p2.next == null){
            //skipping the mid
            p1 = p1.next;
        }
        
        while(p1 != null && p4 != null){
            if(p1.val != p4.val){
                return false;
            }
            
            p1 = p1.next;
            p4 = p4.next;
        }
        
        return true;
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
